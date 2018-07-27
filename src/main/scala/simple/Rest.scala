//package simple
//
//import scala.collection.mutable.HashMap
//
//
//class Sheep(val name: String, val color: String)
//class Mob(val name: String, val set: Map[String, Sheep])
//class Sleep(val name: String, val set: Map[String, Sheep])
//
//
///*
// * This class explores the symmetries and asymmetries of a RESTful api.
// *
// * RESTful design can lead to confusing asymmetric api design.
// * This class aims to show why those asymmetries arise, and how they can be mitigated.
// */
//class Rest {
//  type Color = String
//
//  type SheepData = Map[String, Color]
//  type MobData = Map[String, SheepData]
//  type RanchData = Map[String, MobData]
//
//  type SheepList = List[(Option[String], Option[Color])]
//  type MobList = List[(Option[String], Option[SheepList])]
//  type RanchList = List[(Option[String], Option[MobList])]
//
//  var ranches = scala.collection.mutable.HashMap[String, MobData]()
//
//  /*
//   * mobIds
//   *
//   * Symmetry exists between GET and SET for ids. SET can be divided into PUT and DELETE.
//   *
//   * No POST exists, because we are posting nothing and the id would be generated.
//   */
//
//  /*
//   * GET /ranches/<name>
//   *
//   * Some -> 200
//   * None -> 404
//   */
//  def getMobIds(name: String): Option[Set[String]] = {
//    ranches.get(name).keys()
//  }
//
//  /*
//   * DELETE /ranches/<name>
//   *
//   * True -> 204
//   * False -> 404
//   */
//  def deleteMobIds(name: String): Boolean = {
//    ranches.remove(name).isDefined
//  }
//
//  /*
//   * PUT /ranches/<name>/mobIds/<name>
//   *
//   * True -> 204
//   * False -> 404
//   */
//  def putMobIds(name: String, ids: Set[String]): Boolean = {
//    // putIfAbsent
////    ranches = ranches - name
//  }
//
//  /*
//   * mobs
//   *
//   * Symmetry exists between GET and SET for mobs as well. SET can be again be divided into PUT and DELETE.
//   *
//   * POST is an alias for PUT that generates ids server side. Notably, this behavior is not idempotent.
//   */
//
//  /*
//   * GET /ranches/<name>
//   *
//   * None -> 404
//   * Some -> 200
//   */
//  def getMobs(name: String): Option[MobData] = {
//    ranches.get(name)
//  }
//
//  /*
//   * PUT /ranches/<name>
//   *
//   * None -> 404
//   * Some -> 200
//   */
//  def getMobs(name: String, MobData): Option[MobList] = {
//    ranches.get(name)
//  }
//
//  List<Sheep>
//}
