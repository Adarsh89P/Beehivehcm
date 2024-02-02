package com.gatling.test

import scala.concurrent.duration._
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

import scala.language.postfixOps

class ESSMyTeamMultiApplication extends Simulation {

	val httpProtocol = http
		.baseUrl("https://hrisdemo.beehivehcm.com")

	val ESSMyTeamMultiApplication = scenario("ESSMyTeamMultiApplication")
		.exec(http("Load")
			.get("/ExtMenu/GetMenuLevel1IDs?urls=Leave%2FLeaveApproval&_=1706597416446")
			.resources(http("view")
			.get("/Leave/LeaveApproval")))
		.pause(5)
		.exec(http("Leave Aproval")
			.post("/LeaveApproval/ApproveAll")
			.formParam("ids", "53414,52409"))
		.pause(5)
		.exec(http("confirm")
			.get("/Leave/LeaveApproval")
			.resources(http("request_13")
			.get("/content/ace/assets/font/fontawesome-webfont.woff?v=3.2.1")))
		.pause(1)
		.exec(http("Leave Reject")
			.post("/LeaveApproval/Reject/52407")
			.formParam("id", "52407")
			.formParam("UAL", "")
			.formParam("isfromindex", "true")
			.resources(http("request_19")
			.get("/Leave/LeaveApproval")))
		.pause(5)
		.exec(http("request 7")
			.post("/ESSMyTeam/GetAttendanceSummaryData")
			.resources(http("request 8")
			.get("/MyTeam/ViewCalendar?reportees=2%2C5%2C6%2C7%2C8%2C9%2C10%2C11%2C12%2C13%2C&month=1&year=2024&_=1706597520340")))
		.pause(21)
		.exec(http("OutDoor")
			.get("/ExtMenu/GetMenuLevel1IDs?urls=Attendance%2FOutdoorApproval&_=1706597544012")
			.resources(http("load")
			.get("/Attendance/OutdoorApproval"),
            http("select")
			.get("/OutdoorApproval/GetDataTable?sEcho=1&iColumns=10&sColumns=&iDisplayStart=0&iDisplayLength=10&mDataProp_0=OutdoorApplicationID&mDataProp_1=EmployeeCode&mDataProp_2=Employee&mDataProp_3=FromDate&mDataProp_4=ToDate&mDataProp_5=ApplicationDate&mDataProp_6=Remarks&mDataProp_7=Status&mDataProp_8=ApprovOutdoorID&mDataProp_9=RejectOutdoorID&sSearch=&bRegex=false&sSearch_0=&bRegex_0=false&bSearchable_0=true&sSearch_1=&bRegex_1=false&bSearchable_1=true&sSearch_2=&bRegex_2=false&bSearchable_2=true&sSearch_3=&bRegex_3=false&bSearchable_3=true&sSearch_4=&bRegex_4=false&bSearchable_4=true&sSearch_5=&bRegex_5=false&bSearchable_5=true&sSearch_6=&bRegex_6=false&bSearchable_6=true&sSearch_7=&bRegex_7=false&bSearchable_7=true&sSearch_8=&bRegex_8=false&bSearchable_8=true&sSearch_9=&bRegex_9=false&bSearchable_9=true&_=1706597546889"),
            http("select")
			.get("/OutdoorApproval/GetDataTableView?sEcho=1&iColumns=6&sColumns=&iDisplayStart=0&iDisplayLength=10&mDataProp_0=EmployeeCode&mDataProp_1=Employee&mDataProp_2=FromDate&mDataProp_3=ToDate&mDataProp_4=ApplicationDate&mDataProp_5=Status&sSearch=&bRegex=false&sSearch_0=&bRegex_0=false&bSearchable_0=true&sSearch_1=&bRegex_1=false&bSearchable_1=true&sSearch_2=&bRegex_2=false&bSearchable_2=true&sSearch_3=&bRegex_3=false&bSearchable_3=true&sSearch_4=&bRegex_4=false&bSearchable_4=true&sSearch_5=&bRegex_5=false&bSearchable_5=true&_=1706597546909")))
		.pause(16)
		.exec(http("Approve")
			.post("/OutdoorApproval/ApproveAll")
			.formParam("ids", "33243,33240,33239"))
		.pause(4)
		.exec(http("request 14")
			.get("/Attendance/OutdoorApproval")
			.resources(http("Select")
			.get("/content/ace/assets/font/fontawesome-webfont.woff?v=3.2.1")))
		.pause(3)
		.exec(http("request 16")
			.post("/OutdoorApproval/Reject/33232")
			.formParam("id", "33232")
			.resources(http("request 17")
			.get("/Attendance/OutdoorApproval"),
            http("view")
			.get("/OutdoorApproval/GetDataTable?sEcho=1&iColumns=10&sColumns=&iDisplayStart=0&iDisplayLength=10&mDataProp_0=OutdoorApplicationID&mDataProp_1=EmployeeCode&mDataProp_2=Employee&mDataProp_3=FromDate&mDataProp_4=ToDate&mDataProp_5=ApplicationDate&mDataProp_6=Remarks&mDataProp_7=Status&mDataProp_8=ApprovOutdoorID&mDataProp_9=RejectOutdoorID&sSearch=&bRegex=false&sSearch_0=&bRegex_0=false&bSearchable_0=true&sSearch_1=&bRegex_1=false&bSearchable_1=true&sSearch_2=&bRegex_2=false&bSearchable_2=true&sSearch_3=&bRegex_3=false&bSearchable_3=true&sSearch_4=&bRegex_4=false&bSearchable_4=true&sSearch_5=&bRegex_5=false&bSearchable_5=true&sSearch_6=&bRegex_6=false&bSearchable_6=true&sSearch_7=&bRegex_7=false&bSearchable_7=true&sSearch_8=&bRegex_8=false&bSearchable_8=true&sSearch_9=&bRegex_9=false&bSearchable_9=true&_=1706597602748")))
		 .pause(7)
		.exec(http("request 19")
			.post("/ESSMyTeam/GetAttendanceSummaryData")
			.resources(http("request 20")
			.get("/MyTeam/ViewCalendar?reportees=2%2C5%2C6%2C7%2C8%2C9%2C10%2C11%2C12%2C13%2C&month=1&year=2024&_=1706597630934")))
		.pause(5)
		.exec(http("request 21")
			.get("/ExtMenu/GetMenuLevel1IDs?urls=Travel%2FDomesticTravelRequestApproval&_=1706597656051")
			.resources(http("request 22")
			.get("/Travel/DomesticTravelRequestApproval")))
		.pause(5)
		.exec(http("request 23")
			.post("/ESSMyTeam/GetAttendanceSummaryData")
			.resources(http("request_24")
			.get("/MyTeam/ViewCalendar?reportees=2%2C5%2C6%2C7%2C8%2C9%2C10%2C11%2C12%2C13%2C&month=1&year=2024&_=1706597703730")))
		.pause(5)
		.exec(http("request 25")
			.get("/ExtMenu/GetMenuLevel1IDs?urls=Grievances%2FGrievanceList&_=1706597729458")
			.resources(http("request 26")
			.get("/Grievances/GrievanceList"),
            http("request 27")
			.get("/content/ace/assets/font/fontawesome-webfont.woff?v=3.2.1"),
            http("request 28")
			.post("/ExtMenu/GetMenuID")
			.formParam("id", "29")))

	//setUp(ESSMyTeamMultiApplication.inject(atOnceUsers(1))).protocols(httpProtocol)
	setUp(ESSMyTeamMultiApplication.inject(constantConcurrentUsers(50).during(5 minute))).protocols(httpProtocol)
}