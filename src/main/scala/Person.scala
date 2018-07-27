//import java.util.Date
//
//import akka.actor.{Actor, Props}
//
//object PersonMessage {
//  case class Add(id: String)
//  case class Delete(id: String)
//  case class GarbageCollect()
//  case object Done
//}
//
///**
//  * A person has a closet and a current outfit.
//  */
//class Person(val id: String, val createDate: Date, val name: String) extends Actor {
//
//  override def preStart(): Unit = {
//    val outfitLog = context.actorOf(Props[OutfitLog], "outfitLog")
//    val closet = context.actorOf(Props[Closet], "closet")
//    val tshirts = context.actorOf(Props[Closet], "tshirts")
//
//    // seed
//    tshirts ! TShirtMessage.Add("red", "red")
//    tshirts ! TShirtMessage.Add("green", "green")
//    tshirts ! TShirtMessage.Add("blue", "blue")
//  }
//
//  def receive = {
//    // when the greeter is done, stop this actor and with it the application
//    case PersonMessage.GarbageCollect => {
//      outfitLog ! OutfitMessage.Ids
//      closet !
//    }
//  }
//
////  def garbageCollect() = {
////    snapshotSet
////  }
//}
