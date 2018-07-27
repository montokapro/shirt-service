//package actor
//
////import akka.actor.Actor
////import akka.actor.typed.Behavior
////
////object ShirtActor {
////  sealed trait Command
////  case object HealthCheck extends Command
////
////  val shirtBehavior: Behavior[Command] =
////    Actor.immutable[Command] { (ctx, msg) =>
////      msg match {
////        case HealthCheck =>
////          println("OK")
////          Actor.same
////      }
////    }
////}
//
//import java.lang.String.{ valueOf ⇒ println }
//
//import akka.actor.{ ActorContext, ActorRef, TypedActor, TypedProps }
//import akka.routing.RoundRobinGroup
//import akka.testkit._
//
//import scala.concurrent.{ Future, Await }
//import scala.concurrent.duration._
//
//trait HealthCheckActor {
//  def healthCheck(): String //blocking send-request-reply
//}