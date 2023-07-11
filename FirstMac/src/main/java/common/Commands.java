package common;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Commands extends BasePage {

	
	public void waitElement(By by) {
		int attempts = 0;
		while(attempts < 20) {
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
	    
		WebElement ProductNameInitial = listOfLinks.get(2);																	//Convert to variable in feature step																		
	    String HrefProductAnchorInitial = ProductNameInitial.getText().toLowerCase();
	    String[] SplitAnchor1 = HrefProductAnchorInitial.split("by", 0);	
	    
	    WebElement ProductAnchor = listOfLinks.get(2);																		//select product needed for test	
	    String HrefProductAnchor = ProductAnchor.getAttribute("href");														//retrieve href attribute of product
	       
	    String[] SplitAnchor = HrefProductAnchor.split("https://www.fragrancex.com", 0);									//split in order to be append in xpath
	    System.out.println("Picked Product: "+SplitAnchor[1]);
	    	
	    waitElement(By.xpath("//a[@href='"+SplitAnchor[1]+"']"));															//appended in xpath
		driver.findElement(By.xpath("//a[@href='"+SplitAnchor[1]+"']")).click();
	    
		WebElement ComapreProduct = driver.findElement(By.xpath("//*[@id=\"content\"]/section/div[1]/div[2]/div[1]/div[2]/div/div/h1/span"));
		String ProdctName = ComapreProduct.getText().toLowerCase();
		System.out.println("Product Selected: "+ProdctName);
		System.out.println("Product Clicked: "+SplitAnchor1[0]);
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
	    System.out.println("data-src: "+itemcodeDataSrc); 
	    String[] CodeSplit = itemcodeDataSrc.split("https://img.fragrancex.com/images/products/sku/small/", 0);
	    String Value1 = CodeSplit[1];
	    String Value2 = Value1.replace(".jpg", "");
	    System.out.println("itemcode: "+Value2); 
	    clickElement(By.xpath("//div/div/div/div/div/div/div/div/div/div/form/input[@value='"+Value2+"']//parent::form/button"));
	}
	
	public void validateCheckOutCartCount() {
		waitElement(By.xpath("//*[@id=\"AjaxTopCart\"]/a/div"));
		WebElement CheckOutCount = driver.findElement(By.xpath("//*[@id=\"AjaxTopCart\"]/a/div"));
		String BagCount = CheckOutCount.getText();
		 System.out.println("Number of Check-Out item: "+BagCount); 
	}
	
	public void getQuantitylist() {
		waitElement(By.xpath("//*[@id=\"CartQuantityAsyncForm\"]/div/div[2]/div[2]/div[3]/div[2]/div[1]/select"));
	    List<WebElement> listOfLinks = driver.findElements(By.xpath("//*[@id=\"CartQuantityAsyncForm\"]/div/div[2]/div[2]/div[3]/div[2]/div[1]/select")); //retrieved all products
	    System.out.println("Quantity Choices");
	    for(WebElement e : listOfLinks) 
        {        
        System.out.println("Value: "+e.getText());																//list all quantity 
        }
	}
}
