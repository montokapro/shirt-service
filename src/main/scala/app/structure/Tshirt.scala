//package app.structure
//
//import java.time.Instant
//import java.util.Date
//
//import common.impl.{MapStore, SeqLog}
//
//class Tshirt(val color: String, val createDate: Date, val checkDate: Date)
//
//class TshirtRef(val tShirtId: String, val date: Date)
//
///**
//  * A person has a closet and a current outfit.
//  */
//class Person(val id: String, val date: Date, val name: String) {
//  val tshirts: scala.collection.mutable.HashMap[String, Tshirt];
//
//
//  val tshirtUsages: MapStore[String, Date]
//
//  val favorites = new MapStore[String, Date]
//  val outfitLog = new SeqLog[Date, String]
//  val cleanLogs = new MapStore[String, SeqLog[Date, Boolean]]
//
//  def next(date: Date): Option[(String, Tshirt)] = {
//    tshirts.reduceOption(Ordering.by((p: (String, Tshirt)) => p._2.checkDate).min)
//  }
//
//  def garageSale(date: Date) {
//    Stream.continually(() => next(date)).dropWhile(_.apply().isDefined).head
//  }
//
//  private def validateShirt(date: Date): Boolean = {
//    val tshirtId: Option[String] = tshirts.getLast()
//
//    if (tshirtId.isEmpty) {
//      return false
//    }
//
//    if (validateShirt(tshirtId.get)) {
//      tshirtUsages.at(tshirtId.get).set(Option(date))
//    } else {
//      tshirtUsages.at(tshirtId.get).set(Option.empty)
//    }
//
//    return true
//  }
//
//  private def validateShirt(tshirtId: String): Boolean = {
//    true
//
////    favorites.at(tshirtId).get().isDefined.
////        && outfitLog.at(tshirtId).get().isDefined.
////        && cleanLogs.at(tshirtId).get().isDefined
//  }
//
//}
