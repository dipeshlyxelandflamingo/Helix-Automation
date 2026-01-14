package Pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebDriver;

public class HomePage {

	JavascriptExecutor js;
	WebDriverWait wait;
	WebDriver driver;
	Actions act;

	// Constructor
	public HomePage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		this.js = (JavascriptExecutor) driver;
		this.act = new Actions(driver);
	}

	// TC_01

	public void clickmenbestsellerShopNowAndCloseTab() {

		js.executeScript("window.scrollBy(0,1000)");

		WebElement shopMBNowCTA = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//a[contains(@class,'yb-text-wrapper') and .//p[text()=\"Men's Best Sellers\"]]")));

		String parent = driver.getWindowHandle();

		// Open in new tab
		act.keyDown(Keys.CONTROL).click(shopMBNowCTA).keyUp(Keys.CONTROL).perform();

		// Wait till child tab opens
		wait.until(ExpectedConditions.numberOfWindowsToBe(2));

		// Switch to child tab
		for (String tab : driver.getWindowHandles()) {
			if (!tab.equals(parent)) {
				driver.switchTo().window(tab);
				break;
			}
		}

		// Simple wait (page is usable)
		wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
		// EXTRA 2 seconds wait (as required)
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Close child tab and switch back
		driver.close();
		driver.switchTo().window(parent);
	}

	// TC_02

	public void clickwomenbestsellerShopNowAndCloseTab() {

//		js.executeScript("window.scrollBy(0,1000)");

		WebElement shopWBNowCTA = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"//a[contains(@class,\"yb-text-wrapper\") and .//p[contains(text(),\"Women's Best Sellers\")]] ")));

		String parent = driver.getWindowHandle();

		// Open in new tab
		act.keyDown(Keys.CONTROL).click(shopWBNowCTA).keyUp(Keys.CONTROL).perform();

		// Wait till child tab opens
		wait.until(ExpectedConditions.numberOfWindowsToBe(2));

		// Switch to child tab
		for (String tab : driver.getWindowHandles()) {
			if (!tab.equals(parent)) {
				driver.switchTo().window(tab);
				break;
			}
		}

		// Simple wait (page is usable)
		wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
		// EXTRA 2 seconds wait
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Close child tab and switch back
		driver.close();
		driver.switchTo().window(parent);
	}

