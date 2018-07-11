package api

/**
  * A restful api most often implements get, put, post, and delete
  *
  * @tparam K
  * @tparam V
  */
trait Rest[K, V] {

  def get(key: K): Option[V]

  def post(key: V): K

  def put(key: K, value: V): Unit

  def delete(key: K): Unit

}