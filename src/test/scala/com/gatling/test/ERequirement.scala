package com.gatling.test

import scala.concurrent.duration._
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

import scala.language.postfixOps

class ERequirement extends Simulation {

	val httpProtocol = http
		.baseUrl("https://hrisdemo.beehivehcm.com")

	val scn = scenario("ERequirement")
		.exec(http("View")
			.get("/ATS/Pipeline/View?S=0&id=0&sForm=Joined"))
		.pause(5)
		.exec(http("request_2")
			.get("/ATS/Pipeline/Joined?_=1705993156582"))
		.pause(5)
		.exec(http("pipeline accept")
				.get("/Areas/ATS/Content/js/Pipeline_Accepted.js?_=1705993171348"))

//	setUp(scn.inject(constantConcurrentUsers(15).during(5 minute))).protocols(httpProtocol)
	setUp(scn.inject(rampUsers(3000).during(10 minutes))).protocols(httpProtocol)

}
