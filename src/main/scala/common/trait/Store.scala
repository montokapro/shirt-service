package common.`trait`

/**
  * A store is a collection of cells.
  *
  * A cell does not need to be represented in memory, if it contains a default value.
  *
  * A common store implementation will have V extend Option.
  * - If Option.empty is returned as a value, then no entry exists.
  * - If Option.empty is set as a value, the entry will be removed.
  *
  * @tparam K
  * @tparam V
  */
trait Store[K, V] {
  /**
    * K -> (V -> () and () -> V)
    * K -> Cell<V>
    */
  def at(key: K): Cell[V];
}