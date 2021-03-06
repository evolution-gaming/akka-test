import Dependencies._

lazy val commonSettings = Seq(
  organization := "com.evolutiongaming",
  homepage := Some(new URL("http://github.com/evolution-gaming/akka-test")),
  startYear := Some(2019),
  organizationName := "Evolution Gaming",
  organizationHomepage := Some(url("http://evolutiongaming.com")),
  bintrayOrganization := Some("evolutiongaming"),
  scalaVersion := crossScalaVersions.value.head,
  crossScalaVersions := Seq("2.13.1", "2.12.10"),
  scalacOptions in(Compile, doc) ++= Seq("-groups", "-implicits", "-no-link-warnings"),
  scalacOptsFailOnWarn := Some(false),
  resolvers += Resolver.bintrayRepo("evolutiongaming", "maven"),
  licenses := Seq(("MIT", url("https://opensource.org/licenses/MIT"))),
  releaseCrossBuild := true)


lazy val root = (project in file(".")
  settings (name := "akka-test")
  settings commonSettings
  settings (skip in publish := true)
  aggregate(actor, http))

lazy val actor = (project in file("actor")
  settings (name := "akka-test-actor")
  settings commonSettings
  settings (libraryDependencies ++= Seq(
    Akka.default.actor,
    scalatest,
    Akka.newer.actor   % Test,
    Akka.default.slf4j % Test)))

lazy val http = (project in file("http")
  settings (name := "akka-test-http")
  settings commonSettings
  settings (libraryDependencies ++= Seq(
    Akka.default.actor,
    Akka.default.stream,
    AkkaHttp.default.core,
    scalatest,
    Akka.newer.actor         % Test,
    Akka.newer.stream        % Test,
    AkkaHttp.newer.core      % Test,
    AkkaHttp.default.testkit % Test)))