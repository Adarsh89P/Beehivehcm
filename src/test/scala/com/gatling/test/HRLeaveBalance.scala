package com.gatling.test

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class HRLeaveBalance extends Simulation {

	val httpProtocol = http
		.baseUrl("https://hrisdemo.beehivehcm.com")

	val scn = scenario("HRLeaveBalance")
		.exec(http("Load Leave Balance Page")
			.get("/leave/leavebalance"))
		.pause(5.seconds)
		.exec(http("Get Leave Balance")
			.get("/LeaveBalance/GetLeaveBalance?employeeid=21952&_=1706015319316"))

		.exec(http("Get Unread Notification List")
			.get("/Notification/GetUnreadList?_=1706015325125"))

	setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}
