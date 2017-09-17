package io.github.actordoc.core.testapp

import akka.actor.{Actor, ActorRef, ActorSystem, Props}

class Actor1 extends Actor {
  override def receive: Receive = ???
}
class Actor2(actor1: ActorRef) extends Actor {
  override def preStart(): Unit = actor1 ! ""
  override def receive: Receive = ???
}

object TestApp extends App {
  private val system = ActorSystem("test")
  private val actor1 = system.actorOf(Props(new Actor1()))
  private val actor2 = system.actorOf(Props(new Actor2(actor1)))
  actor2 ! ""
}
