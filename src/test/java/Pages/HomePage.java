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

	WebDriver driver;
	WebDriverWait wait;
	JavascriptExecutor js;
	Actions act;

	// ✅ Constructor
	public HomePage(WebDriver driver) {
		this.driver = driver;
		boolean isLinux = System.getProperty("os.name", "").toLowerCase().contains("linux");
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(isLinux ? 35 : 20));
		this.js = (JavascriptExecutor) driver;
		this.act = new Actions(driver); 

	}

	// ---------------- Helper Method for New Tab Logic ----------------
	private void openInNewTabAndClose(WebElement element) {
		String parent = driver.getWindowHandle();

		// Open element in new tab
		act.keyDown(Keys.CONTROL).click(element).keyUp(Keys.CONTROL).perform();

		// Wait for new tab
		wait.until(ExpectedConditions.numberOfWindowsToBe(2));

		// Switch to child tab
		for (String tab : driver.getWindowHandles()) {
			if (!tab.equals(parent)) {
				driver.switchTo().window(tab);
				break;
			}
		}

		// Wait until page is loaded
		wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));

		// Optional extra wait
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}

		// Close child tab and switch back
		driver.close();
		driver.switchTo().window(parent);
	}

	// ---------------- TC_01 ----------------
	public void clickmenbestsellerShopNowAndCloseTab() {
		WebElement shopMBNowCTA = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//a[contains(@class,'yb-text-wrapper') and .//p[text()=\"Men's Best Sellers\"]]")));

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center', inline:'nearest'});",
				shopMBNowCTA);

		wait.until(ExpectedConditions.elementToBeClickable(shopMBNowCTA));

		openInNewTabAndClose(shopMBNowCTA);
	}

	// ---------------- TC_02 ----------------
	public void clickwomenbestsellerShopNowAndCloseTab() {
		WebElement shopWBNowCTA = wait.until(ExpectedConditions.visibilityOfElementLocated(By
				.xpath("//a[contains(@class,'yb-text-wrapper') and .//p[contains(text(),\"Women's Best Sellers\")]]")));

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center', inline:'nearest'});",
				shopWBNowCTA);

		wait.until(ExpectedConditions.elementToBeClickable(shopWBNowCTA));

		openInNewTabAndClose(shopWBNowCTA);
	}

	// ---------------- TC_03 ----------------
	public void clickLatestReleaseProductLPFCTAAndCloseTab() {
		WebElement LPFCTA = wait.until(ExpectedConditions.visibilityOfElementLocated(By
				.xpath("(//a[@href='/products/helix-by-timex-grey-octagon-analog-silicone-watch-men-tw041hg13'])[3]")));

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center', inline:'nearest'});",
				LPFCTA);

		wait.until(ExpectedConditions.elementToBeClickable(LPFCTA));

		openInNewTabAndClose(LPFCTA);
	}

	// ---------------- TC_04 ----------------
	public void clickOnAllProductsLatestReleaseAndCloseTab() {
		By latestRelease = By.xpath("//div[contains(@class,'product-bx')]//a[contains(@class,'prd-h-img')]");
		String parent = driver.getWindowHandle();

		// Wait until at least 1 item is present
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(latestRelease));

		int count = driver.findElements(latestRelease).size();

		for (int i = 0; i < count; i++) {

			// Re-fetch list each time (DOM refresh safe)
			List<WebElement> items = driver.findElements(latestRelease);

			// Safety: if list size changed, break
			if (i >= items.size())
				break;

			WebElement element = items.get(i);

			// Scroll element into view (headless-safe)
			((JavascriptExecutor) driver)
					.executeScript("arguments[0].scrollIntoView({block:'center', inline:'nearest'});", element);

			// Wait for it to be clickable instead of sleep
			wait.until(ExpectedConditions.elementToBeClickable(element));

			// Open in new tab and close (your existing helper)
			openInNewTabAndClose(element);

			// Ensure we're back on parent and page is stable
			driver.switchTo().window(parent);
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(latestRelease));
		}
	}


	

	// ---------------- TC_05 ----------------
	public void clickTGPBannerAndCloseTab() {
		WebElement TGPBannerCTA = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
				"//a[@href='https://helixwatches-store.myshopify.com/collections/bestsellers?usf_sort=bestselling']")));

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center', inline:'nearest'});",
				TGPBannerCTA);

		wait.until(ExpectedConditions.elementToBeClickable(TGPBannerCTA));

		openInNewTabAndClose(TGPBannerCTA);
	}

	// ---------------- TC_06
	public void clickLSBShopNowCTAAndCloseTab() {
		WebElement LSBShopNowCT = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//a[contains(@class,'banner-a-btn') and contains(@class,'active')]")));

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center', inline:'nearest'});",
				LSBShopNowCT);

		wait.until(ExpectedConditions.elementToBeClickable(LSBShopNowCT));

		openInNewTabAndClose(LSBShopNowCT);
	}

	// ---------------- TC_07 ----------------
	public void goToNewCategory() {
		// Top pe le aao (headless safe)
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0,0);");

		// Watches tab
		WebElement watchesTab = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='new']")));

		// Hover (Actions try), fallback JS mouseover for headless
		try {
			new org.openqa.selenium.interactions.Actions(driver).moveToElement(watchesTab)
					.pause(java.time.Duration.ofMillis(300)).perform();
		} catch (Exception e) {
			((JavascriptExecutor) driver).executeScript(
					"arguments[0].dispatchEvent(new MouseEvent('mouseover', {bubbles:true}));", watchesTab);
		}

		// Men's New Arrivals
		By mensNewArrivalBy = By.xpath("(//a[@href='/collections/mens-new-arrivals'])[1]");
		WebElement mensNewArrival = wait.until(ExpectedConditions.elementToBeClickable(mensNewArrivalBy));

		// Click (normal), fallback JS click if intercepted
		try {
			mensNewArrival.click();
		} catch (org.openqa.selenium.ElementClickInterceptedException ex) {
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", mensNewArrival);
		}

	}
}