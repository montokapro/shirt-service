

package common.impl

import java.time.Instant
import java.util.Date

import org.scalatest._

import scala.collection.mutable.ListBuffer

class SeqLogSpec extends FlatSpec with Matchers {

  "A SeqLog" should "get the value that was set" in {
    val log = new SeqLog[Int, String]("")

    log.at(1).get() should be ("")

    log.at(1).set("1")

    log.at(0).get() should be ("")
    log.at(1).get() should be ("1")
    log.at(2).get() should be ("1")

    log.at(5).set("5")
    log.at(0).get() should be ("")
    log.at(1).get() should be ("1")
    log.at(2).get() should be ("1")
    log.at(4).get() should be ("1")
    log.at(5).get() should be ("5")
    log.at(6).get() should be ("5")

    log.at(3).set("3")
    log.at(0).get() should be ("")
    log.at(1).get() should be ("1")
    log.at(2).get() should be ("1")
    log.at(3).get() should be ("3")
    log.at(4).get() should be ("3")
    log.at(5).get() should be ("5")
    log.at(6).get() should be ("5")

    log.at(3).set("")
    log.at(0).get() should be ("")
    log.at(1).get() should be ("1")
    log.at(2).get() should be ("1")
    log.at(3).get() should be ("")
    log.at(4).get() should be ("")
    log.at(5).get() should be ("5")
    log.at(6).get() should be ("5")
  }

  "A SeqLog" should "purge and view values" in {
    val log = new SeqLog[Int, String]("")

    log.at(5).set("5")
    log.at(1).set("1")
    log.at(3).set("3")

    log.view(2) should be (ListBuffer((1, "1"), (3, "3"), (5, "5")))
    log.view(3) should be (ListBuffer((3, "3"), (5, "5")))
    log.view(6) should be (ListBuffer((5, "5")))

    log.purge(4)

    log.view(2) should be (ListBuffer((5, "5")))
    log.view(3) should be (ListBuffer((5, "5")))
    log.view(6) should be (ListBuffer((5, "5")))

    log.purge(6)

    log.view(6) should be (ListBuffer())
  }

  "A SeqLog" should "accept dates" in {
    val now = Instant.now()

    val log = new SeqLog[Date, Option[String]](None)

    log.at(Date.from(now.plusSeconds(1))).get() should be (Option.empty)

    log.at(Date.from(now.plusSeconds(1))).set(Option("1"))

    log.at(Date.from(now.plusSeconds(0))).get() should be (Option.empty)
    log.at(Date.from(now.plusSeconds(1))).get() should be (Option("1"))
    log.at(Date.from(now.plusSeconds(2))).get() should be (Option("1"))

    log.at(Date.from(now.plusSeconds(5))).set(Option("5"))
    log.at(Date.from(now.plusSeconds(0))).get() should be (Option.empty)
    log.at(Date.from(now.plusSeconds(1))).get() should be (Option("1"))
    log.at(Date.from(now.plusSeconds(2))).get() should be (Option("1"))
    log.at(Date.from(now.plusSeconds(4))).get() should be (Option("1"))
    log.at(Date.from(now.plusSeconds(5))).get() should be (Option("5"))
    log.at(Date.from(now.plusSeconds(6))).get() should be (Option("5"))

    log.at(Date.from(now.plusSeconds(3))).set(Option("3"))
    log.at(Date.from(now.plusSeconds(0))).get() should be (Option.empty)
    log.at(Date.from(now.plusSeconds(1))).get() should be (Option("1"))
    log.at(Date.from(now.plusSeconds(2))).get() should be (Option("1"))
    log.at(Date.from(now.plusSeconds(3))).get() should be (Option("3"))
    log.at(Date.from(now.plusSeconds(4))).get() should be (Option("3"))
    log.at(Date.from(now.plusSeconds(5))).get() should be (Option("5"))
    log.at(Date.from(now.plusSeconds(6))).get() should be (Option("5"))
  }

}