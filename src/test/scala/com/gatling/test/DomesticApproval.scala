package com.gatling.test

import scala.concurrent.duration._
import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.language.postfixOps

class DomesticApproval extends Simulation {

	val httpProtocol = http
		.baseUrl("https://hrisdemo.beehivehcm.com")

	val scn = scenario("DomesticApproval")
		.exec(http("request_0")
			.get("/Travel/AdminDomesticTravelRequestApproval/View/24969")
			.resources(
				http("request_2").post("/TravelDesk/GetProjectDetail").formParam("ProjectID", "8196")
					.check(status.is(200))))
		.exec(http("request_6")
			.get("/Travel/AdminDomesticTravelRequestApproval/View/24969")
			.resources(
				http("request_7").get("/content/ace/assets/font/fontawesome-webfont.woff?v=3.2.1"),
				http("request_8").post("/TravelDesk/GetProjectDetail").formParam("ProjectID", "8196")))

	setUp(scn.inject(constantConcurrentUsers(500).during(8 minute))).protocols(httpProtocol)  //500 user*8= 4000 user total in each request within 8 minutes
}
