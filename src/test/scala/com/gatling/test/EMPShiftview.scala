package com.gatling.test

import scala.concurrent.duration._
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

import scala.language.postfixOps

class EMPShiftview extends Simulation {

	val httpProtocol = http
		.baseUrl("https://hrisdemo.beehivehcm.com")

	val ShiftAndWeekoff = scenario("EMPShiftandweekoff")
		.exec(http("load")
			.get("/attendance/shiftrequest")
			.resources(http("view")
			.get("/content/ace/assets/font/fontawesome-webfont.woff?v=3.2.1")))
		.pause(20)
	setUp(ShiftAndWeekoff.inject(constantConcurrentUsers(800) during (7 minutes))).protocols(httpProtocol)
}