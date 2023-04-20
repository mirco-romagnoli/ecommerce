ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.10"

val slick = Seq(
  "com.typesafe.slick" %% "slick" % "3.4.1",
  "com.typesafe.slick" %% "slick-hikaricp" % "3.4.1",
  "org.postgresql" % "postgresql" % "42.6.0"
)
val slf4j = Seq(
  "org.slf4j" % "slf4j-api" % "2.0.6",
  "org.slf4j" % "slf4j-simple" % "2.0.5",
)

val upickle = Seq(
  "com.lihaoyi" %% "upickle" % "3.1.0"
)

lazy val root = (project in file("."))
  .settings(
    name := "ecommerce",
    idePackagePrefix := Some("it.bitrock"),
    libraryDependencies ++= slick ++ slf4j ++ upickle

  )
