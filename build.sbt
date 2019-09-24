import Dependencies._
import Settings._

lazy val core = (project in file("actordoc-core")).
  settings(Settings.settings: _*).
  settings(Settings.coreSettings: _*).
  settings(libraryDependencies ++= coreDependencies)

lazy val sbtPlugin = (project in file("actordoc-sbt")).
  settings(Settings.settings: _*).
  settings(Settings.sbtPluginSettings: _*).
  settings(libraryDependencies ++= sbtPluginDependencies)

lazy val app = (project in file("actordoc-app")).
  settings(Settings.settings: _*).
  settings(Settings.appSettings: _*).
  settings(libraryDependencies ++= appDependencies).
  dependsOn(core).
  configs(Test)

val root = (project in file(".")).
  settings(Settings.settings: _*)
  settings(Seq(
    name := "actordoc-parent"
  )).
  settings(Settings.sonarSettings).
  settings(aggregate in sonarScan := false).
  aggregate(core, app, sbtPlugin)