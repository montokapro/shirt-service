//package actor
//
//import akka.actor.{Actor, TypedActor, TypedProps}
//import org.scalatest.FunSpec
////import akka.actor.{TypedActor, TypedProps}
//import akka.actor.typed.ActorRef
////import akka.actor.{Props, TypedActor, TypedProps}
//import akka.actor.typed.scaladsl.Behaviors
//import akka.testkit.typed.scaladsl.ActorTestKit
//import akka.testkit.{ImplicitSender, TestActors, TestKit}
////import akka.typed.scaladsl.Actor
////import akka.typed.{ActorRef, ActorSystem}
//import org.scalatest.{BeforeAndAfterAll, FlatSpec, Matchers, WordSpecLike}
//
//import akka.NotUsed
//import akka.actor.typed.scaladsl.Behaviors
//import akka.actor.typed.{ ActorRef, ActorSystem, Behavior, Terminated }
//
////class ShirtActorSpec extends FlatSpec with Matchers {
////
////  "A ShirtActor" should "healthcheck" in {
////    val root = Actor.deferred[Nothing] { ctx =>
////      import ShirtActor._
////      val shirtActor: ActorRef[Command] = ctx.spawn(shirtBehavior, "shirt")
////      shirtActor ! HealthCheck
////
////      Actor.empty
////    }
////    val system = ActorSystem.create[Nothing](root, "ShirtActor")
////  }
////}
//
////val root = Actor.deferred[Nothing] { ctx =>
////  import ShirtActor._
////  val shirtActor: ActorRef[Command] = ctx.spawn(shirtBehavior, "shirt")
////  shirtActor ! HealthCheck
////
////  Actor.empty
////}
////val system =
//
//class ShirtActorSpec extends FunSpec with BeforeAndAfterAll {
//
//  describe("ShirtActor") {
//    val system: ActorSystem[MainActor.Main] = ActorSystem(MainActor.main, "main")
//
//    it("should have a health check") {
//      system ! MainActor.main()
//    }
//  }
////
////  override def afterAll {
////    shutdownTestKit()
////  }
////
////  ""
////
////
////
////  system ! MainActor.Main("Two")
////
//////  val system = ActorSystem("actor-system", Props(ShirtActor()))
//////  val ctx: EffectfulActorContext[Ping] = new EffectfulActorContext[Ping]("ping", Ping.props(), system)
//////
//////
//////  val forwardRef = system.actorOf(Props(classOf[ForwardingActor], testActor))
////
////
////  val root = Behaviors.receive[Nothing]( (ctx, _) => {
////    import ShirtActor._
////    val shirtActor: ActorRef[Command] = ctx.spawn(shirtBehavior, "shirt")
////    shirtActor ! HealthCheck
////  })
////
////  val shirtActor = system.systemActorOf[Nothing](root, "ShirtActor")
////
//////  val system = ActorSystem.create[Nothing](root, "ShirtActor")
////
////
////  val childActor = Behaviors.receiveMessage[String] { _ ⇒
////    Behaviors.same[String]
////  }
////
////
////
////  "A shirt actor" must {
////
////    "respond to health check" in {
////      case class Ping(msg: String, response: ActorRef[ShirtActor])
////      case class Pong(msg: String)
////
////      val echoActor = Behaviors.receive[HealthCheck] { (_, msg) ⇒
////        msg match {
////          case Ping(m, replyTo) ⇒
////            replyTo ! Pong(m)
////            Behaviors.same
////        }
////      }
////    }
////
////  }
//}
