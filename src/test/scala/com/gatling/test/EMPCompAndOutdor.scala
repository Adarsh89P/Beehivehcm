package com.gatling.test

import scala.concurrent.duration._
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

import scala.language.postfixOps

class EMPCompAndOutdor extends Simulation {

	val httpProtocol = http
		.baseUrl("https://hrisdemo.beehivehcm.com")

	val loadview = scenario("EmployeeCompensationAndOutdoorScenario")
		.exec(http("Outdoor Application Request")
			.get("/attendance/outdoorapplication")
			.resources(http("Outdoor Application DataTable Request")
				.get("/OutdoorApplication/GetDataTable?sEcho=1&iColumns=8&sColumns=&iDisplayStart=0&iDisplayLength=10&mDataProp_0=Deleteaccess&mDataProp_1=EmployeeCode&mDataProp_2=Employee&mDataProp_3=FromDate&mDataProp_4=ToDate&mDataProp_5=ApplicationDate&mDataProp_6=Status&mDataProp_7=Deleteaccess&sSearch=&bRegex=false&sSearch_0=&bRegex_0=false&bSearchable_0=true&sSearch_1=&bRegex_1=false&bSearchable_1=true&sSearch_2=&bRegex_2=false&bSearchable_2=true&sSearch_3=&bRegex_3=false&bSearchable_3=true&sSearch_4=&bRegex_4=false&bSearchable_4=true&sSearch_5=&bRegex_5=false&bSearchable_5=true&sSearch_6=&bRegex_6=false&bSearchable_6=true&sSearch_7=&bRegex_7=false&bSearchable_7=true&_=1706075094850")))
		.pause(4)
		.exec(http("CompOff Application Request")
			.get("/attendance/compoffapplication")
			.resources(http("Compensation Application DataTable Request")
				.get("/CompOffApplication/GetDataTable?sEcho=1&iColumns=13&sColumns=&iDisplayStart=0&iDisplayLength=10&mDataProp_0=Deleteaccess&mDataProp_1=EmployeeCode&mDataProp_2=EmployeeName&mDataProp_3=InDate&mDataProp_4=InTime&mDataProp_5=OutTime&mDataProp_6=WorkingHours&mDataProp_7=CompOffAganist&mDataProp_8=CompOffDate&mDataProp_9=ApplicationDate&mDataProp_10=Status&mDataProp_11=Deleteaccess&mDataProp_12=CompOffApplicationCancelID&sSearch=&bRegex=false&sSearch_0=&bRegex_0=false&bSearchable_0=true&sSearch_1=&bRegex_1=false&bSearchable_1=true&sSearch_2=&bRegex_2=false&bSearchable_2=true&sSearch_3=&bRegex_3=false&bSearchable_3=true&sSearch_4=&bRegex_4=false&bSearchable_4=true&sSearch_5=&bRegex_5=false&bSearchable_5=true&sSearch_6=&bRegex_6=false&bSearchable_6=true&sSearch_7=&bRegex_7=false&bSearchable_7=true&sSearch_8=&bRegex_8=false&bSearchable_8=true&sSearch_9=&bRegex_9=false&bSearchable_9=true&sSearch_10=&bRegex_10=false&bSearchable_10=true&sSearch_11=&bRegex_11=false&bSearchable_11=true&sSearch_12=&bRegex_12=false&bSearchable_12=true&_=1706075104722")))


	setUp(loadview.inject(rampUsers(3000).during(10 minutes))).protocols(httpProtocol) //3000k user heating same request even distributed 10 minutes
}
