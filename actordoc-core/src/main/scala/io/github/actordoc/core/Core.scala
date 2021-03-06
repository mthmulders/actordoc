package io.github.actordoc.core

import scala.collection.immutable.Seq
import akka.actor.Actor
import io.github.actordoc.core.impl.DefaultActorFinderComponent

class Core(packages: Seq[Package]) {

  private val actorFinderComponent = new DefaultActorFinderComponent {
  }

  def findActors(): Seq[Class[Actor]] = packages
    .flatMap(actorFinderComponent.actorFinder.findActors)
}
