package Pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class MiniCartPage {

	WebDriver driver;
	WebDriverWait wait;
	JavascriptExecutor js;
	Actions act;

	public MiniCartPage(WebDriver driver) {
		this.driver = driver;
		boolean isLinux = System.getProperty("os.name", "").toLowerCase().contains("linux");
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(isLinux ? 35 : 20));
		this.js = (JavascriptExecutor) driver;
		
	}

	// ---------- Quantity Increase ----------
	public void increaseQuantity() {

		for (int i = 1; i <= 3; i++) {

			WebElement qtyPlusBtn = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@class,'qty-plus')]")));

			js.executeScript("arguments[0].click();", qtyPlusBtn);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// NOW read quantity from INPUT
		WebElement qtyInput = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[contains(@class,'qty')]")));

		String actualQty = qtyInput.getAttribute("value");

		Assert.assertEquals(actualQty, "4", "Quantity is NOT 4");
	}

	public void decreaseQuantity() {

		for (int i = 1; i <= 3; i++) {

			WebElement qtyMinusBtn = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='qty-minus']")));

			js.executeScript("arguments[0].click();", qtyMinusBtn);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		WebElement qtyInput = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[contains(@class,'qty')]")));

		String actualQty = qtyInput.getAttribute("value");

		Assert.assertEquals(actualQty, "1", "Quantity is NOT 1");

	}

	public void enterPincode(String pincode) {
		WebElement pincodeInput = wait.until(ExpectedConditions.elementToBeClickable(By.id("userPincode")));
		pincodeInput.clear();
		pincodeInput.sendKeys(pincode);
		System.out.println("✔ Pincode entered successfully");
	}

	// ---------- Quick Checkout ----------
	public void goToQuickCheckout() {
		WebElement checkoutBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("checkout")));
		js.executeScript("arguments[0].click();", checkoutBtn);
		System.out.println("✔ Quick checkout initiated");
	}
}
