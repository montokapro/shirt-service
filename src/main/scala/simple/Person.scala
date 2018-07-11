package simple

import java.time.{Duration, Instant}
import java.util.Date

import common.`trait`.{Cell, Store}
import common.impl.{AccessMapStore, MapStore, SeqLog}

class OptionMapStore[K, V] extends MapStore[K, Option[V]](None)

case class Tshirt(val color: String, val createDate: Date)

/**
  * A person has a a collection of favorite shirts and a current outfit.
  * TODO: Hide access to internal state of structures, and add abstractions for iterating
  *
  * Each shirt may become "dirty".
  * TODO: Implement cleanLogs, which extend locators with a mutable state
  *
  * TODO: Reimplement with Akka using typed actors
  */
class Person() {
  val BUFFER = Duration.ofSeconds(5)
  val MEMORY = Duration.ofMinutes(5)

  implicit object DateOrdering extends Ordering[Date] {
    def compare(x: Date, y: Date) = x compareTo y
  }

  /*
   * Immutable data store for shirts
   *
   * Tracks when they were last accessed for garbage collection
   */
  val tshirts = new AccessMapStore[String, Option[Tshirt], Date](
    None,
    () => new Date(),
    () => Date.from(Instant.now().minus(BUFFER))
  )

  /*
   * Mutable data store for favorite tshirt ids
   *
   * Tracks when they were added for auditing
   */
  val favoriteStore = new OptionMapStore[String, Date]

  /*
   * Event log for tshirts ids worn
   *
   * Tracks when they were worn for auditing
   */
  val outfitLog = new SeqLog[Date, Option[String]](None)

  /*
   * Mutable data store for event logs of tshirt ids
   *
   * Tracks when they were cleaned and dirtied for auditing
   */
  val cleanLogs = new OptionMapStore[String, SeqLog[Date, String]]

  // TODO: Thread.sleep
//  private val gc = Stream.continually(() => tshirts.next())
//    .flatMap(_.apply())
//    .dropWhile(shirt => validateShirt(shirt))
//    .foreach(shirt => tshirts.at(shirt).set(Option.empty))

  // a person may go through all shirts they own, and give up any that are unused
  def garageSale() {
    tshirts.next().foreach(shirt => {
      if (!validateShirt(shirt)) {
        tshirts.at(shirt).set(None)
      }
      tshirts.at(shirt).get() // access
      garageSale()
    })
  }

  private def validateShirt(tshirtId: String): Boolean = {
    favoriteStore.at(tshirtId).get().isDefined ||
      outfitLog.seq.flatMap(_._2).contains(tshirtId) ||
      cleanLogs.map.map(_._1).filter(_ == tshirtId).headOption.isDefined
  }

//  // a person may wash any shirt they own that they are not wearing
//  private def wash(tshirtId: String, detergent: String) {
//    computeIfAbsent(cleanLogs, () => new SeqLog[Date, Option[String]](None)(tshirtId)
//      .at(new Date())
//      .set(Option(detergent)))
//  }
//
//  private def wear(tshirtId: String) {
//    computeIfAbsent(cleanLogs, () => new SeqLog[Date, Option[String]](None)(tshirtId)
//      .at(new Date())
//      .set(Option.empty))
//  }

//  private def continuation[V](v: V, g: V => Unit): V = {
//    g(v)
//    v
//  }
//
//  private def computeIfAbsent[K, V](mapStore: OptionMapStore[K, V], default: () => V): K => V = key => {
//    //    key => mapStore.at(key).get().getOrElse(continuation(default(), v => mapStore.at(key).set(Option(v))))
//
//    val address = mapStore.at(key)
//
//    address.get() match {
//      case Some(v) => v
//      case None => {
//        val v = default()
//        address.set(Option(v))
//        v
//      }
//    }
//  }

  /*
   * A person may get and set their favorite shirts by id
   *
   * A shirt must already exist with the supplied id
   */
  def favoriteIds() = new Store[String, Boolean] {
    def at(tshirtId: String) = new Cell[Boolean] {
      override def get(): Boolean = {
        favoriteStore.at(tshirtId).get().isDefined
      }

      override def set(value: Boolean): Unit = {
        value match {
          case true => {
            if (tshirts.at(tshirtId).get().isDefined) {
              favoriteStore.at(tshirtId).set(Some(new Date()))
            }
          }
          case false => {
            favoriteStore.at(tshirtId).set(None)
          }
        }
      }
    }
  }

  /*
   * A person may get and set their favorite shirts
   *
   * A shirt will be created if one does not exist with the given id
   */
  def favorites() = new Store[String, Option[Tshirt]] {
    def at(tshirtId: String) = new Cell[Option[Tshirt]] {
      override def get(): Option[Tshirt] = {
        favoriteStore.at(tshirtId).get()
          .flatMap(v => tshirts.at(tshirtId).get())
      }

      override def set(value: Option[Tshirt]): Unit = {
        value match {
          case Some(v) => {
            favoriteStore.at(tshirtId).set(Some(new Date()))
            tshirts.at(tshirtId).set(Some(v))
          }
          case None => {
            favoriteStore.at(tshirtId).set(None)
            tshirts.at(tshirtId).set(None)
          }
        }
      }
    }
  }

  /*
   * A person may get and set their favorite outfit by id
   *
   * A shirt must already exist with the supplied id
   */
  def outfitId() = new Cell[Option[String]] {
    override def get(): Option[String] = {
      outfitLog.at(new Date()).get()
    }

    override def set(value: Option[String]): Unit = {
      value match {
        case Some(v) => {
          if (tshirts.at(v).get().isDefined) {
            outfitLog.at(new Date()).set(value)
          }
        }
        case None => {
          outfitLog.at(new Date()).set(None)
        }
      }
    }
  }

  /*
   * A person may get and set their favorite outfit
   *
   * A shirt will be created if one does not exist with the given id
   */
  def outfit() = new Cell[Option[(String, Tshirt)]] {
    override def get(): Option[(String, Tshirt)] = {
      outfitLog.at(new Date()).get()
        .flatMap(tshirtId => tshirts.at(tshirtId).get().map(tshirt => (tshirtId, tshirt)))
    }

    override def set(value: Option[(String, Tshirt)]): Unit = {
      value.foreach(v => tshirts.at(v._1).set(Some(v._2)))

//      value.foreach(v => wear(v._1)) // TODO: clean

      outfitLog.at(new Date()).set(value.map(_._1))
    }
  }

}
