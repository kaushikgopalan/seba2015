name := "helpies"

version := "1.0"

lazy val `helpies` = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  javaJdbc ,
  javaEbean ,
  cache ,
  javaWs,
  "org.webjars" %% "webjars-play" % "2.3.0-2",
<<<<<<< HEAD
  "com.googlecode.json-simple" % "json-simple" % "1.1.1",
  "org.webjars" % "bootstrap" % "3.3.4",
=======
  "org.webjars" % "bootstrap" % "3.1.1-2",
>>>>>>> 4ade564... database settings
  "org.webjars" % "flat-ui" % "bcaf2de95e"
)

unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )  