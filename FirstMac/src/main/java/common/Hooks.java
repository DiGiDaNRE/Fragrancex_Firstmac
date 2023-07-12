package common;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import manager.DriverManager;

public class Hooks extends BasePage {
	
	static final Logger logger = LogManager.getLogger(Hooks.class.getName());
	
	@Before
	public static void setupbrowser() throws FileNotFoundException {
		DriverManager.setupDriverChrome();
		truncatedFile();
		DOMConfigurator.configure("log4j.xml");
		logger.info("WebDriver start");
	}
	
	@After // Cucumber After hook
	public static void quitDriver() {
		driver.quit();
		logger.info("Webdriver close");

	}
	
	public static void truncatedFile() throws FileNotFoundException {
		PrintWriter writer = new PrintWriter("Result.log");
		writer.print("");
		writer.close();
	}
}
