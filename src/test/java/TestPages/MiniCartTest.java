package TestPages;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Base.BaseClass;
import Pages.HomePage;
import Pages.MiniCartPage;
import Pages.PDPPage;
import Pages.PLPPage;

public class MiniCartTest extends BaseClass {

	HomePage home;
	PLPPage plp;
	PDPPage pdp;
	MiniCartPage miniCart;

	@BeforeClass
	public void initFlow() {

		// Navigate to category
		home = new HomePage(driver);
		home.goToNewCategory();

		// Select a product from PLP
		plp = new PLPPage(driver);
		plp.clickFirstProduct();

		// Add product to cart from PDP
		pdp = new PDPPage(driver);
		pdp.ClickAddToCart();

		// Initialize MiniCartPage
		miniCart = new MiniCartPage(driver);
	}

	@Test(priority = 1)
	public void TC_01_QuantityIncrease() {
		try {
			miniCart.increaseQuantity();
		} catch (Exception e) {
			Assert.fail("Failed to Increase Quantity On Product");
		}
	}

	@Test(priority = 2)
	public void TC_02_QuantityDecrease() {
		try {
			miniCart.decreaseQuantity();
		} catch (Exception e) {
			Assert.fail("Failed to Decrease Quantity On Product");
		}
	}

	@Test(priority = 3)
	public void TC_03_EnterPincode() {
		try {
			miniCart.enterPincode("201306");
		} catch (Exception e) {
			Assert.fail("Failed to Enter Pincode");
		}
	}

	@Test(priority = 4)
	public void TC_04_ClickOnCheckoutButton() {
		try {
			miniCart.goToQuickCheckout();
		} catch (Exception e) {
			Assert.fail("Failed to Click On Checkout Button");
		}
	}
}