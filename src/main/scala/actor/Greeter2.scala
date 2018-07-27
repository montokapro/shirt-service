//package actor
//
//import akka.typed.Behavior
//import akka.typed.scaladsl.Actor
//
//object Greeter2 {
//  sealed trait Command
//  case object Greet extends Command
//  final case class WhoToGreet(who: String) extends Command
//
//  val greeterBehavior: Behavior[Command] = greeterBehavior(currentGreeting = "hello")
//
//  private def greeterBehavior(currentGreeting: String): Behavior[Command] =
//    Actor.immutable[Command] { (ctx, msg) =>
//      msg match {
//        case WhoToGreet(who) =>
//          greeterBehavior(s"hello, $who")
//        case Greet =>
//          println(currentGreeting)
//          Actor.same
//      }
//    }
//}