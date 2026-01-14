package TestPages;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Base.BaseClass;
import Pages.HomePage;
import Pages.PDPPage;
import Pages.PLPPage;

public class PDPTest extends BaseClass {

	HomePage home;
	PLPPage plp;
	PDPPage pdp;

	@BeforeClass
	public void init() {

		home = new HomePage(driver);
		home.goToNewCategory();
		plp = new PLPPage(driver);
		plp = new PLPPage(driver);
		plp.clickFirstProduct();
		pdp = new PDPPage(driver);
	}

	@Test(priority = 1)
	public void TC_01_clickOnRecommondedProductsList() {
		try {
			pdp.clickRecommondedProducts();
		} catch (Exception e) {
			Assert.fail("Failed to Click on Recommended Products List");
		}
	}

	@Test(priority = 2)
	public void TC_02_AddProductInCart() {
		try {
			pdp.ClickAddToCart();
		} catch (Exception e) {
			Assert.fail("Failed to Click on Add Product In Cart");
		}
	}
}