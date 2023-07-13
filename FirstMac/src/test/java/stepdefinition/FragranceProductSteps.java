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

	@When("In homepage direct to TOP PICKS FOR YOU category")
	public void in_homepage_direct_to_top_picks_for_you_category() {
		getListofProducts(homePage.TopPicksForYou);
	}
	
	@Then("select the {int} rd product listed")
	public void select_the_rd_product_listed(Integer inputVal) {
		getSelectedProduct(inputVal);
	}
	
	@Then("Select {int} nd Variant proceed with checkout")
	public void select_nd_variant_proceed_with_checkout(Integer inputVal) {
		getListofVariants(productPage.PerfumeVariants);
		selectVariant(inputVal);
	}
	
	@Then("Validate quantity is {int}")
	public void validate_quantity_is(Integer quantity){
		setQuantitylist(quantity);
		validateCheckOutCartCount(quantity);
	}
	
	@Then("Changes quantity to {int}")
	public void changes_quantity_to(Integer quantity){
		setQuantitylist(quantity);
		validateCheckOutCartCount(quantity);
	}
}