//TC_03

	public void clickLatestReleaseProductLPFCTAAndCloseTab() {

		js.executeScript("window.scrollBy(0,2000)");

		WebElement LPFCTA = wait.until(ExpectedConditions.elementToBeClickable(By
				.xpath("(//a[@href='/products/helix-by-timex-grey-octagon-analog-silicone-watch-men-tw041hg13'])[3]")));
		String parent = driver.getWindowHandle();

		// Open in new tab
		act.keyDown(Keys.CONTROL).click(LPFCTA).keyUp(Keys.CONTROL).perform();

		// Wait till child tab opens
		wait.until(ExpectedConditions.numberOfWindowsToBe(2));

		// Switch to child tab
		for (String tab : driver.getWindowHandles()) {
			if (!tab.equals(parent)) {
				driver.switchTo().window(tab);
				break;
			}
		}

		// Simple wait (page is usable)
		wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
		// EXTRA 2 seconds wait
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Close child tab and switch back
		driver.close();
		driver.switchTo().window(parent);
	}

	// TC_04

	public void clickOnAllProductsLatestReleaseAndCloseTab() {

//		js.executeScript("window.scrollBy(0,2000)");

		By latestRelease = By.xpath("//div[contains(@class,'product-bx')]//a[contains(@class,'prd-h-img')]");

		// Parent tab
		String parent = driver.getWindowHandle();

		// Total items
		List<WebElement> items = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(latestRelease));

		for (int i = 0; i < items.size(); i++) {

			// DOM refresh safe
			items = driver.findElements(latestRelease);
			WebElement LMCTAP = items.get(i);

			// Scroll element into view
			js.executeScript("arguments[0].scrollIntoView({block:'center'});", LMCTAP);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// Open in new tab
			act.keyDown(Keys.CONTROL).click(LMCTAP).keyUp(Keys.CONTROL).perform();

			// Wait till child tab opens
			wait.until(ExpectedConditions.numberOfWindowsToBe(2));

			// Switch to child tab
			for (String tab : driver.getWindowHandles()) {
				if (!tab.equals(parent)) {
					driver.switchTo().window(tab);
					break;
				}
			}

			// Page usable wait
			wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// Close child tab and go back
			driver.close();
			driver.switchTo().window(parent);

		}

	}
	// TC_05

	public void clickOnAllPCAndCloseTab() {

//		js.executeScript("window.scrollBy(0,2000)");

		By latestRelease = By.xpath(
				"//div[contains(@class,'cate-left')]//p[text()='Popular Categories']/following-sibling::div//ul//li//a"

		);

		// Parent tab
		String parent = driver.getWindowHandle();

		// Total items
		List<WebElement> items = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(latestRelease));

		for (int i = 0; i < items.size(); i++) {

			// DOM refresh safe
			items = driver.findElements(latestRelease);
			WebElement PCAT = items.get(i);

			// Scroll element into view
			js.executeScript("arguments[0].scrollIntoView({block:'center'});", PCAT);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// Open in new tab
			act.keyDown(Keys.CONTROL).click(PCAT).keyUp(Keys.CONTROL).perform();

			// Wait till child tab opens
			wait.until(ExpectedConditions.numberOfWindowsToBe(2));

			// Switch to child tab
			for (String tab : driver.getWindowHandles()) {
				if (!tab.equals(parent)) {
					driver.switchTo().window(tab);
					break;
				}
			}

			// Page usable wait
			wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// Close child tab and go back
			driver.close();
			driver.switchTo().window(parent);

		}
	}

	// TC_06

	public void clickTGPBannerAndCloseTab() {

		js.executeScript("window.scrollBy(0,4500)");

		WebElement TGPBannerCTA = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("(//div[contains(@class,'hero-item') and not(contains(@class,'slick-cloned'))]//a)[9]")));

		String parent = driver.getWindowHandle();

		// Open in new tab
		act.keyDown(Keys.CONTROL).click(TGPBannerCTA).keyUp(Keys.CONTROL).perform();

		// Wait till child tab opens
		wait.until(ExpectedConditions.numberOfWindowsToBe(2));

		// Switch to child tab
		for (String tab : driver.getWindowHandles()) {
			if (!tab.equals(parent)) {
				driver.switchTo().window(tab);
				break;
			}
		}

		// Simple wait (page is usable)
		wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
		// EXTRA 2 seconds wait
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Close child tab and switch back
		driver.close();
		driver.switchTo().window(parent);

	}

	// TC_07

	public void clickLSBShopNowCTAAndCloseTab() {

		js.executeScript("window.scrollBy(0,6000)");

		WebElement LSBShopNowCT = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@class,'banner-a-btn active')]")));

		String parent = driver.getWindowHandle();

		// Open in new tab
		act.keyDown(Keys.CONTROL).click(LSBShopNowCT).keyUp(Keys.CONTROL).perform();

		// Wait till child tab opens
		wait.until(ExpectedConditions.numberOfWindowsToBe(2));

		// Switch to child tab
		for (String tab : driver.getWindowHandles()) {
			if (!tab.equals(parent)) {
				driver.switchTo().window(tab);
				break;
			}
		}

		// Simple wait (page is usable)
		wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
		// EXTRA 2 seconds wait
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Close child tab and switch back
		driver.close();
		driver.switchTo().window(parent);

	}

	// TC_08
	public void goToNewCategory() {

		js.executeScript("window.scrollTo(0,0)");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		WebElement watchesTab = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@id='new']")));
		act.moveToElement(watchesTab).perform();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement MensnewArrival = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("(//a[@href='/collections/mens-new-arrivals'])[1]")));
		MensnewArrival.click();

	}

}
