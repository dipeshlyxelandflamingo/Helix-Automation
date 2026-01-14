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
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		this.js = (JavascriptExecutor) driver;
		this.act = new Actions(driver);
	}

	// ---------- Quantity Increase ----------
	public void increaseQuantity() {

		for (int i = 1; i <= 3; i++) {

			WebElement qtyPlusBtn = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@class,'qty-plus')]")));

			qtyPlusBtn.click();
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

			qtyMinusBtn.click();
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
		checkoutBtn.click();
		System.out.println("✔ Quick checkout initiated");
	}
}
