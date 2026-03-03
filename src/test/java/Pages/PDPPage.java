package Pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

	private static final String PDP_URL = "https://helix-watches.com/products/helix-men-green-square-dial-quartz-analog-watch-tw057hg01";
	private static final String PDP_URL_CONTAINS = "products/helix-men-green-square-dial-quartz-analog-watch-tw057hg01";

	public void openPDP() {
		ensureOnPDP();
	}

	private void ensureOnPDP() {
		String currentUrl = driver.getCurrentUrl();

		if (currentUrl == null || !currentUrl.contains(PDP_URL_CONTAINS)) {
			System.out.println("⚠️ Not on PDP. Navigating directly to PDP.");
			driver.get(PDP_URL);
		} else {
			System.out.println("✔ Already on PDP.");
		}
	}

	public PDPPage(WebDriver driver) {
		this.driver = driver;
		boolean isLinux = System.getProperty("os.name", "").toLowerCase().contains("linux");
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(isLinux ? 35 : 20));
		this.js = (JavascriptExecutor) driver;

	}

	public void clickRecommondedProducts() {

		By productsBy = By.xpath("//form[@class='home-cat-iteam']");

		// Wait until products are visible
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productsBy));

		String parent = driver.getWindowHandle();

		int count = driver.findElements(productsBy).size();

		for (int i = 0; i < count; i++) {

			// Re-fetch list each iteration (DOM refresh safe)
			List<WebElement> products = driver.findElements(productsBy);
			if (i >= products.size())
				break;

			WebElement product = products.get(i);

			// Scroll element into view (headless-safe)
			((JavascriptExecutor) driver)
					.executeScript("arguments[0].scrollIntoView({block:'center', inline:'nearest'});", product);

			wait.until(ExpectedConditions.elementToBeClickable(product));

			// Get link URL from inside form (adjust selector if needed)
			WebElement link = product.findElement(By.cssSelector("a[href]"));
			String href = link.getAttribute("href");

			if (href == null || href.isEmpty()) {
				// Fallback: click normally if href not found
				link.click();
				driver.navigate().back();
				wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productsBy));
				continue;
			}

			// Open in new tab via JS (most stable in headless)
			((JavascriptExecutor) driver).executeScript("window.open(arguments[0], '_blank');", href);

			// Switch to new tab
			wait.until(d -> d.getWindowHandles().size() > 1);
			for (String tab : driver.getWindowHandles()) {
				if (!tab.equals(parent)) {
					driver.switchTo().window(tab);
					break;
				}
			}

			// Close new tab and return
			driver.close();
			driver.switchTo().window(parent);

			// Ensure page is stable again
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productsBy));
		}
	}

	public void ClickAddToCart() {
		try {
	        By addToCartBy = By.xpath("//button[contains(@class,'add-tocart-btn')]");

	        // Go to top first
	        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");

	        // If button is slightly below top, give a small scroll
	        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 250);");

	        WebElement addToCart = wait.until(ExpectedConditions.visibilityOfElementLocated(addToCartBy));

	        // Bring it nicely into view (works in headless too)
	        ((JavascriptExecutor) driver)
	                .executeScript("arguments[0].scrollIntoView({block:'center', inline:'nearest'});", addToCart);

	        wait.until(ExpectedConditions.elementToBeClickable(addToCartBy));

	        try {
	            addToCart.click();
	        } catch (Exception ex) {
	            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addToCart);
	        }

	    } catch (Exception e) {
	        throw new RuntimeException("❌ Failed to click Add To Cart button on PDP", e);
	    }
	}
	
}