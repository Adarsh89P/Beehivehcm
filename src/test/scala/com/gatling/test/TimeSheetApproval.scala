package com.gatling.test

import scala.concurrent.duration._
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

import scala.language.postfixOps

class TimeSheetApproval extends Simulation {

	val httpProtocol = http
		.baseUrl("https://hrisdemo.beehivehcm.com")

	val scn = scenario("TimeSheetApproval")
		.exec(http("Select")
			.post("/TimesheetApproval/Approve/8206")
			.formParam("id", "8206"))
		.pause(1.second)
		.exec(http("Approve")
			.get("/TaskManagement/TimesheetApproval")
			.resources(
				http("request_2").get("/content/ace/assets/font/fontawesome-webfont.woff?v=3.2.1"),
				http("request_3").get("/TimesheetApproval/GetDataTable?sEcho=1&iColumns=9&sColumns=&iDisplayStart=0&iDisplayLength=10&mDataProp_0=TimeSheetProjectID&mDataProp_1=EmployeeCode&mDataProp_2=EmployeeName&mDataProp_3=Project&mDataProp_4=FromDate&mDataProp_5=ToDate&mDataProp_6=Status&mDataProp_7=ApprovTimesheetID&mDataProp_8=RejectTimesheetID&sSearch=&bRegex=false&sSearch_0=&bRegex_0=false&bSearchable_0=true&sSearch_1=&bRegex_1=false&bSearchable_1=true&sSearch_2=&bRegex_2=false&bSearchable_2=true&sSearch_3=&bRegex_3=false&bSearchable_3=true&sSearch_4=&bRegex_4=false&bSearchable_4=true&sSearch_5=&bRegex_5=false&bSearchable_5=true&sSearch_6=&bRegex_6=false&bSearchable_6=true&sSearch_7=&bRegex_7=false&bSearchable_7=true&sSearch_8=&bRegex_8=false&bSearchable_8=true&_=1706011167478")
			))
		.pause(3.seconds)
		.exec(http("Select")
			.post("/TimesheetApproval/Reject/8204")
			.formParam("id", "8204")
			.resources(
				http("Reject").get("/TaskManagement/TimesheetApproval")))
		.pause(7.seconds)
		.exec(http("ApproveAll")
			.post("/TimesheetApproval/ApproveAll")
			.formParam("Ids", "8203,8201,8200,8199,8198,8197,8182,8176,8165,8161,"))
		.pause(1.second)
		.exec(http("request_17")
			.get("/TaskManagement/TimesheetApproval"))

	setUp(scn.inject(constantConcurrentUsers(25).during(10 minute))).protocols(httpProtocol)     //10k
	//setUp(scn.inject(constantConcurrentUsers(20).during(5 minute))).protocols(httpProtocol)
}
