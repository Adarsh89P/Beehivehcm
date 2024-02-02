package com.gatling.test

import scala.concurrent.duration._
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

import scala.language.postfixOps

class HRAdminEmpProfile extends Simulation {

	val httpProtocol = http
		.baseUrl("https://hrisdemo.beehivehcm.com")

	val scn = scenario("HRAdminEmpProfile")
		.exec(http("openEmp")
			.get("/hris/employees")
			.resources
			(http("load")
			.get("/content/ace/assets/font/fontawesome-webfont.woff?v=3.2.1"),
				http("Empload")
			.get("/HRIS/Employees/GetDataTableEmployeeOrgStructureList?sEcho=1&iColumns=12&sColumns=&iDisplayStart=0&iDisplayLength=10&mDataProp_0=EmployeeID&mDataProp_1=EmployeeID&mDataProp_2=EmployeeCode&mDataProp_3=FullName&mDataProp_4=DateofJoining&mDataProp_5=Company&mDataProp_6=Branch&mDataProp_7=Department&mDataProp_8=Designation&mDataProp_9=Location&mDataProp_10=EmployeeID&mDataProp_11=ImageName&sSearch=&bRegex=false&sSearch_0=&bRegex_0=false&bSearchable_0=true&sSearch_1=&bRegex_1=false&bSearchable_1=true&sSearch_2=&bRegex_2=false&bSearchable_2=true&sSearch_3=&bRegex_3=false&bSearchable_3=true&sSearch_4=&bRegex_4=false&bSearchable_4=true&sSearch_5=&bRegex_5=false&bSearchable_5=true&sSearch_6=&bRegex_6=false&bSearchable_6=true&sSearch_7=&bRegex_7=false&bSearchable_7=true&sSearch_8=&bRegex_8=false&bSearchable_8=true&sSearch_9=&bRegex_9=false&bSearchable_9=true&sSearch_10=&bRegex_10=false&bSearchable_10=true&sSearch_11=&bRegex_11=false&bSearchable_11=true&iSortCol_0=0&sSortDir_0=asc&iSortingCols=1&bSortable_0=true&bSortable_1=false&bSortable_2=true&bSortable_3=true&bSortable_4=true&bSortable_5=true&bSortable_6=true&bSortable_7=true&bSortable_8=true&bSortable_9=true&bSortable_10=false&bSortable_11=false&_=1706094528207")
			))
		.pause(2)
		.exec(http("Employeeview")
			.get("/HRIS/Employee/View0/6636"))

	setUp(scn.inject(constantUsersPerSec(10) during (15 minutes))).protocols(httpProtocol)
}