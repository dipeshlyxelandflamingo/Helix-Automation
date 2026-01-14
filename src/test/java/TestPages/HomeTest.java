package TestPages;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Base.BaseClass;
import Pages.HomePage;

public class HomeTest extends BaseClass {

	HomePage home;

	@BeforeClass
	public void setUpHomePage() {
		home = new HomePage(driver);
	}

	@Test(priority = 1)
    public void clickOnMenBestSellerShopNowButton() {
        try {
            home.clickmenbestsellerShopNowAndCloseTab();
        } catch (Exception e) {
            Assert.fail("Failed to Click on Men BestSeller ShopNow Button");
        }
    }

    @Test(priority = 2)
    public void clickOnWomenBestSellerShopnowButton() {
        try {
            home.clickwomenbestsellerShopNowAndCloseTab();
        } catch (Exception e) {
            Assert.fail("Failed to Click on Women BestSeller ShopNow Button");
        }
    }

    @Test(priority = 3)
    public void clickLatestReleaseProduct() {
        try {
            home.clickLatestReleaseProductLPFCTAAndCloseTab();
        } catch (Exception e) {
            Assert.fail("Failed to Click on Latest Release Product");
        }
    }

    @Test(priority = 4)
    public void clickOnAllProductsLatestRelease() {
        try {
            home.clickOnAllProductsLatestReleaseAndCloseTab();
        } catch (Exception e) {
            Assert.fail("Failed to Click on All Products Latest Release");
        }
    }

    @Test(priority = 5)
    public void clickOnAllLowStockProductCategories() {
        try {
            home.clickOnAllPCAndCloseTab();
        } catch (Exception e) {
            Assert.fail("Failed to Click on All Low Stock Product Categories");
        }
    }

    @Test(priority = 6)
    public void ClickOnPopularCategories() {
        try {
            home.clickTGPBannerAndCloseTab();
        } catch (Exception e) {
            Assert.fail("Failed to Click on Popular Categories List");
        }
    }

    @Test(priority = 7)
    public void clickOnLeatherWatchMenShopNowButton() {
        try {
            home.clickLSBShopNowCTAAndCloseTab();
        } catch (Exception e) {
            Assert.fail("Failed to Click On Leather Watch Men ShopNow Button");
        }
    }

    @Test(priority = 8)
    public void GoToMensNewArrivalCategory() {
        try {
            home.goToNewCategory();
        } catch (Exception e) {
            Assert.fail("Failed to Click on Mens New Arrival Category");
        }
    }
}