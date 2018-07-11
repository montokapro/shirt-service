

package com.example.cache

import common.impl.{MapStore}
import org.scalatest._

class MapStoreSpec extends FlatSpec with Matchers {

  "A MapStore" should "get a the value that was set" in {
    val store = new MapStore[Int, Int](0)

    store.at(1).get() should be (0)
    store.at(1).set(1)
    store.at(1).get() should be (1)
    store.at(1).set(0)
    store.at(1).get() should be (0)
  }

  "A MapStore" should "get without changing the value" in {
    val store = new MapStore[Int, Option[Int]](None)

    store.at(1).get() should be (None)
    store.at(1).get() should be (None)
    store.at(1).set(Some(1))
    store.at(1).get() should be (Some(1))
    store.at(1).get() should be (Some(1))
  }

  "A MapStore" should "not affect other values" in {
    val store = new MapStore[Int, Int](0)

    store.at(1).get() should be (0)
    store.at(2).set(2)
    store.at(1).get() should be (0)
    store.at(1).set(1)
    store.at(1).get() should be (1)
    store.at(3).set(3)
    store.at(1).get() should be (1)
  }

}