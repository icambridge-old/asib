import sbt._
object MyApp extends Build
{
	lazy val root =

		Project("", file(".")) dependsOn(dispatchLiftJson) dependsOn(redis)
	lazy val dispatchLiftJson =
		uri("git://github.com/dispatch/dispatch-lift-json")
	lazy val redis = uri("git://github.com/icambridge/scala-redis.git")

}