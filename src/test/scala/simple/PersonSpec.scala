package simple

import java.util.Date

import org.scalatest.{FlatSpec, Matchers}

class PersonSpec extends FlatSpec with Matchers {

  "A Person" should "ignore missing favorite ids" in {
    val person = new Person()

    person.favoriteIds().at("a").get() should be (false)
    person.favoriteIds().at("a").set(true);
    person.favoriteIds().at("a").get() should be (false)
  }

  "A Person" should "ignore missing outfit id" in {
    val person = new Person()

    person.outfitId().get() should be (None)
    person.outfitId().set(Some("a"));
    person.outfitId().get() should be (None)
  }

  "A Person" should "add favorites" in {
    val person = new Person()
    val date = new Date()

    person.favorites().at("a").get() should be (None)
    person.favorites().at("a").set(Some(new Tshirt("a", date)));
    person.favorites().at("a").get() should be (Some(new Tshirt("a", date)))
    person.favoriteIds().at("a").get() should be (true)
  }

  "A Person" should "add outfit" in {
    val person = new Person()
    val date = new Date()

    person.outfit().get() should be (None)
    person.outfit().set(Some(("a", new Tshirt("a", date))));
    person.outfit().get() should be (Some(("a", new Tshirt("a", date))))
    person.outfitId().get() should be (Some("a"))
  }

  "A Person" should "garbage collection should be safe" in {
    val person = new Person()
    var date = new Date()

    person.outfit().set(Some(("outfitId", new Tshirt("red", date))))

    person.favorites().at("favoriteId").set(Some(new Tshirt("green", date)))

    Thread.sleep(1000)

    person.favorites().at("bothId").set(Some(new Tshirt("blue", date)))
    person.outfitId().set(Some("bothId"))

    person.tshirts.at("favoriteId").get().isDefined should be (true)
    person.tshirts.at("outfitId").get().isDefined should be (true)
    person.tshirts.at("bothId").get().isDefined should be (true)

    person.garageSale(); // should not remove anything

    person.favoriteIds().at("favoriteId").get() should be (true)
    person.favoriteIds().at("bothId").get() should be (true)
    person.outfitId().get() should be (Some("bothId"))

    person.tshirts.at("favoriteId").get().isDefined should be (true)
    person.tshirts.at("outfitId").get().isDefined should be (true)
    person.tshirts.at("bothId").get().isDefined should be (true)

    Thread.sleep(6000)

    person.garageSale(); // should not remove anything due to references

    person.favoriteIds().at("favoriteId").get() should be (true)
    person.favoriteIds().at("bothId").get() should be (true)
    person.outfitId().get() should be (Some("bothId"))

    person.tshirts.at("favoriteId").get().isDefined should be (true)
    person.tshirts.at("outfitId").get().isDefined should be (true)
    person.tshirts.at("bothId").get().isDefined should be (true)

    person.favoriteIds().at("favoriteId").set(false)
    person.favoriteIds().at("bothId").set(false)
    person.outfitId().set(None)

    person.outfitLog.purge(new Date())

    person.tshirts.at("favoriteId").get().isDefined should be (true)
    person.tshirts.at("outfitId").get().isDefined should be (true)
    person.tshirts.at("bothId").get().isDefined should be (true)

    person.garageSale(); // should not remove anything due to access dates

    person.tshirts.at("favoriteId").get().isDefined should be (true)
    person.tshirts.at("outfitId").get().isDefined should be (true)
    person.tshirts.at("bothId").get().isDefined should be (true)

    Thread.sleep(6000)

    person.garageSale(); // should remove all tshirts due to access dates

    person.tshirts.at("favoriteId").get().isDefined should be (false)
    person.tshirts.at("outfitId").get().isDefined should be (false)
    person.tshirts.at("bothId").get().isDefined should be (false)
  }

}
