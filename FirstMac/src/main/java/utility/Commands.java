package utility;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import common.BasePage;
import pageobjects.CheckOutPage;
import pageobjects.HomePage;
import pageobjects.ProductPage;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Commands extends BasePage {
	
	static final Logger logger = LogManager.getLogger(Commands.class.getName());
	
	CheckOutPage checkOutPage = new CheckOutPage();
	ProductPage productPage = new ProductPage();
	HomePage homePage = new HomePage();
	WebElementContainer container = new WebElementContainer();
	
	public void accessURL(String url) {
		driver.get(url);
		logger.info("Website URL:"+url);
		selectPopUp();
	}

	public void selectPopUp() {
		waitElement(homePage.PopUp);
		clickElement(homePage.PopUpButton);
		logger.info("Popup has been Close");
	}
	
	public static void waitElement(By by) {
		int attempts = 0;
		while(attempts < 3) {
			try {
				WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
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
	
	public void getList(By by) {
		waitElement(by);
	    List<WebElement> getList = driver.findElements(by); 	
	    container.setWebElementList(getList);
	}
	
	public void getListofProducts(By by) {
		getList(by);
	    List<WebElement> retrievedElements = container.getWebElementList();
	    																	
	    System.out.println("Product list of 'Top Picks For You'");
	    for(WebElement e : retrievedElements) 
	    	{        
	    		System.out.println("Anchor: "+e.getAttribute("href"));		
	    		logger.info("Anchor: "+e.getAttribute("href"));
	    	}	
	}
	
	public void getSelectedProduct(Integer inputval){
		List<WebElement> retrievedElements = container.getWebElementList();
	    Integer chooseProduct = inputval - 1;
	    WebElement ProductAnchor = retrievedElements.get(chooseProduct);										//select product needed for test	
	    String HrefProductAnchor = ProductAnchor.getAttribute("href");										//retrieve href attribute of product
	    
	    String HrefProductAnchorInitial = ProductAnchor.getText().toLowerCase();											
	    
	    String[] SplitAnchor = HrefProductAnchor.split("https://www.fragrancex.com", 0);										//split in order to be append in xpath
	    By xpathSelectedTopPicksForYou = homePage.SelectedTopPicksForYou(SplitAnchor[1]);
	    clickElement(xpathSelectedTopPicksForYou);
	    verifyProduct(HrefProductAnchorInitial);		
	}
	
	public void verifyProduct(String HrefProductAnchorInitial) {
		String[] SplitAnchor1 = HrefProductAnchorInitial.split("by", 0);										//retrieving productName
	    logger.info("Selected Product: "+SplitAnchor1[0]);	
	    WebElement ComapreProduct = driver.findElement(productPage.ProductNameSelector);								//locating productName for comparison
		String ProdctName = ComapreProduct.getText().toLowerCase();
		logger.info("Picked Product: "+ProdctName);
		System.out.println("Is displayed product selected:"+ProdctName.replaceAll("\\s", "_").contains(SplitAnchor1[0].replaceAll("\\s", "_")));
		logger.info("Is displayed product selected: "+ProdctName.replaceAll("\\s", "_").contains(SplitAnchor1[0].replaceAll("\\s", "_")));	
	}
	
	public void getListofVariants(By by) {
		getList(by);
	    List<WebElement> retrievedElements = container.getWebElementList();
	    																											
	    System.out.println("Product list of 'Top Picks For You'");
	    for(WebElement e : retrievedElements) 
	    	{        	
	    		System.out.println("Variants: "+e.getText());
	    		logger.info("Variants: "+e.getText());																				 
	    	}
	}
	    
	public void selectVariant(Integer inputval) {
		List<WebElement> retrievedElements = container.getWebElementList();
		Integer chooseVariant = inputval - 1;
	    WebElement Variant = retrievedElements.get(chooseVariant);
	    String StrVariant = Variant.getText();
	    System.out.println("Selected Variant: "+StrVariant); 
        logger.info("Selected Variant: "+StrVariant);	
        By xpathSelectedProductVariant = productPage.SelectedProductVariant(StrVariant);
	    WebElement itemCode = driver.findElement(xpathSelectedProductVariant);									//locating itemcode to be use for xpath
	    String itemcodeDataSrc = itemCode.getAttribute("data-src");
	    String[] CodeSplit = itemcodeDataSrc.split("https://img.fragrancex.com/images/products/sku/small/", 0);
	    String Value1 = CodeSplit[1];
	    String Value2 = Value1.replace(".jpg", "");
	    System.out.println("itemcode: "+Value2); 
	    selectAddtoCartButton(Value2);
	}
	
	public void selectAddtoCartButton(String Value2) {
		By xpathCheckOutButtonSelector = productPage.CheckOutButtonSelector(Value2);
		clickElement(xpathCheckOutButtonSelector);
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
	
	public void setQuantitylist(Integer quantity) {
		getList(checkOutPage.ItemDropDownList);
	    List<WebElement> retrievedElements = container.getWebElementList();		
	    System.out.println("Quantity Choices:");
	    for(WebElement e : retrievedElements) 
        	{        	
	    		System.out.println(e.getText());
	    		logger.info(e.getText());															
        	}	
	    System.out.println("Quantity Selected: "+quantity);
	    By xpathSelectedQuantityLocator = CheckOutPage.SelectedQuantityLocator(quantity);
	    clickElement(xpathSelectedQuantityLocator);
	    }
	}
	
	
