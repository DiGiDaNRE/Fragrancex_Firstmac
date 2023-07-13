package pageobjects;

import org.openqa.selenium.By;

public class CheckOutPage {
	public By ItemDropDownList = By.xpath("//*[@id=\"CartQuantityAsyncForm\"]/div/div[2]/div[2]/div[3]/div[2]");
	public By CheckOutBagQuantity = By.xpath("/html/body/header/div[2]/div/div[4]/a/div");
	public By DropDownQuantity = By.xpath("//*[@id=\"CartQuantityAsyncForm\"]/div/div[2]/div[2]/div[3]/div[2]/div[1]/select/option[5]");
	
	 public static By SelectedQuantityLocator(int chooseQuantity) {
		  String partialXpath =  "//*[@id='CartQuantityAsyncForm']/div/div[2]/div[2]/div[3]/div[2]/div[1]/select/option[%d]";	      
	      String fullXpath = String.format(partialXpath, chooseQuantity);
	      By SelectedQuantity = By.xpath(fullXpath);
	      return SelectedQuantity;
	    }

}
