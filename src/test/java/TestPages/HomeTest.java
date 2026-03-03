package TestPages;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import Base.BaseClass;
import Pages.HomePage;
import listeners.Test_listeners;

@Listeners(Test_listeners.class)
@Test(groups = { "Homepage" })
public class HomeTest extends BaseClass {

	HomePage home;

	@BeforeClass(alwaysRun = true)
	public void setUpHomePage() {
		home = new HomePage(driver);
	}

	@Test(priority = 1, groups = { "Homepage" })
	public void clickOnMenBestSellerShopNowButton() {
		try {
			home.clickmenbestsellerShopNowAndCloseTab();
		} catch (Exception e) {
			Assert.fail("Failed to Click on Men BestSeller ShopNow Button"+e.getMessage());
		}
	}

	@Test(priority = 2, groups = { "Homepage" })
	public void clickOnWomenBestSellerShopnowButton() {
		try {
			home.clickwomenbestsellerShopNowAndCloseTab();
		} catch (Exception e) {
			Assert.fail("Failed to Click on Women BestSeller ShopNow Button"+e.getMessage());
		}
	}

	@Test(priority = 3, groups = { "Homepage" })
	public void clickLatestReleaseProduct() {
		try {
			home.clickLatestReleaseProductLPFCTAAndCloseTab();
		} catch (Exception e) {
			Assert.fail("Failed to Click on Latest Release Product"+e.getMessage());
		}
	}

	@Test(priority = 4, groups = { "Homepage" })
	public void clickOnAllProductsLatestRelease() {
		try {
			home.clickOnAllProductsLatestReleaseAndCloseTab();
		} catch (Exception e) {
			Assert.fail("Failed to Click on All Products Latest Release"+e.getMessage());
		}
	}

	@Test(priority = 5, groups = { "Homepage" })
	public void ClickOnPopularCategories() {
		try {
			home.clickTGPBannerAndCloseTab();
		} catch (Exception e) {
			Assert.fail("Failed to Click on Popular Categories List"+e.getMessage());
		}
	}

	@Test(priority = 6, groups = { "Homepage" })
	public void clickOnLeatherWatchMenShopNowButton() {
		try {
			home.clickLSBShopNowCTAAndCloseTab();
		} catch (Exception e) {
			Assert.fail("Failed to Click On Leather Watch Men ShopNow Button"+e.getMessage());
		}
	}

	@Test(priority = 7, groups = { "Homepage" })
	public void GoToMensNewArrivalCategory() {
		try {
			home.goToNewCategory();
		} catch (Exception e) {
			Assert.fail("Failed to Click on Mens New Arrival Category"+e.getMessage());
		}
	}
}