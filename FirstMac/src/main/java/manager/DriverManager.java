package manager;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import common.BasePage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverManager extends BasePage {
	
	public void setupBrowser(String BrowserType) {
		if(BrowserType == "chrome") {
			setupDriverChrome();
		}else if(BrowserType == "firefox") {
			setupDriverfirefox();
		}else if(BrowserType == "edge") {
			setupDriverEdge();
		};
	}
	
	public static void setupDriverChrome() {
		ChromeOptions chromeOptions = new ChromeOptions();
		WebDriverManager.chromedriver().setup();
		chromeOptions.addArguments("--remote-allow-origins=*");
		chromeOptions.addArguments("--disable-gpu");
		chromeOptions.addArguments("--disable-dev-shm-usage");
		chromeOptions.addArguments("--no-sandbox");
		chromeOptions.addArguments("--disable-notifications");
		driver  = new ChromeDriver(chromeOptions);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
	}
	
	public static void setupDriverfirefox() {
		FirefoxOptions firefoxOptions = new FirefoxOptions();
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver(firefoxOptions);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
	}
	
	public static void setupDriverEdge() {
		EdgeOptions edgeOptions = new EdgeOptions();
		WebDriverManager.edgedriver().setup();
		edgeOptions.addArguments("--remote-allow-origins=*");
		driver = new EdgeDriver(edgeOptions);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
	}
}
