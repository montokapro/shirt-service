//package actor
//
//import akka.typed.{ActorRef, ActorSystem}
//import akka.typed.scaladsl.Actor
//import org.scalatest.{FlatSpec, Matchers}
//
//class Greeter2Spec extends FlatSpec with Matchers {
//
//  "A Greeter2" should "greet" in {
//    val root = Actor.deferred[Nothing] { ctx =>
//      import Greeter2._
//      val greeter: ActorRef[Command] = ctx.spawn(greeterBehavior, "greeter")
//      greeter ! WhoToGreet("World")
//      greeter ! Greet
//
//      Actor.empty
//    }
//    val system = ActorSystem.create[Nothing](root, "HelloWorld")
//  }
//}
