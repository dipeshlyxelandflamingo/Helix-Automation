package TestPages;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import Base.BaseClass;
import Pages.PLPPage;
import listeners.Test_listeners;

@Listeners(Test_listeners.class)
@Test(groups = { "PLP" })
public class PLPTest extends BaseClass {

	PLPPage plp;

	@BeforeClass(alwaysRun = true)
	public void init() {
		plp = new PLPPage(driver);
		plp.openPLP();
	}

	@Test(priority = 1, groups = { "PLP" })
	public void TC_01_ClickOnDialShapeFilter() {
		try {
			plp.ClickDialShapeFilter("Square", 1);
		} catch (Exception e) {
			Assert.fail("Failed to Click on Dial Shape Filter" + e.getMessage());
		}
	}

	@Test(priority = 2, groups = { "PLP" })
	public void TC_02_ClickOnAttachmentTypeFilter() {
		try {
			plp.ClickAttachmentTypeFilter("Resin", 2);
		} catch (Exception e) {
			Assert.fail("Failed to Click on Attachment Type Filter" + e.getMessage());
		}
	}

	@Test(priority = 3, groups = { "PLP" })
	public void TC_03_ClickOnDialColorFilter() {
		try {
			plp.ClickDialColorFilter("Blue", 19);
		} catch (Exception e) {
			Assert.fail("Failed to Click on Dial Color Filter" + e.getMessage());
		}
	}

	@Test(priority = 4, groups = { "PLP" })
	public void TC_04_ClickOnStrapColorFilter() {
		try {
			plp.ClickStrapColorFilter("Black (20)", 20);
		} catch (Exception e) {
			Assert.fail("Failed to Click on Strap Color Filter" + e.getMessage());
		}
	}

	@Test(priority = 5, groups = { "PLP" })
	public void TC_05_ClickOnFirstProduct() {
		try {
			plp.clickFirstProduct();
		} catch (Exception e) {
			Assert.fail("Failed to Click on First Product" + e.getMessage());
		}
	}
}