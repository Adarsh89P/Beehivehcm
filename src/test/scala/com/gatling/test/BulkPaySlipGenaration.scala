package com.gatling.test

import scala.concurrent.duration._
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

import scala.language.postfixOps

class BulkPaySlipGenaration extends Simulation {

	val httpProtocol = http
		.baseUrl("https://hrisdemo.beehivehcm.com")

	val PaySlip = scenario("BulkPaySlipGenaration")
		.exec(http("select")
			.post("/BulkSalarySlip/GenerateBulkzipSalarySlip")
			.formParam("company", "3")
			.formParam("division", "3,4,5,7,9,10,11,12,13,14,15,16,17")
			.formParam("zone", "1,2,3,4,6,9,1011,1012")
			.formParam("branch", "6,7,8,9,10,11,12,13,15,16,17,18,19,27,28")
			.formParam("worklocation", "7,8,9,10,11,12,13,14,15,17,18,19,20,21,22,23")
			.formParam("department", "10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50")
			.formParam("CriteriaType", "org")
			.formParam("FinYear", "2019-2020")
			.formParam("Month", "2")
			.formParam("employeeStatus", "Both")
			.formParam("EmployeeList", ""))
		.pause(5)
		.exec(http("Generate")
			.get("/payroll/bulksalaryslip")
			.resources(http("request_2")
			.get("/OrgStructure?controllerName=BulkSalarySlip&_=1705989807750")))

	//setUp(PaySlip.inject(constantConcurrentUsers(30).during(10 minute))).protocols(httpProtocol) //in minute 30 user per minute will heeting all requests
setUp(PaySlip.inject(constantUsersPerSec(10).during(10 minutes))).protocols(httpProtocol)

}