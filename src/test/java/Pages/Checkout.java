package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Checkout {
	
	WebDriver driver;
	WebDriverWait wait;
	JavascriptExecutor js;

	public Checkout(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
		this.js = (JavascriptExecutor) driver;
	}
	
	@Test(priority = 1)
	public void verifyCheckoutPage() {

	    WebElement contactTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(
	            By.xpath("//h2[@id='deliveryAddress']")
	    ));

	    Assert.assertTrue(contactTitle.isDisplayed(), "Checkout page not opened");
	}
	
	@Test(priority = 2)
	public void fillCheckoutForm() throws Exception {

	    wait.until(ExpectedConditions.elementToBeClickable(By.id("email")))
	            .sendKeys("automationtest3j@gmail.com");

	    driver.findElement(By.name("firstName")).sendKeys("Test");
	    driver.findElement(By.name("lastName")).sendKeys("Singh");
	    driver.findElement(By.name("company")).sendKeys("L&F");
	    driver.findElement(By.name("address1")).sendKeys("Test");
	    driver.findElement(By.name("address2")).sendKeys("Test");
	    driver.findElement(By.name("city")).sendKeys("Ghaziabad");
	    driver.findElement(By.name("postalCode")).sendKeys("201010");
	    driver.findElement(By.name("phone")).sendKeys("9876543210");

	}
	
	@Test(priority = 3)
	public void clickPayNow() throws Exception {

	    // Scroll to bottom
	    js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	    Thread.sleep(1500);

	    // Shopify Pay Now iframe
	    WebElement iframe = wait.until(ExpectedConditions.presenceOfElementLocated(
	            By.xpath("//iframe[contains(@title,'Checkout')]")
	    ));
	    driver.switchTo().frame(iframe);

	    WebElement payNow = wait.until(ExpectedConditions.elementToBeClickable(
	            By.xpath("//button[contains(.,'Pay now')]")
	    ));

	    js.executeScript("arguments[0].click();", payNow);

	    driver.switchTo().defaultContent();
	}




}
