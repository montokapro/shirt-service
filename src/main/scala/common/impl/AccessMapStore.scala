package common.impl

import common.`trait`.{Cell, Store}

import scala.collection.mutable

class AccessMapStore[K, V, A](default: V, access: () => A, check: () => A)(implicit ordering: Ordering[A]) extends Store[K, V] {
  val map = new mutable.HashMap[K, (V, A)]()

  override def at(key: K): Cell[V] = new Cell[V] {
    override def get(): V = {
      val value = map.get(key).map(_._1).getOrElse(default)

      // TODO: schedule operation to update access, okay if fails
      set(value)

      value
    }

    override def set(value: V): Unit = {
      value match {
        case `default` => map.remove(key)
        case v => map.put(key, (v, access()))
      }
    }
  }

  def next(): Option[K] = {
    // TODO: this map should be ordered, so it can be more efficient

    map.reduceOption(Ordering.by((p: (K, (V, A))) => p._2._2).min)
      .filter(b => ordering.lteq(b._2._2, check()))
      .map(_._1)
  }
}
