package actor

import akka.actor.typed.scaladsl.Behaviors
import akka.actor.typed.{ActorRef, Behavior}
//import akka.actor.{ActorContext, ActorRef, TypedActor, TypedProps}

object ShirtActor {
  sealed trait HealthCheckCommand
  case class HealthCheckRequest(respondTo: ActorRef[HealthCheckResponse]) extends HealthCheckCommand
  case class HealthCheckResponse()

//  final case class HealthCheckRequest(replyTo: ActorRef[HealthCheckResponse])
//  final case class HealthCheckResponse()

  val healthCheck = Behaviors.receive[HealthCheckRequest] { (ctx, msg) ⇒
    System.out.println("e")

    msg match {
      case HealthCheckRequest(respondTo) => {
//        ctx.log.info("HealthCheck!")
        respondTo ! HealthCheckResponse()
        Behaviors.same
      }
    }
  }
}