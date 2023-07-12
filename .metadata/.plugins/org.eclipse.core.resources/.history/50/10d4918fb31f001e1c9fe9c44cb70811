package common;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Hooks extends BasePage {

	@Before //Cucumber Before Hook
	public static void setupDriver() throws InterruptedException {

		ChromeOptions chromeOptions = new ChromeOptions();
		WebDriverManager.chromedriver().setup();
		chromeOptions.addArguments("--remote-allow-origins=*");
		chromeOptions.addArguments("--disable-gpu");
		chromeOptions.addArguments("--disable-dev-shm-usage");
		chromeOptions.addArguments("--no-sandbox");
		chromeOptions.addArguments("--disable-notifications");
		driver  = new ChromeDriver(chromeOptions);
		driver.manage().window().maximize();
		driver.get("https://www.fragrancex.com/");
	}

	@After // Cucumber After hook
	public static void quitDriver() throws Exception {
		driver.quit();
	}

}
