package com.gatling.test

import scala.concurrent.duration._
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

import scala.language.postfixOps

class AdminLeaverejectApprove extends Simulation {

	val httpProtocol = http
		.baseUrl("https://hrisdemo.beehivehcm.com")


	val ESSTeam = scenario("EssMyTeamrejectApprove")
		.exec(http("leave Approve")
			.post("/LeaveApproval/ApproveAll")
			.formParam("ids", "53415,52411,52410"))
		.pause(2)
		.exec(http("Approve Succses")
			.get("/Leave/LeaveApproval")
			.resources(http("Check Teble")
			.get("/LeaveApproval/GetDataTable?sEcho=1&iColumns=13&sColumns=&iDisplayStart=0&iDisplayLength=10&mDataProp_0=LeaveApplicationID&mDataProp_1=EmployeeCode&mDataProp_2=Employee&mDataProp_3=LeaveType&mDataProp_4=FromDate&mDataProp_5=ToDate&mDataProp_6=LeaveDuration&mDataProp_7=ApplicationDate&mDataProp_8=LeaveAvailed&mDataProp_9=Remarks&mDataProp_10=Status&mDataProp_11=ApprovLeaveID&mDataProp_12=RejectLeaveID&sSearch=&bRegex=false&sSearch_0=&bRegex_0=false&bSearchable_0=true&sSearch_1=&bRegex_1=false&bSearchable_1=true&sSearch_2=&bRegex_2=false&bSearchable_2=true&sSearch_3=&bRegex_3=false&bSearchable_3=true&sSearch_4=&bRegex_4=false&bSearchable_4=true&sSearch_5=&bRegex_5=false&bSearchable_5=true&sSearch_6=&bRegex_6=false&bSearchable_6=true&sSearch_7=&bRegex_7=false&bSearchable_7=true&sSearch_8=&bRegex_8=false&bSearchable_8=true&sSearch_9=&bRegex_9=false&bSearchable_9=true&sSearch_10=&bRegex_10=false&bSearchable_10=true&sSearch_11=&bRegex_11=false&bSearchable_11=true&sSearch_12=&bRegex_12=false&bSearchable_12=true&_=1706160635112")))
		.pause(10)
		.exec(http("Leave Reject")
			.post("/LeaveApproval/Reject/52413")
			.formParam("id", "52413")
			.formParam("UAL", "")
			.formParam("isfromindex", "true"))


	setUp(ESSTeam.inject(constantConcurrentUsers(500).during(8 minutes))).protocols(httpProtocol)
	//setUp(ESSTeam.inject(constantUsersPerSec(7).during(10 minutes))).protocols(httpProtocol)

}