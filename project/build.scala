import sbt._
object MyApp extends Build
{
	lazy val root =
<<<<<<< HEAD
		Project("", file(".")) dependsOn(dispatchLiftJson) dependsOn(xsbtStart)
	lazy val dispatchLiftJson =
		uri("git://github.com/dispatch/dispatch-lift-json")
	lazy val xsbtStart =
		uri("git://github.com/typesafehub/xsbt-start-script-plugin.git")
=======
		Project("", file(".")) dependsOn(dispatchLiftJson) dependsOn(redis)
	lazy val dispatchLiftJson =
		uri("git://github.com/dispatch/dispatch-lift-json")
	lazy val redis = uri("git://github.com/icambridge/scala-redis.git")

>>>>>>> 3366d8a4aaf0adcf885195ec334ba86d7af34cc7
}