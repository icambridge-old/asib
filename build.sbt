import com.typesafe.startscript.StartScriptPlugin

seq(StartScriptPlugin.startScriptForClassesSettings: _*)

name              := "asib"

version           := "0.1"

mainClass         := Some("asib.Asib")

parallelExecution := false

scalaVersion      := "2.9.1"

libraryDependencies ~= { seq =>
  val vers = "0.8.7"
  seq ++ Seq(
    "net.databinder" %% "dispatch-core" % vers,
    "net.databinder" %% "dispatch-oauth" % vers,
    "net.databinder" %% "dispatch-nio" % vers,
    /* Twine doesn't need the below dependencies, but it simplifies
     * the Dispatch tutorials to keep it here for now. */
    "net.databinder" %% "dispatch-http" % vers,
    "net.databinder" %% "dispatch-tagsoup" % vers,
    "net.databinder" %% "dispatch-jsoup" % vers
  )
}

resolvers += "Guardian Github Releases" at "http://guardian.github.com/maven/repo-releases"

libraryDependencies += "com.gu.openplatform" %% "content-api-client" % "1.13"

libraryDependencies +=  "org.scalatest" %% "scalatest" % "1.6.1" % "test"

