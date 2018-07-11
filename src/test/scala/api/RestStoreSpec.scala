package api

import java.util.UUID

import api.impl.RestStore
import common.impl.MapStore
import org.scalatest.{FlatSpec, Matchers}

class RestStoreSpec extends FlatSpec with Matchers {

  "A RestStore" should "get" in {
    val store = new MapStore[String, Option[Int]](None)

    store.at("1").set(Some(1))

    val rest = new RestStore(store, () => UUID.randomUUID().toString())

    rest.get("0") should be (None)
    rest.get("1") should be (Some(1))
  }

  "A RestStore" should "post" in {
    val store = new MapStore[String, Option[Int]](None)

    val rest = new RestStore(store, () => UUID.randomUUID().toString())

    val key = rest.post(1)

    store.at("0").get() should be (None)
    store.at(key).get() should be (Some(1))
  }

  "A RestStore" should "put" in {
    val store = new MapStore[String, Option[Int]](None)

    val rest = new RestStore(store, () => UUID.randomUUID().toString())

    rest.put("1", 1)

    store.at("0").get() should be (None)
    store.at("1").get() should be (Some(1))
  }

  "A RestStore" should "delete" in {
    val store = new MapStore[String, Option[Int]](None)

    store.at("1").set(Some(1))
    store.at("2").set(Some(2))

    val rest = new RestStore(store, () => UUID.randomUUID().toString())

    rest.delete("1")

    store.at("1").get() should be (None)
    store.at("2").get() should be (Some(2))
  }

}
