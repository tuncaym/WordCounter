name := "WordCounter"

version := "0.1"

scalaVersion := "2.13.10"


libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.6.18",
  "com.typesafe.akka" %% "akka-http" % "10.2.7",
  "com.typesafe.akka" %% "akka-http-spray-json" % "10.2.7",
  "com.typesafe.akka" %% "akka-stream" % "2.6.18",
  "org.scalatest" %% "scalatest" % "3.2.9" % Test,
  "org.mockito" %% "mockito-scala" % "1.16.42" % Test,
  "com.typesafe.akka" %% "akka-http-testkit" % "10.2.6" % Test,
  "com.typesafe.akka" %% "akka-testkit" % "2.6.18" % Test





)
