package io.github.actordoc.core

import scala.collection.immutable.Seq

import akka.actor.Actor

trait ActorFinderComponent {
  trait ActorFinder {
    def findActors(p: Package): Seq[Class[Actor]]
  }
}
