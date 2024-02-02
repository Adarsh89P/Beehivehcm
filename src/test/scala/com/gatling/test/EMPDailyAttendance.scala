package com.gatling.test

import scala.concurrent.duration._
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

import scala.language.postfixOps

class EMPDailyAttendance extends Simulation {

	val httpProtocol = http
		.baseUrl("https://hrisdemo.beehivehcm.com")

	val scn = scenario("EMPDailyAttendance")
		.exec(http("View")
				.get("/attendance/dailyattendance")
			.resources(http("load")
			.get("/attendance/DailyAttendance/Calendar?employeeid=&pageType=Me&start=1704047400&end=1707676200&_=1706073838544")))
		.pause(3)
		.exec(http("EmpView")
			.post("/DailyAttendance/AttendanceSummaryCount")
			.formParam("employeeid", "21943")
			.formParam("sFromDate", "1/1/2024")
			.formParam("sToDate", "1/2/2024"))

	setUp(scn.inject(constantConcurrentUsers(70).during(10 minutes))).protocols(httpProtocol)
}