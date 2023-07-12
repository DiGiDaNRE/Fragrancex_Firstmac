package common;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import manager.DriverManager;
import pageobjects.CheckOutPage;
import pageobjects.ProductPage;


public class Commands extends BasePage {
	
	CheckOutPage checkOutPage = new CheckOutPage();
	ProductPage productPage = new ProductPage();
	DriverManager driverManager = new DriverManager();
	
	public void setupBrowser(String BrowserType) {
		if(BrowserType == "chrome") {
			driverManager.setupDriverChrome();
		}else if(BrowserType == "firefox") {
			driverManager.setupDriverfirefox();
		}else if(BrowserType == "edge") {
			driverManager.setupDriverEdge();
		};
	}
	
	public void popUp() {
		waitElement(By.xpath("//*[@id=\"coupon-popup\" and @style=\"display: block;\"]"));
		clickElement(By.xpath("/html/body/div[4]/div/div/div[1]/img[2]"));
	}
	
	public void accessURL(String url) {
		setupBrowser("chrome");
		driver.get(url);
		popUp();
	}

	public static void waitElement(By by) {
		int attempts = 0;
		while(attempts < 10) {
			try {
				WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(3));
				wait.until(ExpectedConditions.visibilityOfElementLocated(by));
				break;
			}catch(Exception e) {
			}
			attempts++;
		}
	}

	public void clickElement(By by) {
		waitElement(by);
		driver.findElement(by).click();
	}
	
	public void verifyElement(By by) {
		waitElement(by);
		driver.findElement(by).isDisplayed();
	}
	
	public void getListofProducts(By by) {
		waitElement(by);
	    List<WebElement> listOfLinks = driver.findElements(by); //retrieved all products
	    System.out.println("Product list of 'Top Picks For You'");
	    for(WebElement e : listOfLinks) 
        {        
        System.out.println("Anchor: "+e.getAttribute("href"));																//list all products 
        }	
	    
	    WebElement ProductAnchor = listOfLinks.get(2);																		//select product needed for test	
	    String HrefProductAnchor = ProductAnchor.getAttribute("href");														//retrieve href attribute of product

	    String HrefProductAnchorInitial = ProductAnchor.getText().toLowerCase();											
	    String[] SplitAnchor1 = HrefProductAnchorInitial.split("by", 0);													//retrieving productName
	    
	    String[] SplitAnchor = HrefProductAnchor.split("https://www.fragrancex.com", 0);									//split in order to be append in xpath
	    System.out.println("Picked Product: "+SplitAnchor[1]);
	    waitElement(By.xpath("//a[@href='"+SplitAnchor[1]+"']"));															//appended in xpath
		driver.findElement(By.xpath("//a[@href='"+SplitAnchor[1]+"']")).click();
	    
													
		
		WebElement ComapreProduct = driver.findElement(productPage.ProductNameSelector);									//locating productName
		String ProdctName = ComapreProduct.getText().toLowerCase();
		
		System.out.println("Selected Product: "+ProdctName);
		System.out.println("Clicked Product: "+SplitAnchor1[0]);
		System.out.println("Is displayed product selected:"+ProdctName.replaceAll("\\s", "_").contains(SplitAnchor1[0].replaceAll("\\s", "_")));	//check if it contains string of retrieved productname with located product name.
	}
	
	public void getListofVariants(By by) {
		waitElement(by);
	    List<WebElement> listOfLinks = driver.findElements(by); //retrieved all products
	    System.out.println("List of Variants");
	    for(WebElement e : listOfLinks) 
        {        
        System.out.println("Variants: "+e.getText());																//list all products 
        }
	    WebElement Variant = listOfLinks.get(1);
	    String StrVariant = Variant.getText();
	    System.out.println("Selected Variant: "+StrVariant); 
	    
	    WebElement itemCode = driver.findElement(By.xpath("//*[contains(@alt,'"+StrVariant+"')]"));
	    String itemcodeDataSrc = itemCode.getAttribute("data-src");
	    String[] CodeSplit = itemcodeDataSrc.split("https://img.fragrancex.com/images/products/sku/small/", 0);
	    String Value1 = CodeSplit[1];
	    String Value2 = Value1.replace(".jpg", "");
	    System.out.println("itemcode: "+Value2); 
	    clickElement(By.xpath("//div/div/div/div/div/div/div/div/div/div/form/input[@value='"+Value2+"']//parent::form/button"));
	}
	
	public void validateCheckOutCartCount(Integer quantity) {
		int num1;
		waitElement(checkOutPage.CheckOutBagQuantity);
		WebElement CheckOutCount = driver.findElement(checkOutPage.CheckOutBagQuantity);
		String BagCount = CheckOutCount.getText();
		num1 = Integer.parseInt(BagCount);
		if(num1 == quantity) {
            System.out.println("Both actual and expceted are equal");
            System.out.println("expected:"+quantity); 
    		System.out.println("actual:"+num1); 
			}else {
            System.out.println("actual and expceted are not equal");
            validateCheckOutCartCount(quantity);
			}
	}
	
	public void getQuantitylist() {
		waitElement(checkOutPage.ItemDropDownList);
	    List<WebElement> listOfLinks = driver.findElements(checkOutPage.ItemDropDownList); //retrieved all products
	    System.out.println("Quantity Choices");
	    for(WebElement e : listOfLinks) 
        {        
        System.out.println("Value: "+e.getText());																//list all quantity 
        }
	}
}
