package com.gatling.test

import scala.concurrent.duration._
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

import scala.language.postfixOps

class AdminCompoffApplication extends Simulation {

	val httpProtocol = http
		.baseUrl("https://hrisdemo.beehivehcm.com")


	val cmpoff = scenario("Approve and reject Compoff")
		.exec(http("Approve")
			.post("/CompOffApproval/Approve/11206")
			.formParam("id", "11206"))

		.pause(12)
		.exec(http("reject")
			.post("/CompOffApproval/Reject/11171")
			.formParam("id", "11171"))

	setUp(cmpoff.inject(constantConcurrentUsers(500).during(5 minute))).protocols(httpProtocol)//500*5=2500 per request
}