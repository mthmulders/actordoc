import sbt._
import Keys._
import sbtassembly.AssemblyPlugin.autoImport._
import com.sksamuel.scapegoat.sbt.ScapegoatSbtPlugin.autoImport._
import sbtsonar.SonarPlugin.autoImport.sonarProperties


object Settings {

  lazy val settings = Seq(
    organization := "io.github.actordoc",
    version := "0.0.1" + sys.props.getOrElse("buildNumber", default="0-SNAPSHOT"),
    scalaVersion := "2.12.0",
    publishMavenStyle := true,
    publishArtifact in Test := false,
    scapegoatVersion in ThisBuild := "1.3.3",
  )

  lazy val testSettings = Seq(
    fork in Test := false,
    parallelExecution in Test := false
  )

  lazy val itSettings = Defaults.itSettings ++ Seq(
    logBuffered in IntegrationTest := false,
    fork in IntegrationTest := true
  )

  lazy val appSettings = Seq(
    assemblyJarName in assembly := "actordoc-" + version.value + ".jar",
    test in assembly := {},
    target in assembly := file(baseDirectory.value + "/../bin/"),
    assemblyOption in assembly := (assemblyOption in assembly).value.copy(
      includeScala = true,
      includeDependency=true),
    assemblyMergeStrategy in assembly := {
      case PathList("META-INF", xs@_*) => MergeStrategy.discard
      case n if n.startsWith("reference.conf") => MergeStrategy.concat
      case _ => MergeStrategy.first
    }
  )

  lazy val coreSettings = Seq()

  lazy val sbtPluginSettings = Seq()

  lazy val sonarSettings = Seq(
    sonarProperties ++= Map(
      "sonar.host.url" -> "https://sonarcloud.io",
      "sonar.modules" -> "actordoc-core,actordoc-app,actordoc-sbt",
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
      "actordoc-sbt.sonar.sources" -> "src/main/scala"
    )
  )

}
