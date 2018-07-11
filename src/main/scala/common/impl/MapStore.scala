package common.impl

import common.`trait`.{Cell, Store}

import scala.collection.mutable

class MapStore[K, V](default: V) extends Store[K, V] {
  val map = new mutable.HashMap[K, V]();

  override def at(key: K): Cell[V] = new Cell[V] {
    override def get(): V = {
      map.getOrElse(key, default)
    }

    override def set(value: V): Unit = {
      value match {
        case `default` => map.remove(key)
        case v => map.put(key, v)
      }
    }
  }
}
