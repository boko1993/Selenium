package com.noorteck.qa.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class DemoSteps {

	@Given(".* enterBatch8 \"(.*)\" \"(.*)\"$")
	public void enterText(String string, String string2) {
		System.out.println(string);
		System.out.println(string2);

	}

	@When(".* clickBatch8 \"(.*)\"$")
	public void click(String string) {
		System.out.println(string);

	}

}
