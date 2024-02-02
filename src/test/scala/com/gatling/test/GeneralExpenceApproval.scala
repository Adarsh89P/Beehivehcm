package com.gatling.test

import scala.concurrent.duration._
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

import scala.language.postfixOps

class GeneralExpenceApproval extends Simulation {

	val httpProtocol = http
		.baseUrl("https://hrisdemo.beehivehcm.com")

	val scn = scenario("GeneralExpenceApproval")
		.exec(http("Load")
			.get("/Expense/GeneralExpReqApproval/View/27909")
			.resources(http("View")
			.get("/content/ace/assets/font/fontawesome-webfont.woff?v=3.2.1")))
		.pause(7)
		.exec(http("load_Emp")
			.post("/GeneralExpReqApproval/GetExpenseData")
			.formParam("id", "27909"))
		.pause(10)
		.exec(http("load")
			.post("/GeneralExpReqApproval/Approve/27909")
			.formParam("id", "27909")
			.formParam("ApprovedAmount", "-3000")
			.formParam("ApprovedindividualAmount", "31196~125$")
			.formParam("IsHardCopySubmitted", "false")
			.formParam("notes", "Approve"))
		.pause(1)
		.exec(http("load")
			.get("/Expense/GeneralExpReqApproval")
			.resources(http("load after approval")
			.get("/content/ace/assets/font/fontawesome-webfont.woff?v=3.2.1")))
		.pause(3)


	setUp(scn.inject(constantUsersPerSec(10) during (1 minutes))).protocols(httpProtocol)
}