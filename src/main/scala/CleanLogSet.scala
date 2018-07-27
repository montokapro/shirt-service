import java.util.Date

import scala.collection.mutable.HashMap

import akka.actor.Actor

object CleanMessage {
  case class Add(id: String)
  case class Delete(id: String)
  case object Done
}

class CleanLogSet extends Actor {
  val map: HashMap[String, Date] = HashMap();

  def receive = {
    case CleanMessage.Add(id) => map += ((id, new Date()))
    case CleanMessage.Delete(id) => map -= id
    case CleanMessage.Done => context.stop(self)
  }
}