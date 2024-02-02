package com.gatling.test

import scala.concurrent.duration._
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

import scala.language.postfixOps

class ProfileSection extends Simulation {

	val httpProtocol = http
		.baseUrl("https://hrisdemo.beehivehcm.com")

	val ProfileSection = scenario("ProfileSection")
		.exec(http("load")
			.get("/ExtMenu/GetMenuLevel1IDs?urls=HRIS%2FEmployee%2FView0%2F21942&_=1705986473556")
			.resources(http("view")
			.get("/HRIS/Employee/View0/21942")))
		.pause(9)
		.exec(http("EmpView")
			.get("/HRIS/Employee/View/21942"))
		.pause(10)
		.exec(http("edit")
			.post("/Employee/UpdateByField")
			.formParam("name", "EmployeeCurrentContactState_ID")
			.formParam("value", "12")
			.formParam("pk", "22900")
			.formParam("empid", "21942")
			.formParam("type", "EmployeeCurrentContact"))
		.pause(7)
		.exec(http("Refresh")
			.post("/ExtMenu/GetMenuID")
			.formParam("id", "5"))

	setUp(ProfileSection.inject(constantConcurrentUsers(50).during(10 minutes))).protocols(httpProtocol)
}