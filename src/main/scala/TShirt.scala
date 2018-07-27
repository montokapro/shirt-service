//import java.util.Date
//
//import akka.actor.Actor
//
//import scala.collection.mutable.HashMap
//
//object TShirtMessage {
//  case class Put(id: Option[String], color: String)
//  case class Get(id: String) // -> color: Option[String]
//  case object Done
//}
//
//class TShirt(val id: String, val createDate: Date, val color: String)
//
//class TShirtActor extends Actor {
//  val map: HashMap[String, (Date, String)] = HashMap();
//
//  def receive = {
//    case TShirtMessage.Put(id, color) => map.put(id, (new Date(), color))
//    case TShirtMessage.Get(id) => map.get(id)
//    case TShirtMessage.Delete(id) => map -= id
//    case TShirtMessage.Done => context.stop(self)
//  }
//}
