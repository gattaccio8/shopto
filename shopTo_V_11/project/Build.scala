import sbt._
import Keys._

object ShopToBuild extends Build {
  import scala.collection._
  import Dependency._
  import com.github.siasia._
  import WebPlugin._
  import Resolvers._
  import BuildSettings._

  lazy val root = Project(id = "shopTo_V_11", base = file("."), settings = buildSettings ++ webSettings ++ Seq(
  resolvers := Seq(jettyRepo, Classpaths.typesafeResolver),
  libraryDependencies ++= lift ++ jetty ++ Seq(junit, selenium, spec2, h2, httpComponent)
  ))
}

object Resolvers {
  val jettyRepo = "jetty repo" at "http://siasia.github.com/maven2"
}

object BuildSettings {

  val reallyClean = TaskKey[Unit]("really-clean")
  val blah = TaskKey[Unit]("create-directory")
  var printHello = TaskKey[Unit]("hello")

  val buildSettings: Seq[sbt.Project.Setting[_]] = Defaults.defaultSettings ++ Seq[Setting[_]](
    organization := "com.shopTo",     //TODO these 3 lines are ignored here and picked from build.sbt instead
    version := "1.0.0",
    scalaVersion := "2.9.1",
    retrieveManaged := false,

    printHello <<= (baseDirectory) map {(aVar) => println("just a sample task") },

    reallyClean <<= (baseDirectory) map { (theBase) =>
    print("**** This is really-clean task ****")
    IO.delete((theBase / "target"))
    IO.delete((theBase / "warfile"))
    //    IO.delete(((theBase / "logs") * "*.log").get)
  },

  blah <<= (baseDirectory) map { (theBase) =>
    print("**** This is create-directory task ****")
    IO.createDirectory(theBase / "warfile")
//    zip(theBase / "warfile")
  }
  )
}


object Dependency {

  val liftVersion = "2.4"
  val jettyTestVersion = "6.1.26"
  val jettyVersion = "7.5.4.v20111024"
  val junitVersion = "4.7"
  val seleniumVersion = "2.12.0"
  val spec2Version = "1.6.1"
  val h2Version = "1.2.147"
  val httpclientVersion = "4.0.2"

  val junit = "junit" % "junit" % junitVersion % "test"
  val selenium = "org.seleniumhq.selenium" % "selenium-firefox-driver" % seleniumVersion
  val spec2 = "org.specs2" %% "specs2" % spec2Version % "test"
  val h2 = "com.h2database" % "h2" % h2Version
  val httpComponent = "org.apache.httpcomponents" % "httpclient" % httpclientVersion

  val jetty =
    Seq (
      "org.mortbay.jetty" % "jetty" % jettyTestVersion % "test",
      "org.eclipse.jetty" % "jetty-webapp" % jettyVersion % "container;compile"
    )

  val lift =
  Seq (
    "net.liftweb" %% "lift-webkit" % liftVersion % "compile",
    "net.liftweb" %% "lift-mapper" % liftVersion % "compile"
    )

}
