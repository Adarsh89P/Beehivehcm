package com.gatling.test

import scala.concurrent.duration._
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

import scala.language.postfixOps

class EmpLEaveViewLoad extends Simulation {

	val httpProtocol = http
		.baseUrl("https://hrisdemo.beehivehcm.com")

	val scn = scenario("EmpLEaveViewLoad")
		.exec(http("Leaveclick")
			.get("/ESS/ESSMe/Leave?_=1706102616567")
			.resources(
            http("View")
			.get("/Areas/ESS/Content/js/ESSMe.Leave.js?_=1706102616990")
		,
            http("Load")
			.get("/Leave/LeaveApplication/GetDataTable?iDisplayStart=0&iDisplayLength=-1&_=1706102617076")))

	//setUp(scn.inject(rampUsers(1000).during(5 minutes))).protocols(httpProtocol)

	setUp(scn.inject(constantConcurrentUsers(20).during(10 minutes))).protocols(httpProtocol) //Total 13k user
}