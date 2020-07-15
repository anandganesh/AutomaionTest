package com.test.rupeek.firstRound;

import java.util.List;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.test.rupeek.dataProviders.GenericDataProvider;
import com.test.rupeek.firstRound.base.APIBase;
import com.test.rupeek.firstRound.modals.UserDetails;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GetUserDetailsTest extends APIBase {

	APIBase apiBase;
	TokenTest token;


	@BeforeClass
	public void init() {

		apiBase = new APIBase();
		token = new TokenTest();
	}

	@Test(dataProvider = "tokenProvider", dataProviderClass = GenericDataProvider.class)
	public void getUserDetails(String token, String statusCode) {
		Response response = apiBase.get(apiBase.authHeaders(token), apiBase.getEndpoint("getUserDetails"));
		Assert.assertEquals(response.getStatusCode(), Integer.parseUnsignedInt(statusCode));
		if (response.getStatusCode() == 200) {
			List<Object> fNames = JsonPath.from(UserDetails.expectedResult).get("first_name");
			List<Object> resonseName = response.jsonPath().get("first_name");
			Assert.assertEquals(fNames.size(), resonseName.size());
			resonseName.removeAll(fNames);
			Assert.assertEquals(resonseName.size(), 0);
		}
	}
}
