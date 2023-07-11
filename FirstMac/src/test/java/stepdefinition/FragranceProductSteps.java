package stepdefinition;

import org.openqa.selenium.By;

import common.Commands;
import io.cucumber.java.en.*;
import pageobjects.HomePage;

public class FragranceProductSteps extends Commands {

	HomePage CustLogin = new HomePage();
	
	@Given("User access website")
	public void user_access_website() {	
		verifyElement(CustLogin.FragranceXTitle);
	}

	@When("User naavigate to TOP PICK FOR YOU category to select Third product displayed")
	public void user_naavigate_to_top_pick_for_you_category_to_select_third_product_displayed() {
		getListofProducts(CustLogin.TopPicksForYou);
		
	}
		
	@Then("Select second variant proceed with checkout")
	public void select_second_variant_proceed_with_checkout() {
		getListofVariants(CustLogin.PerfumeVariants);
	}
	
	@Then("Validate quantity is one")
	public void validate_quantity_is_one() {
		validateCheckOutCartCount();

	}
	@Then("Changes quantity to five")
	public void changes_quantity_to_five() {
		clickElement(By.xpath("//*[@id=\"CartQuantityAsyncForm\"]/div/div[2]/div[2]/div[3]/div[2]/div[1]/select/option[5]"));
		validateCheckOutCartCount();
	}
}
