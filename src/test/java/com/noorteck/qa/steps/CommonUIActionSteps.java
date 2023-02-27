package com.noorteck.qa.steps;

import static com.noorteck.qa.setup.ObjInitialize.getInstance;

import java.util.List;
import java.util.Map;

import com.github.javafaker.Faker;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CommonUIActionSteps {

	@When(".* enterValue \"(.*)\" \"(.*)\"$")
	public void enterValue(String objectKey, String objectValue) {
		getInstance().getCommonUIInstance().enter(objectKey, objectValue);
	}

	@When(".* clickElement \"(.*)\"$")
	public void clickElement(String objectKey) throws InterruptedException {

		getInstance().getCommonUIInstance().click(objectKey);

	}

	@When(".* selectDropDown \"(.*)\" \"(.*)\" \"(.*)\"$")
	public void selectDropDown(String objectName, String Value, String method) throws Exception {
		getInstance().getCommonUIInstance().selectDropdownOption(objectName, Value, method);
	}

	@When(".* enterRandomEmail \"(.*)\"$")
	public void enterRandomEmail(String objectKey) throws InterruptedException {
		Faker fa = new Faker();
		String objectValue = fa.name().firstName() + "@Yahoo.com";

		getInstance().getCommonUIInstance().enter(objectKey, objectValue);
	}

	@When("^User switches to another menu page$")
	public void clickWithDataTable(io.cucumber.datatable.DataTable dataTable) {
		List<Map<String, String>> expListMap = dataTable.asMaps();
		for (Map<String, String> map : expListMap) {
			String objectKey = map.get("objectKey");
			getInstance().getCommonUIInstance().click(objectKey);

		}
	}
	
}