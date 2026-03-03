package TestPages;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Base.BaseClass;
import Pages.Checkout;
import Pages.HomePage;
import Pages.MiniCartPage;
import Pages.PDPPage;
import Pages.PLPPage;
import listeners.Test_listeners;

@Listeners(Test_listeners.class)
@Test(groups = { "CheckOut" })
public class CheckoutTest extends BaseClass {

	PDPPage pdp;
	MiniCartPage miniCart;
	Checkout checkout;

	@BeforeClass(alwaysRun = true)
	public void initFlow() {
		driver.get("https://helix-watches.com//products/helix-men-green-square-dial-quartz-analog-watch-tw057hg01");
		pdp = new PDPPage(driver);
		pdp.ClickAddToCart();

		miniCart = new MiniCartPage(driver);
		miniCart.enterPincode("201306");
		miniCart.goToQuickCheckout();
		checkout = new Checkout(driver);
	}

	@Test(priority = 1, groups = { "CheckOut" })
	public void TC_01_verifyCheckoutPage() {
		try {
			boolean isVisible = checkout.verifyCheckoutPage();
			Assert.assertTrue(isVisible, "Checkout page not opened");
		} catch (Exception e) {
			Assert.fail("Failed to verify Checkout page visibility"+e.getMessage());
		}
	}

	@Test(priority = 2, groups = { "CheckOut" })
	public void TC_02_fillCheckoutForm() {
		try {
			checkout.fillCheckoutForm();
		} catch (Exception e) {
			Assert.fail("Failed to fill checkout form"+e.getMessage());
		}
	}

	@Test(priority = 3, groups = { "CheckOut" })
	public void TC_03_clickPayNow() {
		try {
			checkout.clickPayNow();
		} catch (Exception e) {
			Assert.fail("Failed to click on Pay Now button"+e.getMessage());
		}
	}

	@Test(priority = 4, groups = { "CheckOut" })
	public void VerifyCheckoutEnable() {
		// Checkout page object
		Checkout checkout = new Checkout(driver);

		// Hard assert: agar false → test FAIL ho jaaye
		Assert.assertTrue(checkout.checkoutVerify(), "Secure Checkout is not visible or not enabled");
	}
}
