package Pages;

import java.time.Duration;

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

	public Checkout(WebDriver driver) {
		this.driver = driver;
		boolean isLinux = System.getProperty("os.name", "").toLowerCase().contains("linux");
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(isLinux ? 35 : 20));
		this.js = (JavascriptExecutor) driver;
	}

	public boolean verifyCheckoutPage() {

		WebElement contactTitle = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@id='deliveryAddress']")));

		return contactTitle.isDisplayed();
	}

	public void fillCheckoutForm() throws Exception {

		 try {
		        // --- Locators ---
		        By emailBy = By.id("email");
		        By firstNameBy = By.name("firstName");
		        By lastNameBy  = By.name("lastName");
		        By address1By  = By.name("address1");
		        By cityBy      = By.name("city");
		        By pinBy       = By.name("postalCode");
		        By phoneBy     = By.name("phone");

		        // Shopify checkout address suggestions (most reliable)
		        By suggestionsBy = By.cssSelector("[role='listbox'] [role='option']");

		        // --- Email ---
		        typeSlow(emailBy, "automationtest3j@gmail.com", 60);

		        // --- Name ---
		        typeSlow(firstNameBy, "Test", 80);
		        typeSlow(lastNameBy, "Singh", 80);

		        // --- Address (autocomplete) ---
		        WebElement add1 = wait.until(ExpectedConditions.elementToBeClickable(address1By));
		        add1.click();
		        clearField(add1);

		        // Type in 2 parts so suggestions load properly
		        typeSlow(add1, "Noida city center", 70);

		        // Wait for suggestions (if they don't appear, proceed anyway)
		        waitUntilSuggestions(suggestionsBy, 1, 10);

		        typeSlow(add1, " Sector 39", 70);
		        waitUntilSuggestions(suggestionsBy, 1, 10);

		        // Select first suggestion (keyboard is best in headless)
		        add1.sendKeys(Keys.ARROW_DOWN);
		        add1.sendKeys(Keys.ENTER);

		        // Wait a bit for autofill to populate city/state/pin (condition-based, not sleep)
		        wait.until(ExpectedConditions.visibilityOfElementLocated(cityBy));

		        // --- City (only fill if not auto-filled) ---
		        WebElement city = driver.findElement(cityBy);
		        if (isBlank(city.getAttribute("value"))) {
		            typeSlow(cityBy, "Noida", 70);
		        }

		        // --- PIN (only fill if not auto-filled) ---
		        WebElement pin = driver.findElement(pinBy);
		        if (isBlank(pin.getAttribute("value"))) {
		            clearField(pin);
		            typeSlow(pinBy, "201303", 70);
		        }

		        // --- Phone ---
		        WebElement phone = wait.until(ExpectedConditions.elementToBeClickable(phoneBy));
		        if (isBlank(phone.getAttribute("value"))) {
		            clearField(phone);
		            typeSlow(phoneBy, "9876543210", 60);
		        }

		    } catch (Exception e) {
		        throw new RuntimeException("❌ Failed to fill checkout form", e);
		    }
		}

		/* ================= Helpers ================= */

		private void typeSlow(By by, String text, int delayMs) {
		    WebElement el = wait.until(ExpectedConditions.elementToBeClickable(by));
		    el.click();
		    clearField(el);
		    typeSlow(el, text, delayMs);
		}

		private void typeSlow(WebElement el, String text, int delayMs) {
		    for (char c : text.toCharArray()) {
		        el.sendKeys(String.valueOf(c));
		        sleepSilently(delayMs);
		    }
		}

		private void clearField(WebElement el) {
		    try {
		        el.sendKeys(Keys.chord(Keys.CONTROL, "a"));
		        el.sendKeys(Keys.BACK_SPACE);
		    } catch (Exception e) {
		        el.clear();
		    }
		}

		private void sleepSilently(int ms) {
		    try {
		        Thread.sleep(ms);
		    } catch (InterruptedException ignored) {
		        Thread.currentThread().interrupt();
		    }
		}

		/**
		 * Waits up to maxSeconds for at least minCount suggestions to appear.
		 * Returns true if suggestions appeared, false otherwise.
		 */
		private boolean waitUntilSuggestions(By suggestionsBy, int minCount, int maxSeconds) {
		    try {
		        WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(maxSeconds));
		        return shortWait.until(d -> d.findElements(suggestionsBy).size() >= minCount);
		    } catch (Exception ignored) {
		        return false;
		    }
		}

		private boolean isBlank(String s) {
		    return s == null || s.trim().isEmpty();
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
