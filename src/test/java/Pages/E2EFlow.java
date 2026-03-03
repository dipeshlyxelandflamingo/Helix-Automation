package Pages;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class E2EFlow {

	WebDriver driver;

	HomePage home;
	PLPPage plp;
	PDPPage pdp;
	MiniCartPage miniCart;
	Checkout checkout;

	public E2EFlow(WebDriver driver) {
		this.driver = driver;

		home = new HomePage(driver);
		plp = new PLPPage(driver);
		pdp = new PDPPage(driver);
		miniCart = new MiniCartPage(driver);
		checkout = new Checkout(driver);
	}

	// ✅ ONE COMPLETE USER JOURNEY
	public void runCompleteE2EFlow() throws Throwable {

		// 1️⃣ Homepage → Watches Category (PLP)
		home.goToNewCategory();
		plp.clickFirstProduct();
		pdp.ClickAddToCart();
		// 4️⃣ Mini Cart → Validate not empty + Pincode
		miniCart.enterPincode("201306");
		// 5️⃣ Mini Cart → Quick Checkout
		miniCart.goToQuickCheckout();

		// 6️⃣ Checkout → Verify page
		checkout.fillCheckoutForm();
		checkout.clickPayNow();
		checkout.verifyCheckoutPage();

		System.out.println("🎉 E2E FLOW COMPLETED SUCCESSFULLY");
	}

}
