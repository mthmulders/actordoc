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

lazy val sonarSettings = Seq(
  sonarProperties ++= Map(
    "sonar.host.url" -> "https://sonarcloud.io",
    "sonar.modules" -> "actordoc-core,"actordoc-app,actordoc-sbt",
    "sonar.projectKey" -> "mthmulders_actordoc",
    "sonar.organization" -> "mthmulders-github",

    "sonar.sourceEncoding" -> "UTF-8",
    "sonar.scala.version" -> "2.12.8",

    "actordoc-core.sonar.scala.coverage.reportPaths" -> "target/scala-2.12/scoverage-report/scoverage.xml",
    "actordoc-core.sonar.scala.scapegoat.reportPaths" -> "target/scala-2.12/scapegoat-report/scapegoat-scalastyle.xml",
    "actordoc-core.sonar.sources" -> "src/main/scala",

    "actordoc-app.sonar.scala.coverage.reportPaths" -> "target/scala-2.12/scoverage-report/scoverage.xml",
    "actordoc-app.sonar.scala.scapegoat.reportPaths" -> "target/scala-2.12/scapegoat-report/scapegoat-scalastyle.xml",
    "actordoc-app.sonar.sources" -> "src/main/scala",

    "actordoc-sbt.sonar.scala.coverage.reportPaths" -> "target/scala-2.12/scoverage-report/scoverage.xml",
    "actordoc-sbt.sonar.scala.scapegoat.reportPaths" -> "target/scala-2.12/scapegoat-report/scapegoat-scalastyle.xml",
    "actordoc-sbt.sonar.sources" -> "src/main/scala",
  )
)