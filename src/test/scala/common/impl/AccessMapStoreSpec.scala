

package common.impl

import org.scalatest._

class AccessMapStoreSpec extends FlatSpec with Matchers {

  "An AccessMapStore" should "get a the value that was set" in {
    val store = new AccessMapStore[Int, Int, Int](0, () => 0, () => 0)

    store.at(1).get() should be (0)
    store.at(1).set(1)
    store.at(1).get() should be (1)
    store.at(1).set(0)
    store.at(1).get() should be (0)
  }

  "An AccessMapStore" should "get without changing the value" in {
    val store = new AccessMapStore[Int, Int, Int](0, () => 0, () => 0)

    store.at(1).get() should be (0)
    store.at(1).get() should be (0)
    store.at(1).set(1)
    store.at(1).get() should be (1)
    store.at(1).get() should be (1)
  }

  "An AccessMapStore" should "not affect other values" in {
    val store = new AccessMapStore[Int, Int, Int](0, () => 0, () => 0)

    store.at(1).get() should be (0)
    store.at(2).set(2)
    store.at(1).get() should be (0)
    store.at(1).set(1)
    store.at(1).get() should be (1)
    store.at(3).set(3)
    store.at(1).get() should be (1)
  }

  "An AccessMapStore" should "offer the least recently accessed item" in {
    var i = 0;

    val store = new AccessMapStore[Int, Option[Int], Int](None, () => i, () => i)

    store.next() should be (Option.empty)
    store.at(2).set(Option(2))
    i += 1
    store.next() should be (Option(2))
    store.at(1).set(Option(1))
    i += 1
    store.next() should be (Option(2))
    store.at(2).get()
    i += 1
    store.next() should be (Option(1))
    store.at(1).set(Option.empty)
    i += 1
    store.next() should be (Option(2))
    store.at(2).set(Option.empty)
    i += 1
    store.next() should be (Option.empty)
  }

  "An AccessMapStore" should "offer the least recently accessed item with a buffer" in {
    var i = 0;

    val store = new AccessMapStore[Int, Int, Int](0, () => i, () => i - 3)

    store.next() should be (None)
    store.at(1).set(1)
    i += 1
    store.next() should be (None)
    store.at(2).set(2)
    i += 1
    store.next() should be (None)
    store.at(3).set(3)
    i += 1
    store.next() should be (Some(1))
    store.at(4).set(4)
    i += 1
    store.next() should be (Some(1))
    store.at(1).get()
    store.next() should be (Some(2))
    store.at(2).get()
    store.next() should be (None)
    i += 1
    store.next() should be (Some(3))
  }

}