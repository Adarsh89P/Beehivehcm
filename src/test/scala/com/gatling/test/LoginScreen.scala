package com.gatling.test

import scala.concurrent.duration._
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._
import org.testng.annotations.Test

import scala.language.postfixOps
@Test
class LoginScreen extends Simulation {

	val httpProtocol = http
		.baseUrl("https://hrisdemo.beehivehcm.com")

    val uri2 = "https://cdn.jsdelivr.net/npm/gridstack@1.1.2/dist/gridstack.min.css"
    val uri3 = "https://netdna.bootstrapcdn.com/font-awesome/3.2.1"

	val scn = scenario("RecordedSimulation")
		.exec(http("request_1")
			.post("/Login/FormLogin")
			.formParam("username", "T00023")
			.formParam("password", "HRMS@123")
			.formParam("CaptchaDeText", "f0ec8a98025c43878b19332f346828e0")
			.formParam("CaptchaInputText", "")
			.formParam("X-Requested-With", "XMLHttpRequest"))


	setUp(
		//scn.inject(rampUsers(20000).during(15 minutes)).protocols(httpProtocol))
	//	scn.inject(constantConcurrentUsers(33).during(10 minutes)).protocols((httpProtocol)))//20k user
scn.inject(constantUsersPerSec(33).during(10 minutes))).protocols(httpProtocol)
}