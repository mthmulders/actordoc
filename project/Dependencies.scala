import sbt._
import Keys._

object Dependencies {

  private val version = new {
      val scalaTest       = "3.0.0"
  }

  private val library = new  {
      val test  = "org.scalatest" %% "scalatest" % version.scalaTest % Test
  }

  val coreDependencies: Seq[ModuleID] = Seq(
    library.test
  )

  val sbtPluginDependencies: Seq[ModuleID] = Seq(
    library.test
  )

  val appDependencies: Seq[ModuleID] = Seq(
    library.test
  )

}
