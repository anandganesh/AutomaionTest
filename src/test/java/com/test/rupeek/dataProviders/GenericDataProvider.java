package com.test.rupeek.dataProviders;

import org.testng.ITestContext;
import org.testng.annotations.DataProvider;

public class GenericDataProvider {

	@DataProvider(name = "credentials")
	public Object credentialProvider() {
		Object[][] obj = { { "rupeek", "password", "200" }, { "abcd", "test", "401" } };
		return obj;
	}

	@DataProvider(name = "tokenProvider")
	public Object credentialProvider(ITestContext context) {
		Object[][] obj = { { context.getAttribute("token").toString(), "200" }, { "sfdasdfweasdfafdaf", "401" } };
		return obj;
	}

	@DataProvider(name = "mobileNumberProvider")
	public Object mobileNumberProvider(ITestContext context) {
		Object[][] obj = { { context.getAttribute("token").toString(), "8037602400", "Aliko", "200" },
				{ "sfdasdfweasdfafdaf", "9876543211", "", "401" } };
		return obj;
	}
}
