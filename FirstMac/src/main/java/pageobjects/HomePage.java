package pageobjects;

import org.openqa.selenium.By;

public class HomePage {
	public By PopUp = By.xpath("//*[@id=\"coupon-popup\" and @style=\"display: block;\"]");
	public By PopUpButton = By.xpath("/html/body/div[4]/div/div/div[1]/img[2]");
	public By FragranceXTitle = By.xpath("//div[2]/div/div[1]/a/img[@title='FragranceX']");
	public By TopPicksForYou = By.xpath("//*[@id=\"recommended-items\"]/div/div[1]//a[@href]");
	
	 public By SelectedTopPicksForYou(String anchor) {
		  String partialXpath =  "//a[@href='%s']";	      
	      String fullXpath = String.format(partialXpath, anchor);
	      By TopPicksForYou = By.xpath(fullXpath);
	      return TopPicksForYou;
	    }
	 

}
