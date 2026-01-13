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

	public PLPPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		this.js = (JavascriptExecutor) driver;
		this.act = new Actions(driver);
	}

	public void ClickDialShapeFilter(String filterValue, int expectedCount) {
		WebElement OpenDialShapeFilter = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//div[@class='usf-title usf-no-select']//button[contains(text(),'Dial Shape')]")));
		js.executeScript("arguments[0].scrollIntoView({block:'center'});", OpenDialShapeFilter);
		js.executeScript("arguments[0].click();", OpenDialShapeFilter);

		WebElement SelectDialShapeFilter = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//div[contains(@class,'usf-facet-values')]//button[.//span[normalize-space()='" + filterValue
						+ "']]")));
		js.executeScript("arguments[0].scrollIntoView({block:'center'});", SelectDialShapeFilter);
		js.executeScript("arguments[0].click();", SelectDialShapeFilter);

		js.executeScript("arguments[0].scrollIntoView({block:'center'});", OpenDialShapeFilter);
		js.executeScript("arguments[0].click();", OpenDialShapeFilter);

		waitForProductsToLoad();
		validateProductCount(expectedCount);

		ClickOnClearbutton();
		waitForProductsToLoad();

	}

	public void ClickAttachmentTypeFilter(String filterValue, int expectedCount) {

		WebElement OpenAttachmentTypeFilter = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//div[@class='usf-title usf-no-select']//button[contains(text(),' Attachment Type')]")));
		js.executeScript("arguments[0].scrollIntoView({block:'center'});", OpenAttachmentTypeFilter);
		js.executeScript("arguments[0].click();", OpenAttachmentTypeFilter);

		WebElement SelectAttachmentTypeFilter = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//div[contains(@class,'usf-facet-values')]//button[.//span[normalize-space()='" + filterValue
						+ "']]")));
		js.executeScript("arguments[0].scrollIntoView({block:'center'});", SelectAttachmentTypeFilter);
		js.executeScript("arguments[0].click();", SelectAttachmentTypeFilter);

		js.executeScript("arguments[0].scrollIntoView({block:'center'});", OpenAttachmentTypeFilter);
		js.executeScript("arguments[0].click();", OpenAttachmentTypeFilter);

		waitForProductsToLoad();
		validateProductCount(expectedCount);

		ClickOnClearbutton();
		waitForProductsToLoad();

	}

	public void ClickDialColorFilter(String filterValue, int expectedCount) {

		WebElement OpenDialColorFilter = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//div[@class='usf-title usf-no-select']//button[contains(text(),'Dial Color')]")));
		js.executeScript("arguments[0].scrollIntoView({block:'center'});", OpenDialColorFilter);
		js.executeScript("arguments[0].click();", OpenDialColorFilter);

		WebElement SelectDialColorFilter = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//div[contains(@class,'usf-facet-values')]//button[.//span[normalize-space()='" + filterValue
						+ "']]")));
		js.executeScript("arguments[0].scrollIntoView({block:'center'});", SelectDialColorFilter);
		js.executeScript("arguments[0].click();", SelectDialColorFilter);

		js.executeScript("arguments[0].scrollIntoView({block:'center'});", OpenDialColorFilter);
		js.executeScript("arguments[0].click();", OpenDialColorFilter);

		waitForProductsToLoad();
		validateProductCount(expectedCount);

		ClickOnClearbutton();
		waitForProductsToLoad();

	}

	public void ClickStrapColorFilter(String filterValue, int expectedCount) {

		WebElement OpenStrapColorFilter = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//div[@class='usf-title usf-no-select']//button[contains(text(),'Strap Color')]")));
		js.executeScript("arguments[0].scrollIntoView({block:'center'});", OpenStrapColorFilter);
		js.executeScript("arguments[0].click();", OpenStrapColorFilter);

		WebElement SelectStrapColorFilter = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//div[contains(@class,'usf-facet-values--Swatch')]//button[@title='" + filterValue + "']")));
		js.executeScript("arguments[0].scrollIntoView({block:'center'});", SelectStrapColorFilter);
		js.executeScript("arguments[0].click();", SelectStrapColorFilter);

		js.executeScript("arguments[0].scrollIntoView({block:'center'});", OpenStrapColorFilter);
		js.executeScript("arguments[0].click();", OpenStrapColorFilter);

		waitForProductsToLoad();
		validateProductCount(expectedCount);

		ClickOnClearbutton();
		waitForProductsToLoad();

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
		WebElement summary = driver.findElement(By.xpath("//span[contains(@class,'usf-sr-summary')]/b"));
		String summaryText = summary.getText();
		int actualCount = Integer.parseInt(summaryText.replaceAll("[^0-9]", ""));
		Assert.assertEquals(actualCount, expectedCount, "Expected product count does not match filtered result");
	}

	private void ClickOnClearbutton() {
		WebElement clearBtn = driver.findElement(By.xpath("(//button[@class='usf-clear-all usf-btn'])[1]"));
		js.executeScript("arguments[0].scrollIntoView({block:'center'});", clearBtn);
		js.executeScript("arguments[0].click();", clearBtn);

	}

}
