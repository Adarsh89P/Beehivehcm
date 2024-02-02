package com.gatling.test

import scala.concurrent.duration._
import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.language.postfixOps

class QALeaveBalance extends Simulation {

	val httpProtocol = http
		.baseUrl("https://hrisdemo.beehivehcm.com")

	val scn = scenario("QALeaveBalance")
		.exec(http("Load Leave Balance Page")
			.get("/leave/leavebalance"))

		.pause(6.seconds)
		.exec(http("Get Leave Balance")
			.get("/LeaveBalance/GetLeaveBalance?employeeid=5&_=1706015474958"))

		.pause(1.second)
		.exec(http("Get Opening Leave Balance")
			.get("/LeaveBalance/GetOpeningLeaveBalance?employeeid=5&leavetypeid=3029&LeavePeriodID=30&_=1706015477201"))

		.exec(http("Get Leave Balance History")
			.get("/LeaveBalance/GetLeaveBalanceHistory?employeeid=5&leavetypeid=3029&leavePeriodID=30&_=1706015477276"))

	setUp(scn.inject(rampUsers(10000).during(10 minutes))).protocols(httpProtocol)
}
