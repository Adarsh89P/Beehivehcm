package com.gatling.test

import scala.concurrent.duration._
import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.language.postfixOps

class NotificationApprovalAndReject extends Simulation {

	val httpProtocol = http
		.baseUrl("https://hrisdemo.beehivehcm.com")

	val scn = scenario("NotificationApprovalAndReject")
		.exec(http("Load & View Leave Approval")
			.get("/Leave/LeaveApproval/View2/51333?_=1706013336541"))

		.pause(10.seconds)
		.exec(http("Approve Leave Application")
			.post("/LeaveApproval/Approve/51333")
			.formParam("id", "51333")
			.formParam("notes", "Approve")
			.formParam("UAL", "")
			.formParam("isfromindex", "false"))

		.pause(4.seconds)
		.exec(http("Update Notification Status - Approval")
			.post("/Notification/UpdateStatus")
			.formParam("id", "238924")
			.formParam("status", "History"))

		.pause(2.seconds)
		.exec(http("Load & View Leave Approval")
			.get("/Leave/LeaveApproval/View2/51332?_=1706013369475"))

		.pause(5.seconds)
		.exec(http("Reject Leave Application")
			.post("/LeaveApproval/Reject/51332")
			.formParam("id", "51332")
			.formParam("notes", "Reject"))

		.pause(3.seconds)
		.exec(http("Update Notification Status - Rejection")
			.post("/Notification/UpdateStatus")
			.formParam("id", "238923")
			.formParam("status", "History"))

		.pause(3.seconds)
		.exec(http("Load & View Leave Approval")
			.get("/Leave/LeaveApproval/View2/46194?_=1706013386335"))

	//setUp(scn.inject(heavisideUsers(700) during (6 minutes))).protocols(httpProtocol)
	setUp(scn.inject(constantConcurrentUsers(100).during(10 minute))).protocols(httpProtocol)

}
