package TestPages;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import Base.BaseClass;
import Pages.HomePage;
import Pages.PDPPage;
import Pages.PLPPage;
import listeners.Test_listeners;

@Listeners(Test_listeners.class)
@Test(groups = { "PDP" })
public class PDPTest extends BaseClass {

	PDPPage pdp;

	@BeforeClass(alwaysRun = true)
	public void init() {
		pdp = new PDPPage(driver);
		pdp.openPDP();
	}

	@Test(priority = 1, groups={"PDP"})
	public void TC_01_clickOnRecommondedProductsList() {
		try {
			pdp.clickRecommondedProducts();
		} catch (Exception e) {
			Assert.fail("Failed to Click on Recommended Products List"+e.getMessage());
		}
	}

	@Test(priority = 2, groups={"PDP"})
	public void TC_02_AddProductInCart() {
		try {
			pdp.ClickAddToCart();
		} catch (Exception e) {
			Assert.fail("Failed to Click on Add Product In Cart"+e.getMessage());
		}
	}
}