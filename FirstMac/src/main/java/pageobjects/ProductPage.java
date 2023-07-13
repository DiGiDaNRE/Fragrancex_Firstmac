package pageobjects;


import org.openqa.selenium.By;

import utility.Commands;


public class ProductPage  {
	
	public By PerfumeVariants = By.xpath("/html/body/div[1]/section/div[1]/div[2]/div[1]/div[4]/div/div/div[2]/div/div/div[1]/h2");
	public By ProductNameSelector = By.xpath("//*[@id=\"content\"]/section/div[1]/div[2]/div[1]/div[2]/div/div/h1/span");
	
	 public By SelectedProductVariant(String itemCode) {
		  String partialXpath =  "//*[contains(@alt,'%s')]";	      
	      String fullXpath = String.format(partialXpath, itemCode);
	      By ProductVariant = By.xpath(fullXpath);
	      return ProductVariant;
	    }
	 
	 public By CheckOutButtonSelector(String Value2) {
		  String partialXpath =  "//div/div/div/div/div/div/div/div/div/div/form/input[@value='%s']//parent::form/button";	      
	      String fullXpath = String.format(partialXpath, Value2);
	      By CheckOutButton = By.xpath(fullXpath);
	      return CheckOutButton;
	    }
	 
	 

}
