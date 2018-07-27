import java.util.Date

import scala.collection.mutable.HashMap

import akka.actor.Actor

object FavoriteMessage {
  case class Add(id: String)
  case class Delete(id: String)
  case object Done
}

class Favorite extends Actor {
  val map: HashMap[String, Date] = HashMap();

  def receive = {
    case FavoriteMessage.Add(id) => map += ((id, new Date()))
    case FavoriteMessage.Delete(id) => map -= id
    case FavoriteMessage.Done => context.stop(self)
  }
}