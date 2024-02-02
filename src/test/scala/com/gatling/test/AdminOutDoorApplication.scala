package com.gatling.test

import scala.concurrent.duration._
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

import scala.language.postfixOps

class AdminOutDoorApplication extends Simulation {

	val httpProtocol = http
		.baseUrl("https://hrisdemo.beehivehcm.com")

	val OutDoor = scenario("OutDoorApplication")
		.exec(http("Approve")
			.post("/OutdoorApproval/Approve/33244")
			.formParam("id", "33244"))
		.pause(14)
		.exec(http("Reject")
			.post("/OutdoorApproval/Reject/33242")
			.formParam("id", "33242"))

	setUp(OutDoor.inject(constantConcurrentUsers(400).during(8 minute))).protocols(httpProtocol)
}