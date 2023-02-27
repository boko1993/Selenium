package com.noorteck.qa.steps;

import static com.noorteck.qa.setup.ObjInitialize.getInstance;

import java.util.List;
import java.util.Map;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;

public class CommonUIVerifySteps {

	@Then(".* elementDisplayed \"(.*)\"$")
	public void elementDisplayed(String objectKey) {

		boolean isDisplayed = getInstance().getCommonUIInstance().isElementDisplayed(objectKey);

		if (!isDisplayed) {

			getInstance().getCommonUIInstance().takeScreenshot();
			getInstance().getSoftAssert().fail(objectKey + "' is not displayed");

		}
	}

	@Then(".* verifyMessage \"(.*)\" \"(.*)\"$")
	public void verifyMessage(String objectKey, String expErroMessage) throws InterruptedException {
		String actErroMessage = getInstance().getCommonUIInstance().getText(objectKey);
		Thread.sleep(2000);
		boolean isErrorMatched = actErroMessage.equals(expErroMessage);

		if (!isErrorMatched) {
			getInstance().getCommonUIInstance().takeScreenshot();
			getInstance().getSoftAssert().fail(
					"Error Message mismatch. Expected: '" + expErroMessage + "'. Actual: '" + actErroMessage + "'");
		}
	}

	@Then(".* verifyPageTitle \"(.*)\"$")
	public void verifyPageTitle(String expPageTitle) {
		String actPageTitle = getInstance().getCommonUIInstance().getTitle();
		boolean isTitleMatched = actPageTitle.equals(expPageTitle);

		if (!isTitleMatched) {
			getInstance().getCommonUIInstance().takeScreenshot();
			getInstance().getSoftAssert()
					.fail("Page title mismatch. Expected: '" + expPageTitle + "'. Actual: '" + actPageTitle + "'");
		}
	}

	@Then(".* successMessageVerify \"(.*)\" \"(.*)\"$")
	public void successMessageVerify(String objectKey, String expSuccessMessage) {
		String actsuccessMessage = getInstance().getCommonUIInstance().getText(objectKey);
		boolean isSuccessMessageMatched = actsuccessMessage.equals(expSuccessMessage);

		if (!isSuccessMessageMatched) {
			getInstance().getCommonUIInstance().takeScreenshot();
			getInstance().getSoftAssert().fail("Success Message mismatch. Expected: '" + expSuccessMessage
					+ "'. Actual: '" + actsuccessMessage + "'");

		}
	}

	@Then("^user verifies that following elements are visible$")
	public void verifyWithDataTable(io.cucumber.datatable.DataTable dataTable) {
		List<Map<String, String>> expListMap = dataTable.asMaps();
		for (Map<String, String> map : expListMap) {
			String objectKey = map.get("objectKey");
			boolean isDisplayed = getInstance().getCommonUIInstance().isElementDisplayed(objectKey);
			if (!isDisplayed) {
				getInstance().getCommonUIInstance().takeScreenshot();
				getInstance().getSoftAssert().fail(objectKey + "' is not displayed");
			}
		}
	}

	@Then("^user verifies that following elements texts are visible$")
	public void verifyTextWithDataTableT(DataTable dataTable) {
		List<Map<String, String>> data = dataTable.asMaps();
		for (Map<String, String> map : data) {
			String objectKey = map.get("objectKey");
			String expMessage = map.get("expMessage");
			String actMessage = getInstance().getCommonUIInstance().getText(objectKey);
			boolean isMessagedMatched = actMessage.equals(expMessage);
			if (!isMessagedMatched) {
				getInstance().getCommonUIInstance().takeScreenshot();
				getInstance().getSoftAssert()
						.fail("Message mismatch. Expected: '" + expMessage + "'. Actual: '" + actMessage + "'");
			}
		}
	}

}
