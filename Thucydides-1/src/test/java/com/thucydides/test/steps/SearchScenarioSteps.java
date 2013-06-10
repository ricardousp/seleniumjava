package com.thucydides.test.steps;


import org.jbehave.core.annotations.AfterStory;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.runner.RunWith;
import net.thucydides.junit.runners.ThucydidesRunner;

import com.selenium.library.Driver;

/* Author - Payal Garg
   StoryName - search_product.story
   Date created - 29-05-2013*/

@RunWith(ThucydidesRunner.class)
public class SearchScenarioSteps {
	
	Driver driver;
	public SearchScenarioSteps() throws Exception{
		driver = new Driver();
	}
	

	@Given("I am a shopper")
	public void ImShopper() {
		System.out.println("welcome");	
	}
	
	@When("I am viewing HomePage")
	public void viewHomePage() throws Exception{
		driver.openURL("http://appliancesonline.com.au");
		driver.maximise();
	}
	
	@Then("I search for <product>")
	public void searchProduct(@Named("product") String product) {
			System.out.println("looking for product " + product);
			
	}	
	
	
	@Then("I enter <product> in search box")
	public void searchForProduct(@Named("product") String product) throws Exception {
		try {
			driver.search("Home_search_textbox",product);
			} catch (Exception e) {
				e.printStackTrace();
			}	
		}
	
	@Then("I click search button")
	public void clickSearchButton() throws Exception{
		try{
			
			driver.click("Home_search_button");
			driver.sync();
		}catch (Exception e) {
			e.printStackTrace();
			}	
		}

	@AfterStory
	public void afterStory(){
		driver.closeOpenedDriver();
	}
	

}
