package com.test.rupeek.firstRound.base;

import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.test.rupeek.firstRound.utils.TestContext;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveAuthProvider;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class APIBase {

	public static Properties apiEndPoints;
	static TestContext context;

	static {
		context = new TestContext();
		apiEndPoints = new Properties();
		FileInputStream oApiEndpointsFile = null;
		try {
			oApiEndpointsFile = new FileInputStream(
					"src/main/java/com/test/rupeek/firstRound/configs/apiEndPoints.properties");
			apiEndPoints.load(oApiEndpointsFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		RestAssured.baseURI = apiEndPoints.getProperty("baseUri");
	}

	public RequestSpecification givenConfig() {
		RestAssured.useRelaxedHTTPSValidation();
		return given().header("Content-Type", "application/json");
	}

	public Response post(RequestSpecification req, String endPoint, Object obj) {
		return req.body(obj).when().post(endPoint);
	}

	public RequestSpecification authHeaders(String context) {

		return givenConfig().header("Authorization", " Bearer " + context);
	}

	public Response get(RequestSpecification req, String endPoint) {
		return req.when().get(endPoint);
	}

	public Response get(RequestSpecification req, String endPoint, String mobileNumber) {
		return req.when().basePath(endPoint).get(mobileNumber);
	}

	public String getEndpoint(String endPointName) {
		String endPoint = apiEndPoints.getProperty(endPointName);
		return endPoint;
	}

	public Object getResponseValue(Response response, String path) {
		return response.jsonPath().get(path);
	}

}
