package TestPages;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Base.BaseClass;
import Pages.PDPPage;
import Pages.PLPPage;

public class PDPTest extends BaseClass {

	// HomePage home;
	PLPPage plp;
	PDPPage pdp;

	@BeforeClass
	public void init() throws Exception {

		// home = new HomePage(driver);

		plp = new PLPPage(driver);
		plp.clickFirstProduct();
		pdp = new PDPPage(driver);
	}

	@Test(priority = 1)
	public void TC_01_clickOnRecommondedProducts() throws Exception {
		pdp.clickRecommondedProducts();
	}

	
	@Test(priority = 2)
	public void TC_02_AddProductInCart() {
		pdp.ClickAddToCart();
	}

}
