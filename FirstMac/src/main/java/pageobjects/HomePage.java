package pageobjects;

import org.openqa.selenium.By;

public class HomePage {
	public By FragranceXTitle = By.xpath("//div[2]/div/div[1]/a/img[@title='FragranceX']");
	public By TopPicksForYou = By.xpath("//*[@id=\"recommended-items\"]/div/div[1]//a[@href]");
}
