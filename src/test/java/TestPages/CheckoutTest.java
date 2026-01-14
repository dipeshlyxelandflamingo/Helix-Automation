package TestPages;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.testng.annotations.BeforeClass;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Base.BaseClass;
import Pages.Checkout;
import Pages.HomePage;
import Pages.MiniCartPage;
import Pages.PDPPage;
import Pages.PLPPage;

public class CheckoutTest extends BaseClass {

	HomePage home;
	Checkout checkout;
	PLPPage plp;
	PDPPage pdp;
	MiniCartPage miniCart;

	@BeforeClass
	public void initFlow() {

		home = new HomePage(driver);
		home.goToNewCategory();

		plp = new PLPPage(driver);
		plp.clickFirstProduct();

		pdp = new PDPPage(driver);
		pdp.ClickAddToCart();

		miniCart = new MiniCartPage(driver);
		miniCart.enterPincode("201306");
		miniCart.goToQuickCheckout();

		checkout = new Checkout(driver, wait);
	}

	@Test(priority = 1)
	public void TC_01_verifyCheckoutPage() {
		try {
			boolean isVisible = checkout.verifyCheckoutPage();
			Assert.assertTrue(isVisible, "Checkout page not opened");
		} catch (Exception e) {
			Assert.fail("Failed to verify Checkout page visibility");
		}
	}

	@Test(priority = 2, dependsOnMethods = { "TC_01_verifyCheckoutPage" })
	public void TC_02_fillCheckoutForm() {
		try {
			checkout.fillCheckoutForm();
		} catch (Exception e) {
			Assert.fail("Failed to fill checkout form");
		}
	}

	@Test(priority = 3, dependsOnMethods = { "TC_02_fillCheckoutForm" })
	public void TC_03_clickPayNow() {
		try {
			checkout.clickPayNow();
		} catch (Exception e) {
			Assert.fail("Failed to click on Pay Now button");
		}
	}
}
