package com.gatling.test

import scala.concurrent.duration._
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

import scala.language.postfixOps

class EMPweekoff extends Simulation {

	val httpProtocol = http
		.baseUrl("https://hrisdemo.beehivehcm.com")

	val scn = scenario("EMPweekoff")
		.exec(http("load")
			.get("/attendance/shiftrequest")
			.resources(http("view")
			.get("/content/ace/assets/font/fontawesome-webfont.woff?v=3.2.1"),
            http("view")
			.get("/ShiftRequest/GetDataTable?sEcho=1&iColumns=11&sColumns=&iDisplayStart=0&iDisplayLength=10&mDataProp_0=Deleteaccess&mDataProp_1=EmployeeCode&mDataProp_2=Employee&mDataProp_3=Shift&mDataProp_4=InTime&mDataProp_5=OutTime&mDataProp_6=FromDate&mDataProp_7=ToDate&mDataProp_8=Applicationdate&mDataProp_9=Status&mDataProp_10=Deleteaccess&sSearch=&bRegex=false&sSearch_0=&bRegex_0=false&bSearchable_0=true&sSearch_1=&bRegex_1=false&bSearchable_1=true&sSearch_2=&bRegex_2=false&bSearchable_2=true&sSearch_3=&bRegex_3=false&bSearchable_3=true&sSearch_4=&bRegex_4=false&bSearchable_4=true&sSearch_5=&bRegex_5=false&bSearchable_5=true&sSearch_6=&bRegex_6=false&bSearchable_6=true&sSearch_7=&bRegex_7=false&bSearchable_7=true&sSearch_8=&bRegex_8=false&bSearchable_8=true&sSearch_9=&bRegex_9=false&bSearchable_9=true&sSearch_10=&bRegex_10=false&bSearchable_10=true&_=1706506965083")))
		.pause(3)

	setUp(scn.inject(constantConcurrentUsers(800) during (7 minutes))).protocols(httpProtocol)
}