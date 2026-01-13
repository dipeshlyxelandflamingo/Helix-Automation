package TestPages;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Base.BaseClass;
import Pages.PLPPage;

public class PLPTest extends BaseClass {

	// HomePage home;
	PLPPage plp;

	@BeforeClass
	public void init() throws Exception {

		// home = new HomePage(driver);

		plp = new PLPPage(driver);
	}

	@Test(priority = 1)
	public void TC_01_ClickOnDialShapeFilter() {
		plp.ClickDialShapeFilter("Square", 1);
	}

	@Test(priority = 2)
	public void TC_02_ClickOnAttachmentTypeFilter() {
		plp.ClickAttachmentTypeFilter("Resin", 2);
	}

	@Test(priority = 3)
	public void TC_03_ClickOnDialColorFilter() {
		plp.ClickDialColorFilter("Blue", 19);
	}

	@Test(priority = 4)
	public void TC_04_ClickOnStrapColorFilter() {
		plp.ClickStrapColorFilter("Black (20)", 20);
	}
	
	
	@Test(priority = 5)
	public void TC_05_ClickOnFirstProduct() {
		plp.clickFirstProduct();
	}

}
