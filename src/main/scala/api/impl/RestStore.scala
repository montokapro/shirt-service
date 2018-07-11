package api.impl

import api.Rest
import common.`trait`.Store

class RestStore[K, V](store: Store[K, Option[V]], getKey: () => K) extends Rest[K, V] {

  def get(key: K): Option[V] = {
    store.at(key).get()
  }

  def post(value: V): K = {
    val key = getKey.apply()

    store.at(key).set(Some(value))

    key
  }

  def put(key: K, value: V): Unit = {
    store.at(key).set(Some(value))
  }

  def delete(key: K): Unit = {
    store.at(key).set(None)
  }

}
