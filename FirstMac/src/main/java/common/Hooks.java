package common;

import io.cucumber.java.After;

public class Hooks extends BasePage {
	@After // Cucumber After hook
	public static void quitDriver() throws Exception {
		driver.quit();
	}
}
