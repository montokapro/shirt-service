package common.`trait`

/**
  * A common cell implementation will have V extend Option.
  * - If Option.empty is returned as a value, then no value is found.
  * - If Option.empty is set as a value, the value will be removed.
  *
  * @tparam V datatype
  */
trait Cell[V] {
  /**
    * V -> ()
    */
  def set(value: V): Unit

  /**
    * () -> V
    */
  def get(): V
}
