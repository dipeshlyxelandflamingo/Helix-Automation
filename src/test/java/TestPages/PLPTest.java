package TestPages;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Base.BaseClass;
import Pages.HomePage;
import Pages.PLPPage;

public class PLPTest extends BaseClass {

	HomePage home;
	PLPPage plp;

	@BeforeClass
	public void init() {

		home = new HomePage(driver);
		home.goToNewCategory();
		plp = new PLPPage(driver);
	}

	 @Test(priority = 1)
	    public void TC_01_ClickOnDialShapeFilter() {
	        try {
	            plp.ClickDialShapeFilter("Square", 1);
	        } catch (Exception e) {
	            Assert.fail("Failed to Click on Dial Shape Filter");
	        }
	    }

	    @Test(priority = 2)
	    public void TC_02_ClickOnAttachmentTypeFilter() {
	        try {
	            plp.ClickAttachmentTypeFilter("Resin", 2);
	        } catch (Exception e) {
	            Assert.fail("Failed to Click on Attachment Type Filter");
	        }
	    }

	    @Test(priority = 3)
	    public void TC_03_ClickOnDialColorFilter() {
	        try {
	            plp.ClickDialColorFilter("Blue", 19);
	        } catch (Exception e) {
	            Assert.fail("Failed to Click on Dial Color Filter");
	        }
	    }

	    @Test(priority = 4)
	    public void TC_04_ClickOnStrapColorFilter() {
	        try {
	            plp.ClickStrapColorFilter("Black (20)", 20);
	        } catch (Exception e) {
	            Assert.fail("Failed to Click on Strap Color Filter");
	        }
	    }

	    @Test(priority = 5)
	    public void TC_05_ClickOnFirstProduct() {
	        try {
	            plp.clickFirstProduct();
	        } catch (Exception e) {
	            Assert.fail("Failed to Click on First Product");
	        }
	    }
	}