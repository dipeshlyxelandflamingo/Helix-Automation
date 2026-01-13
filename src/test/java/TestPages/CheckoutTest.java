package TestPages;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import Base.BaseClass;
import Pages.Checkout;
import Pages.Homepage;

public class CheckoutTest extends BaseClass {

	Homepage home;
	Checkout checkout;

	@BeforeClass
	public void initFlow() throws Exception {

		home = new Homepage(driver);
		home.goToNewCategory(); // navigates till checkout page

		checkout = new Checkout(driver, wait);
	}

	@Test(priority = 1)
	public void TC_01_verifyCheckoutPage() {
		checkout.verifyCheckoutPage();
	}

	@Test(priority = 2, dependsOnMethods = { "TC_01_verifyCheckoutPage" })
	public void TC_02_fillCheckoutForm() {
		try {
			checkout.fillCheckoutForm();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Checkout form fill failed");
		}
	}

	@Test(priority = 3, dependsOnMethods = { "TC_02_fillCheckoutForm" })
	public void TC_03_clickPayNow() {
		try {
			checkout.clickPayNow();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Pay Now click failed");
		}
	}
}
