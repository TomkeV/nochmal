val scala3Version = "3.3.1"

lazy val root = project
  .in(file("."))
  .settings(
    name := "Nochmal",
    version := "0.1.0-SNAPSHOT",

    scalaVersion := scala3Version,

    libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.10",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.10" % "test",

    libraryDependencies += "com.google.inject" % "guice" % "5.1.0",
    libraryDependencies += "com.google.inject.extensions" % "guice-assistedinject" % "7.0.0",

    libraryDependencies += "org.scala-lang.modules" %% "scala-swing" % "3.0.0"
  )
