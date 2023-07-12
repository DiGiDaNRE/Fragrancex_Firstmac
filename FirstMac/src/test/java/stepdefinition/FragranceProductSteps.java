package stepdefinition;

import io.cucumber.java.en.*;
import pageobjects.HomePage;
import pageobjects.ProductPage;
import utility.Commands;

public class FragranceProductSteps extends Commands {

	HomePage homePage = new HomePage();
	ProductPage productPage = new ProductPage();
	
	@Given("User access {string} website")
	public void user_access_website(String url) {
		accessURL(url);
		verifyElement(homePage.FragranceXTitle);
	}

	@When("In homepage direct to TOP PICKS FOR YOU category to select Third product listed")
	public void in_homepage_direct_to_top_picks_for_you_category_to_select_third_product_listed() {
		getListofProducts(homePage.TopPicksForYou);
	}
		
	@Then("Select second variant proceed with checkout")
	public void select_second_variant_proceed_with_checkout() {
		getListofVariants(productPage.PerfumeVariants);
	}
	
	@Then("Validate quantity is {int}")
	public void validate_quantity_is(Integer Quantity){
		getQuantitylist();
		validateCheckOutCartCount(Quantity);
	}
	
	@Then("Changes quantity to {int}")
	public void changes_quantity_to(Integer Quantity){
		clickElement(productPage.DropDownQuantity);
		validateCheckOutCartCount(Quantity);
	}
}
