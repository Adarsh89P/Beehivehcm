package com.gatling.test

import scala.concurrent.duration._
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

import scala.language.postfixOps

class AdminShiftRequest extends Simulation {

	val httpProtocol = http
		.baseUrl("https://hrisdemo.beehivehcm.com")

	val ShiftRequest = scenario("ShiftRequest")
		.exec(http("Approve")
			.post("/ShiftRequestApproval/Approve/9138")
			.formParam("id", "9138"))
		.pause(10)
		.exec(http("Reject")
			.post("/ShiftRequestApproval/Reject/9135")
			.formParam("id", "9135"))

	setUp(ShiftRequest.inject(rampUsers(2000).during(10 minutes))).protocols(httpProtocol)
}