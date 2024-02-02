package com.gatling.test

import scala.concurrent.duration._
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

import scala.language.postfixOps

class EmployeeLeaveBalance extends Simulation {

	val httpProtocol = http
		.baseUrl("https://hrisdemo.beehivehcm.com")


	val scn = scenario("EmployeeLeaveBalance")
		.exec(http("request_0")
			.get("/leave/leavebalance")
			.resources(http("request_1")
			.get("/content/ace/assets/font/fontawesome-webfont.woff?v=3.2.1")))
		.pause(7)
		.exec(http("request_2")
			.get("/LeaveBalance/GetLeaveBalance?employeeid=21942&_=1706015068552"))
		.pause(4)
	setUp(scn.inject(rampUsers(1000).during(10 minutes))).protocols(httpProtocol)
}