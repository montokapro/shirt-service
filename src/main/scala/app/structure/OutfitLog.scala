package app.structure

import java.util.Date

import scala.collection.mutable.MutableList

import akka.actor.Actor

object OutfitMessage {
  case class Add(id: String)
  case class Purge(date: Date)
  case object Done
}

class OutfitLog extends Actor {
  val list: MutableList[(String, Date)] = MutableList();

  def receive = {
    case OutfitMessage.Add(id) => list += ((id, new Date()))
    case OutfitMessage.Purge(date) => list dropWhile (p => p._2.before(date))
    case OutfitMessage.Done => context.stop(self)
  }
}