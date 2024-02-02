package com.gatling.test

import scala.concurrent.duration._
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

import scala.language.postfixOps

class ESSHRDashboard extends Simulation {

	val httpProtocol = http
		.baseUrl("https://hrisdemo.beehivehcm.com")

	val HRDashboard = scenario("ESSHRDashboard")
		.exec(http("Load")
			.get("/ExtMenu/GetMenuLevel1IDs?urls=ESS%2FESSMyTeam&_=1706876527193"))
		.pause(6)
		.exec(http("Verify")
			.post("/ESSMyTeam/GetAttendanceSummaryData"))


	 //setUp(HRDashboard.inject(atOnceUsers(1))).protocols(httpProtocol)
   setUp(HRDashboard.inject(constantUsersPerSec(17).during(10 minutes))).protocols(httpProtocol)



}