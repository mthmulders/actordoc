import sbt._
import Keys._

object Dependencies {

  private val version = new {
    val akka            = "2.5.4"
    val asm             = "5.2"
    val scalaTest       = "3.0.0"
  }

  private val library = new  {
    val akka  = "com.typesafe.akka" %% "akka-actor" % version.akka
    val asm   = "org.ow2.asm" % "asm" % version.asm
    val test  = "org.scalatest" %% "scalatest" % version.scalaTest % Test
  }

  val coreDependencies: Seq[ModuleID] = Seq(
    library.akka,
    library.asm,
    library.test
  )

  val sbtPluginDependencies: Seq[ModuleID] = Seq(
    library.test
  )

  val appDependencies: Seq[ModuleID] = Seq(
    library.test
  )

}
