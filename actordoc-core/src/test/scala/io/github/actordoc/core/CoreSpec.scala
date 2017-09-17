package io.github.actordoc.core

import scala.collection.immutable.Seq

import io.github.actordoc.core.testapp.{Actor1, Actor2, TestApp}

class CoreSpec extends BaseSpec {
  "ActorDoc core" should {
    "find actors in package" in {
      // Arrange
      val packages = Seq(
        TestApp.getClass.getPackage
      )

      // Act
      val core = new Core(packages)
      val actors = core.findActors()

      // Assert
      actors should contain only (classOf[Actor1], classOf[Actor2])
    }
  }
}
