package common.`trait`

/**
  * A log is an ordered history of events.
  *
  * A common log implementation will have K be a date.
  * - purge will clear all history before a date
  * - view will view all history at or after a date
  *
  * A class might consider encapsulating this class, and generate the keys
  *
  * @tparam K
  * @tparam V
  */
trait Log[K, V] {

  /**
    * K -> ()
    *
    * purge all history before key, except for the last value
    */
  def purge(key: K): Unit

  /**
    * K -> [V]
    *
    * view all history at and after key, plus the previous value
    */
  def view(key: K): Seq[(K, V)]
}