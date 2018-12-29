name := "CoverTheBoard"
version := "0.1"

lazy val coverTheBoard = Project("CoverTheBoard", file("."))
  .settings(scalaVersion := "2.12.8",
    libraryDependencies ++= Seq(
      "org.scalatest" %% "scalatest" % "3.0.5" % "test"
    )
  )

