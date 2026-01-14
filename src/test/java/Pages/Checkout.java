package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Checkout {

	WebDriver driver;
	WebDriverWait wait;
	JavascriptExecutor js;

	public Checkout(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
		this.js = (JavascriptExecutor) driver;
	}

	public boolean verifyCheckoutPage() {

		WebElement contactTitle = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@id='deliveryAddress']")));

		return contactTitle.isDisplayed();
	}

	public void fillCheckoutForm() throws Exception {

		wait.until(ExpectedConditions.elementToBeClickable(By.id("email"))).sendKeys("automationtest3j@gmail.com");

		// name
		WebElement fname = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("firstName")));
		fname.sendKeys("Test");

		// last name
		WebElement lname = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("lastName")));
		lname.sendKeys("Singh");

		// address by dropdown
		WebElement Add1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("address1")));
		Add1.sendKeys("Noida city center Sector 39");
		Thread.sleep(3000);

		// first suggestion select
		Add1.sendKeys(Keys.ARROW_DOWN);
		Thread.sleep(2000);
		Add1.sendKeys(Keys.ENTER);

		WebElement city = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("city")));
		city.sendKeys("Noida");
		Thread.sleep(1000);

		WebElement pincode = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("postalCode")));
		pincode.clear();
		pincode.sendKeys("201303");

		WebElement mobile = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='phone']")));
		mobile.sendKeys("9876543210");

	}

	public void clickPayNow() throws Exception {
		Thread.sleep(3000);
		// Scroll to bottom
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(1500);

		WebElement payNow = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='checkout-pay-button']")));

		js.executeScript("arguments[0].click();", payNow);
		payNow.click();
	}

	public boolean checkoutVerify() {
		try {
			// Thoda zyada wait rakha 15 sec for payment page load
			WebDriverWait longWait = new WebDriverWait(driver, java.time.Duration.ofSeconds(15));

			WebElement secureCheckout = longWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
					"//div[contains(@class,'sidebarFooter__secureCheckout') and .//p[text()='Secure Checkout']]")));

			// Return true if visible and enabled
			return secureCheckout.isDisplayed() && secureCheckout.isEnabled();

		} catch (Exception e) {
			// Agar element nahi mila ya exception aaya → return false
			return false;
		}
	}
}
