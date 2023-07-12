package utility;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import common.BasePage;
import pageobjects.CheckOutPage;
import pageobjects.ProductPage;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Commands extends BasePage {
	
	static final Logger logger = LogManager.getLogger(Commands.class.getName());
	
	CheckOutPage checkOutPage = new CheckOutPage();
	ProductPage productPage = new ProductPage();
	
	public void accessURL(String url) {
		driver.get(url);
		logger.info("Website URL:"+url);
		popUp();
	}

	public void popUp() {
		waitElement(By.xpath("//*[@id=\"coupon-popup\" and @style=\"display: block;\"]"));
		clickElement(By.xpath("/html/body/div[4]/div/div/div[1]/img[2]"));
		logger.info("Popup has been Close");
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
		logger.info("WebElement is visible");
	}

	public void clickElement(By by) {
		waitElement(by);
		driver.findElement(by).click();
		logger.info("WebElement has been click: "+by);
	}
	
	public void verifyElement(By by) {
		waitElement(by);
		driver.findElement(by).isDisplayed();
		logger.info("WebElement is visible: "+by);
	}
	
	public void getListofProducts(By by) {
		waitElement(by);
	    List<WebElement> listOfLinks = driver.findElements(by); 															//retrieved all products
	    System.out.println("Product list of 'Top Picks For You'");
	    for(WebElement e : listOfLinks) 
        {        
        System.out.println("Anchor: "+e.getAttribute("href"));		
        logger.info("Anchor: "+e.getAttribute("href"));																		//list all products 
        }	
	    
	    WebElement ProductAnchor = listOfLinks.get(2);																		//select product needed for test	
	    String HrefProductAnchor = ProductAnchor.getAttribute("href");														//retrieve href attribute of product

	    String HrefProductAnchorInitial = ProductAnchor.getText().toLowerCase();											
	    String[] SplitAnchor1 = HrefProductAnchorInitial.split("by", 0);													//retrieving productName
	    logger.info("Selected Product: "+SplitAnchor1[0]);	
	    
	    String[] SplitAnchor = HrefProductAnchor.split("https://www.fragrancex.com", 0);									//split in order to be append in xpath
	    waitElement(By.xpath("//a[@href='"+SplitAnchor[1]+"']"));															//appended in xpath
		driver.findElement(By.xpath("//a[@href='"+SplitAnchor[1]+"']")).click();
	    								
		WebElement ComapreProduct = driver.findElement(productPage.ProductNameSelector);									//locating productName for comparison
		String ProdctName = ComapreProduct.getText().toLowerCase();
		logger.info("Picked Product: "+ProdctName);

		System.out.println("Is displayed product selected:"+ProdctName.replaceAll("\\s", "_").contains(SplitAnchor1[0].replaceAll("\\s", "_")));
		logger.info("Is displayed product selected: "+ProdctName.replaceAll("\\s", "_").contains(SplitAnchor1[0].replaceAll("\\s", "_")));	
	}
	
	public void getListofVariants(By by) {
		waitElement(by);
	    List<WebElement> listOfLinks = driver.findElements(by); 															//retrieved all variants
	    System.out.println("List of Variants");
	    for(WebElement e : listOfLinks) 
        {        
        System.out.println("Variants: "+e.getText());
        logger.info("Variants: "+e.getText());																				//list all variants 
        }
	    WebElement Variant = listOfLinks.get(1);
	    String StrVariant = Variant.getText();
	    System.out.println("Selected Variant: "+StrVariant); 
        logger.info("Selected Variant: "+StrVariant);	
        
	    WebElement itemCode = driver.findElement(By.xpath("//*[contains(@alt,'"+StrVariant+"')]"));							//locating itemcode to be use for xpath
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
			logger.info("Expected and actual checkout quantity is equal");	
            System.out.println("Both actual and expceted are equal");
            System.out.println("expected:"+quantity); 
    		System.out.println("actual:"+num1); 
			}else {
            validateCheckOutCartCount(quantity);	
			}
	}
	
	public void getQuantitylist() {
		waitElement(checkOutPage.ItemDropDownList);
	    List<WebElement> listOfLinks = driver.findElements(checkOutPage.ItemDropDownList); 
	    System.out.println("Quantity Choices");
	    for(WebElement e : listOfLinks) 
        {        
        System.out.println("Value: "+e.getText());															
        }
	}
}