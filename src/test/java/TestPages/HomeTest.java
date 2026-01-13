package TestPages;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Base.BaseClass;
import Pages.Homepage;

public class HomeTest extends BaseClass {

	Homepage home;

	@BeforeClass
	public void setUpHomePage() {
		home = new Homepage(driver);
	}

	@Test(priority = 1)
	public void clickOnMenBestSeller() {

		try {
			home.clickmenbestsellerShopNowAndCloseTab();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test(priority = 2)
	public void clickOnWomenBestSeller() {

		try {
			home.clickwomenbestsellerShopNowAndCloseTab();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test(priority = 3)
	public void clickLatestReleaseProduct() {

		try {
			home.clickLatestReleaseProductLPFCTAAndCloseTab();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	@Test(priority = 4)
	public void clickOnAllProductsLatestReleaseAndCloseTab() {

		try {
			home.clickOnAllProductsLatestReleaseAndCloseTab();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@Test(priority = 5)
	public void clickOnAllPCAndCloseTab() {

		try {
			home.clickOnAllPCAndCloseTab();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@Test(priority = 6)
	public void clickTGPBannerAndCloseTab() {

		try {
			home.clickTGPBannerAndCloseTab();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@Test(priority = 7)
	public void clickLSBShopNowCTAAndCloseTab() {

		try {
			home.clickLSBShopNowCTAAndCloseTab();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@Test(priority = 8)
	public void goToNewCategory() {

		try {
			home.goToNewCategory();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
