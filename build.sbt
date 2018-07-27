name := "menu"

version := "0.1"

scalaVersion := "2.12.6"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor-typed" % "2.5.12",
  "com.typesafe.akka" %% "akka-testkit-typed" % "2.5.12" % Test,
  "org.scalatest" % "scalatest_2.12" % "3.0.5" % Test
)

licenses := Seq(("CC0", url("http://creativecommons.org/publicdomain/zero/1.0")))