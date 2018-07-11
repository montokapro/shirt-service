name := "menu"

version := "0.1"

scalaVersion := "2.12.6"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.5.8",
  "org.scalatest" % "scalatest_2.12" % "3.0.5" % "test"
)

licenses := Seq(("CC0", url("http://creativecommons.org/publicdomain/zero/1.0")))