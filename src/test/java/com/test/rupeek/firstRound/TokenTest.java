package com.test.rupeek.firstRound;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.test.rupeek.dataProviders.GenericDataProvider;
import com.test.rupeek.firstRound.base.APIBase;
import com.test.rupeek.firstRound.modals.Token;
import com.test.rupeek.firstRound.modals.TokenBuilder;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TokenTest extends APIBase {
	TokenBuilder tokenBuilder;
	APIBase apiBase;

	@BeforeClass
	public void init() {
		tokenBuilder = new TokenBuilder();
		apiBase = new APIBase();
	}


	@Test(dataProvider = "credentials", dataProviderClass = GenericDataProvider.class)
	public void tokenTest(String userName, String password, String statusCode, ITestContext context) {
		Token token = tokenBuilder.getToken(userName, password);
		String tokenPath = apiBase.getEndpoint("token");
		RequestSpecification req = apiBase.givenConfig();
		apiBase.givenConfig().log().all();
		Response response = apiBase.post(req, tokenPath, token);
		if (response.getStatusCode() == 200) {
			context.setAttribute("token", response.jsonPath().get("token").toString());
		}
		Assert.assertEquals(response.getStatusCode(), Integer.parseUnsignedInt(statusCode));
	}

}
