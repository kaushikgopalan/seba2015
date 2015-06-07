name := "helpies"

version := "1.0"

lazy val `helpies`: Project = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  javaJdbc ,
  javaEbean ,
  cache ,
  javaWs,
  "org.webjars" %% "webjars-play" % "2.3.0-2",
  "com.googlecode.json-simple" % "json-simple" % "1.1.1",
  "org.webjars" % "bootstrap" % "3.3.4",
  "org.webjars" % "flat-ui" % "bcaf2de95e"
  //"org.xerial" % "sqlite-jdbc" % "3.8.10.1",
  //"com.applerao" % "hibernatesqlite" % "1.0" exclude("org.hibernate", "hibernate")
  )

libraryDependencies ++= Seq(
  javaJpa.exclude("org.hibernate.javax.persistence", "hibernate-jpa-2.0-api"),
  "org.hibernate" % "hibernate-entitymanager" % "4.3.9.Final"
)

//resolvers += "hibernatesqlite-maven" at "https://hibernate-sqlite.googlecode.com/svn/trunk/mavenrepo"

unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )  