package Pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class PLPPage {

	WebDriver driver;
	WebDriverWait wait;
	JavascriptExecutor js;
	Actions act;

	private static final String PLP_URL = "https://helix-watches.com/collections/mens-new-arrivals?usf_sort=bestselling";
	private static final String PLP_URL_CONTAINS = "/collections/mens-new-arrivals";

	public void openPLP() {
		ensureOnPLP();
	}

	private void ensureOnPLP() {
		String currentUrl = driver.getCurrentUrl();

		if (currentUrl == null || !currentUrl.contains(PLP_URL_CONTAINS)) {
			System.out.println("⚠️ Not on PLP. Navigating directly to PLP.");
			driver.get(PLP_URL);
		} else {
			System.out.println("✔ Already on PLP.");
		}
	}

	public PLPPage(WebDriver driver) {
		this.driver = driver;
		boolean isLinux = System.getProperty("os.name", "").toLowerCase().contains("linux");
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(isLinux ? 35 : 20));
		this.js = (JavascriptExecutor) driver;

	}

	public void ClickDialShapeFilter(String filterValue, int expectedCount) throws Exception {
		WebElement OpenDialShapeFilter = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//div[@class='usf-title usf-no-select']//button[contains(text(),'Dial Shape')]")));
		js.executeScript("arguments[0].scrollIntoView({block:'center'});", OpenDialShapeFilter);
		js.executeScript("arguments[0].click();", OpenDialShapeFilter);

		WebElement SelectDialShapeFilter = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//div[contains(@class,'usf-facet-values')]//button[.//span[normalize-space()='" + filterValue
						+ "']]")));
		js.executeScript("arguments[0].scrollIntoView({block:'center'});", SelectDialShapeFilter);
		js.executeScript("arguments[0].click();", SelectDialShapeFilter);

		waitForProductsToLoad();
		validateProductCount(expectedCount);
		Thread.sleep(2000);
		ClickOnClearbutton();

		WebElement CloseDialShapeFilter = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//div[@class='usf-title usf-no-select']//button[contains(text(),'Dial Shape')]")));
		js.executeScript("arguments[0].scrollIntoView({block:'center'});", CloseDialShapeFilter);
		js.executeScript("arguments[0].click();", CloseDialShapeFilter);

	}

	public void ClickAttachmentTypeFilter(String filterValue, int expectedCount) throws Exception {

		WebElement OpenAttachmentTypeFilter = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//div[@class='usf-title usf-no-select']//button[contains(text(),' Attachment Type')]")));
		js.executeScript("arguments[0].scrollIntoView({block:'center'});", OpenAttachmentTypeFilter);
		js.executeScript("arguments[0].click();", OpenAttachmentTypeFilter);

		WebElement SelectAttachmentTypeFilter = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//div[contains(@class,'usf-facet-values')]//button[.//span[normalize-space()='" + filterValue
						+ "']]")));
		js.executeScript("arguments[0].scrollIntoView({block:'center'});", SelectAttachmentTypeFilter);
		js.executeScript("arguments[0].click();", SelectAttachmentTypeFilter);

		waitForProductsToLoad();
		validateProductCount(expectedCount);
		Thread.sleep(800);
		ClickOnClearbutton();
		waitForProductsToLoad();

		js.executeScript("arguments[0].scrollIntoView({block:'center'});", OpenAttachmentTypeFilter);
		js.executeScript("arguments[0].click();", OpenAttachmentTypeFilter);

	}

	public void ClickDialColorFilter(String filterValue, int expectedCount) throws Exception {

		WebElement OpenDialColorFilter = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//div[@class='usf-title usf-no-select']//button[contains(text(),'Dial Color')]")));
		js.executeScript("arguments[0].scrollIntoView({block:'center'});", OpenDialColorFilter);
		Thread.sleep(800);
		js.executeScript("arguments[0].click();", OpenDialColorFilter);

		WebElement SelectDialColorFilter = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//div[contains(@class,'usf-facet-values')]//button[.//span[normalize-space()='" + filterValue
						+ "']]")));
		js.executeScript("arguments[0].scrollIntoView({block:'center'});", SelectDialColorFilter);
		Thread.sleep(800);
		js.executeScript("arguments[0].click();", SelectDialColorFilter);

		waitForProductsToLoad();
		validateProductCount(expectedCount);
		Thread.sleep(800);
		ClickOnClearbutton();
		waitForProductsToLoad();

		js.executeScript("arguments[0].scrollIntoView({block:'center'});", OpenDialColorFilter);
		Thread.sleep(800);
		js.executeScript("arguments[0].click();", OpenDialColorFilter);

	}

	public void ClickStrapColorFilter(String filterValue, int expectedCount) throws Exception {

		WebElement OpenStrapColorFilter = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//div[@class='usf-title usf-no-select']//button[contains(text(),'Strap Color')]")));
		js.executeScript("arguments[0].scrollIntoView({block:'center'});", OpenStrapColorFilter);
		Thread.sleep(800);
		js.executeScript("arguments[0].click();", OpenStrapColorFilter);

		WebElement SelectStrapColorFilter = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//div[contains(@class,'usf-facet-values--Swatch')]//button[@title='" + filterValue + "']")));
		js.executeScript("arguments[0].scrollIntoView({block:'center'});", SelectStrapColorFilter);
		Thread.sleep(800);
		js.executeScript("arguments[0].click();", SelectStrapColorFilter);

		waitForProductsToLoad();
		validateProductCount(expectedCount);
		Thread.sleep(800);
		ClickOnClearbutton();
		waitForProductsToLoad();

		js.executeScript("arguments[0].scrollIntoView({block:'center'});", OpenStrapColorFilter);
		Thread.sleep(800);
		js.executeScript("arguments[0].click();", OpenStrapColorFilter);

	}

	public void clickFirstProduct() {
		WebElement product = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"//a[@href='/collections/mens-new-arrivals/products/helix-men-green-square-dial-quartz-analog-watch-tw057hg01']")));
		js.executeScript("arguments[0].scrollIntoView({block:'center'});", product);
		js.executeScript("arguments[0].click();", product);
	}

	private void waitForProductsToLoad() {
		try {
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".usf-loader")));
		} catch (Exception e) {
			// Ignore if loader not found
		}
	}

	private void validateProductCount(int expectedCount) {

	    By summaryBy = By.xpath("//span[contains(@class,'usf-sr-summary')]");

	    // Wait until summary appears and text is populated
	    WebElement summary = wait.until(
	        ExpectedConditions.visibilityOfElementLocated(summaryBy)
	    );

	    // Wait until it contains number + products
	    wait.until(ExpectedConditions.textToBePresentInElement(summary, "products"));

	    String summaryText = summary.getText();   // e.g. "1 products"

	    int actualCount = Integer.parseInt(summaryText.replaceAll("[^0-9]", ""));

	    Assert.assertEquals(
	        actualCount,
	        expectedCount,
	        "Expected product count does not match filtered result"
	    );
	}

	private void ClickOnClearbutton() {
		By clearBtnBy = By.xpath("//button[@aria-label='Clear all filters' and contains(@class,'usf-clear-all')]");

		WebElement clearBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(clearBtnBy));

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center', inline:'nearest'});",
				clearBtn);

		// Wait until button is actually clickable
		wait.until(ExpectedConditions.elementToBeClickable(clearBtnBy));
		

		// Re-locate after wait (DOM refresh safe)
		clearBtn = driver.findElement(clearBtnBy);

		try {
			clearBtn.click(); // normal click first
		} catch (Exception e) {
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", clearBtn); // JS fallback
		}
	}

}
