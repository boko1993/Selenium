package com.noorteck.qa.steps;

import static com.noorteck.qa.setup.ObjInitialize.getInstance;


import io.cucumber.java.en.Given;

public class CommonSetUpSteps {

	@Given(".* loginToApp \"(.*)\" \"(.*)\"$")
	public void loginToApp(String browser, String region) throws Exception {
		getInstance().getEnvInstance().setEnvironment(region);
		getInstance().getCommonUIInstance().openBrowser(browser);
		getInstance().getLoginPageInstance().login();
	}

	@Given(".* navigateToApp \"(.*)\" \"(.*)\"$")
	public void navigateToApp(String browser, String region) throws Exception {

		getInstance().getEnvInstance().setEnvironment(region);
		getInstance().getCommonUIInstance().openBrowser(browser);
		String url = getInstance().getEnvInstance().getEnvData("url");
		getInstance().getCommonUIInstance().navigateTo(url);

	}

	


}
