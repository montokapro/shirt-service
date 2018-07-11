package common.impl

import common.`trait`.{Cell, Log, Store}

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

class SeqLog[K, V](default: V)(implicit ordering: Ordering[K]) extends Log[K, V] with Store[K, V] {
  var seq: mutable.Buffer[(K, V)] = new ListBuffer[(K, V)]()

  override def purge(key: K): Unit = {
    seq = seq.dropWhile(b => ordering.gteq(key, b._1))
  }

  override def view(key: K): Seq[(K, V)] = {
    seq.drop(seq.lastIndexWhere(b => ordering.gteq(key, b._1)))
  }

  override def at(key: K): Cell[V] = new Cell[V] {
    override def get(): V = {
      seq.takeWhile(b => ordering.gteq(key, b._1))
        .lastOption
        .map(_._2)
        .getOrElse(default)
    }

    override def set(value: V): Unit = {
      val index = seq.lastIndexWhere(b => ordering.gteq(key, b._1)) + 1

      seq.insert(index, (key, value))
    }
  }
}
