package Pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PDPPage {

	WebDriver driver;
	WebDriverWait wait;
	JavascriptExecutor js;
	Actions act;

	public PDPPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		this.js = (JavascriptExecutor) driver;
		this.act = new Actions(driver);
	}

	public void clickRecommondedProducts() throws Exception {

		js.executeScript("window.scrollBy(0,2900)");

		List<WebElement> products = wait.until(
				ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//form[@class='home-cat-iteam']")));

		for (WebElement product : products) {
			String parent = driver.getWindowHandle();

			act.keyDown(Keys.CONTROL).click(product).keyUp(Keys.CONTROL).perform();
			Thread.sleep(1000);
			for (String tab : driver.getWindowHandles()) {
				if (!tab.equals(parent)) {
					driver.switchTo().window(tab);
					break;
				}
			}

			driver.close();
			driver.switchTo().window(parent);
		}

	}

	

	public void ClickAddToCart() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		js.executeScript("window.scrollBy(0,900)");
		WebElement AddToCart = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='button button--action product-details__sticky-atc-add-btn']")));
		AddToCart.click();
	}

}
