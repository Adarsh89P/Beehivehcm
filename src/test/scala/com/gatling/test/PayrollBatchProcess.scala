package com.gatling.test

import scala.concurrent.duration._
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

import scala.language.postfixOps

class PayrollBatchProcess extends Simulation {

	val httpProtocol = http
		.baseUrl("https://hrisdemo.beehivehcm.com")

	val commonRequests = repeat(1) { // Repeat the common batch status requests
		exec(http("GetBatchStatus")
			.post("/PayrollBatchProcess/GetBatchStatus")
			.formParam("Batchno", "test12 BSSPL"))
	}

	val scn = scenario("PayrollBatchProcess")
		.exec(http("GetPendingChecker")
			.post("/PayrollBatchProcess/GetPendingChecker")
			.formParam("BatchID", "4774"))
		.exec(http("ProcessBatch")
			.post("/PayrollBatchProcess/ProcessBatch")
			.formParam("BatchID", "4774"))
		.exec(commonRequests)
		.pause(59)
		.exec(commonRequests)

	setUp(scn.inject(rampUsers(2000).during(10 minutes))).protocols(httpProtocol)
}
