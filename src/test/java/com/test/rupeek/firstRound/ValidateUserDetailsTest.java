package com.test.rupeek.firstRound;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.test.rupeek.dataProviders.GenericDataProvider;
import com.test.rupeek.firstRound.base.APIBase;

import io.restassured.response.Response;

public class ValidateUserDetailsTest {

	APIBase apiBase;
	TokenTest token;

	@BeforeClass
	public void init() {

		apiBase = new APIBase();
		token = new TokenTest();
	}

	@Test(dataProvider = "mobileNumberProvider", dataProviderClass = GenericDataProvider.class)
	public void validateUserDetails(String token, String mobileNumber, String name, String statusCode) {
		Response response = apiBase.get(apiBase.authHeaders(token), apiBase.getEndpoint("getUserDetails"),
				mobileNumber);
		Assert.assertEquals(response.getStatusCode(), Integer.parseUnsignedInt(statusCode));
		if (response.getStatusCode() == 200) {
			String resonseName = response.jsonPath().get("first_name");
			Assert.assertEquals(resonseName.equals(name), true);

		}
	}

}
