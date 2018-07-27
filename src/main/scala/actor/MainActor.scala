package actor

import actor.ShirtActor.{HealthCheckRequest, HealthCheckResponse}
import akka.NotUsed
import akka.actor.typed.{ActorRef, Behavior, Terminated}
import akka.actor.typed.scaladsl.Behaviors

object MainActor {
//  final case class Main(name: String)

  val main: Behavior[HealthCheckResponse] =
    Behaviors.setup { ctx =>
      System.out.println("c")

      val shirtActor: ActorRef[ShirtActor.HealthCheckRequest] = ctx.spawn(ShirtActor.healthCheck, "healthCheck")

      ctx.watch(shirtActor)

//      healthCheck ! ShirtActor.HealthCheck(self)

      shirtActor ! HealthCheckRequest(ctx.self)

//      Behaviors.receiveSignal {
//        case (_, Terminated(ref)) => Behaviors.stopped
//      }

      Behaviors.receiveMessage { msg ⇒
        msg match {
          case HealthCheckResponse() ⇒ {
            ctx.log.info("healthCheck received")
            //        respondTo ! HealthCheckResponse()
            Behaviors.same
          }
        }
      }
    }

  val check = Behaviors.receive[ShirtActor.HealthCheckResponse] { (ctx, msg) ⇒
    System.out.println("d")

    msg match {
      case HealthCheckResponse() ⇒
        ctx.log.info("healthCheck received")
//        respondTo ! HealthCheckResponse()
        Behaviors.same
    }
  }
}
