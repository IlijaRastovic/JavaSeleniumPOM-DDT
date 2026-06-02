package Tests;

import Base.BaseTest;
import Pages.ItemPage;
import Pages.LoginPage;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class ItemPageTests extends BaseTest {

    @BeforeMethod
    public void pageSetUp() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));


        loginPage = new LoginPage(driver);
        itemPage = new ItemPage(driver);
    }



    @Test(priority = 1)
    public void allItemsShownTest() {
        loginPage.userLogin();
        itemPage.checkIfAllItemsAreShown();
        Assert.assertEquals(itemPage.getActualUrl(), driver.getCurrentUrl());
        Assert.assertTrue(itemPage.getTitle().isDisplayed());
        Assert.assertTrue(itemPage.getCart().isDisplayed());
        Assert.assertTrue(itemPage.checkIfAllItemsAreShown(),"Not all of the items are shown");
    }

    @Test(priority = 2)
    public void allDifferentItemsShownTest() {
        loginPage.userLogin();
        Assert.assertTrue(itemPage.checkIfAllItemsShownAreDifferen(),"There are duplicate items on the page");
        Assert.assertEquals(itemPage.getActualUrl(), driver.getCurrentUrl());
        Assert.assertTrue(itemPage.getTitle().isDisplayed());
        Assert.assertTrue(itemPage.getCart().isDisplayed());
    }

    @Test(priority = 3)
    public void allFilerOptionsShownTest() {
        loginPage.userLogin();
        itemPage.checkIfAllFilterOptionsAreShown();
        itemPage.clickFilterDropDownMenu(); // optional
        Assert.assertTrue(itemPage.checkIfAllFilterOptionsAreShown(), "Not all the filter options are shown");
        Assert.assertEquals(itemPage.getActualUrl(), driver.getCurrentUrl());
        Assert.assertTrue(itemPage.getTitle().isDisplayed());
        Assert.assertTrue(itemPage.getCart().isDisplayed());

    }

    @Test(priority = 4)
    public void cartIconBadgeIsShownTest() {
        loginPage.userLogin();
        itemPage.clickOnAddToCartButton();
        Assert.assertTrue(itemPage.getCartBadge().isDisplayed());
        Assert.assertEquals(itemPage.getActualUrl(), driver.getCurrentUrl());
        Assert.assertTrue(itemPage.getTitle().isDisplayed());
        Assert.assertTrue(itemPage.getCart().isDisplayed());
    }





    @AfterMethod
    public void tearDown() {
        driver.quit();
    }


}
