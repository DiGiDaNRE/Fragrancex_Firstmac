package pageobjects;

import org.openqa.selenium.By;

public class ProductPage{

	public By PerfumeVariants = By.xpath("/html/body/div[1]/section/div[1]/div[2]/div[1]/div[4]/div/div/div[2]/div/div/div[1]/h2");
	public By DropDownQuantity = By.xpath("//*[@id=\"CartQuantityAsyncForm\"]/div/div[2]/div[2]/div[3]/div[2]/div[1]/select/option[5]");
	public By ProductNameSelector = By.xpath("//*[@id=\"content\"]/section/div[1]/div[2]/div[1]/div[2]/div/div/h1/span");;
	
	
}
