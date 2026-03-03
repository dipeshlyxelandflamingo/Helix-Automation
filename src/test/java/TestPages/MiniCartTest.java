package TestPages;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import Base.BaseClass;
import Pages.MiniCartPage;
import Pages.PDPPage;
import listeners.Test_listeners;

@Listeners(Test_listeners.class)
@Test(groups = { "MiniCart" })
public class MiniCartTest extends BaseClass {

	PDPPage pdp;
	MiniCartPage miniCart;

	@BeforeClass(alwaysRun = true)
	public void initFlow() {
		driver.get("https://helix-watches.com//products/helix-men-green-square-dial-quartz-analog-watch-tw057hg01");
		// Add product to cart from PDP
		pdp = new PDPPage(driver);
		pdp.ClickAddToCart();

		// Initialize MiniCartPage
		miniCart = new MiniCartPage(driver);

	}

	@Test(priority = 1, groups = { "MiniCart" })
	public void TC_01_QuantityIncrease() {
		try {
			miniCart.increaseQuantity();
		} catch (Exception e) {
			Assert.fail("Failed to Increase Quantity On Product"+e.getMessage());
		}
	}

	@Test(priority = 2, groups = { "MiniCart" })
	public void TC_02_QuantityDecrease() {
		try {
			miniCart.decreaseQuantity();
		} catch (Exception e) {
			Assert.fail("Failed to Decrease Quantity On Product"+e.getMessage());
		}
	}

	@Test(priority = 3, groups = { "MiniCart" })
	public void TC_03_EnterPincode() {
		try {
			miniCart.enterPincode("201306");
		} catch (Exception e) {
			Assert.fail("Failed to Enter Pincode"+e.getMessage());
		}
	}

	@Test(priority = 4, groups = { "MiniCart" })

	public void TC_04_ClickOnCheckoutButton() {
		try {
			miniCart.goToQuickCheckout();
		} catch (Exception e) {
			Assert.fail("Failed to Click On Checkout Button"+e.getMessage());
		}
	}
}