package actor

import actor.ShirtActor.{HealthCheckRequest, HealthCheckResponse}
import akka.NotUsed
import akka.actor.typed.scaladsl.Behaviors
import akka.actor.typed.{ActorRef, ActorSystem, Behavior, Terminated}

object Main {

  def main(args: Array[String]) = {
    System.out.println("a")

    val system: ActorSystem[HealthCheckResponse] = ActorSystem(MainActor.main, "main")

    System.out.println("b")
  }

}
