package TestPages;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Base.BaseClass;
import Pages.HomePage;
import Pages.MiniCartPage;
import Pages.PDPPage;
import Pages.PLPPage;

public class MiniCartTest extends BaseClass {

	// HomePage home;
	PLPPage plp;
	PDPPage pdp;
	MiniCartPage miniCart;

	@BeforeClass
	public void initFlow() {

		// Navigate to category
		// home = new HomePage(driver);
		// home.goToWatchesCategory();

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
	public void TC_01_QuantityIncrease() throws Exception {

		miniCart.increaseQuantity();

	}
	
	@Test(priority = 2)
	public void TC_02_QuantityDecrease() throws Exception {

		miniCart.decreaseQuantity();
	}
	
	@Test(priority = 3)
	public void TC_03_EnterPincode() throws Exception {

		miniCart.enterPincode("201306");
	}
	
	@Test(priority = 4)
	public void TC_04_ClickOnCheckout() throws Exception {

		miniCart.goToQuickCheckout();
	}
	

}