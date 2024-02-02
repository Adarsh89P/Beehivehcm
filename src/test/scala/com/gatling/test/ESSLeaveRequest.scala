package com.gatling.test

import scala.concurrent.duration._
import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.language.postfixOps

class ESSLeaveRequest extends Simulation {

	val httpProtocol = http
		.baseUrl("https://hrisdemo.beehivehcm.com")

	val leaveScenario = scenario("Leave")
		.exec(http("Load Leave Approval")
			.get("/ess/essmyteam/leave?_=1706089832833"))
		.pause(4)
		.exec(http("Leave Approval Action")
			.post("/LeaveApproval/Approve/53445")
			.formParam("id", "53445")
			.formParam("UAL", "")
			.formParam("isfromindex", "true"))
		.pause(3)
		.exec(http("View Leave Summary after Approval")
			.get("/ESSMyTeam/ViewLeaveSummary?_=1706089878504"))

		.exec(http("Load Leave Cancel")
			.get("/ess/essmyteam/leavecancel?_=1706089890508"))
		.pause(5)
		.exec(http("Leave Cancel Action")
			.post("/LeaveCancellation/Cancel/53440")
			.formParam("id", "53440"))

	setUp(leaveScenario.inject(constantConcurrentUsers(200) during (5 minutes))).protocols(httpProtocol)
}
