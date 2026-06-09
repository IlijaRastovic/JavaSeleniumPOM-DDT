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

        // Create a new instance of Firefox browser before each test method
        driver = new FirefoxDriver();

        // Maximize browser window for better visibility and stability of tests
        driver.manage().window().maximize();

        // Navigate to the SauceDemo application URL
        driver.get("https://www.saucedemo.com");

        // Set implicit wait to handle element loading delays (applies globally to driver)
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        // Initialize page objects with current WebDriver instance
        loginPage = new LoginPage(driver);
        itemPage = new ItemPage(driver);
    }



    @Test(priority = 1)
    public void shouldShowAllInventoryItemsAfterLogin() {
        loginPage.userLogin();
        itemPage.checkIfAllItemsAreShown();

        // Checking if the URL is correct
        Assert.assertEquals(itemPage.getActualUrl(), driver.getCurrentUrl());
        // Check if page title is displayed
        Assert.assertTrue(itemPage.getTitle().isDisplayed());
        // Check if cart button is displayed
        Assert.assertTrue(itemPage.getCart().isDisplayed());
        Assert.assertTrue(itemPage.checkIfAllItemsAreShown(),"Not all of the items are shown");
    }

    @Test(priority = 2)
    public void shouldDisplayOnlyUniqueItems() {
        loginPage.userLogin();
        Assert.assertTrue(itemPage.checkIfAllItemsShownAreDifferent(),"There are duplicate items on the page");
        // Checking if the URL is correct
        Assert.assertEquals(itemPage.getActualUrl(), driver.getCurrentUrl());
        // Check if page title is displayed
        Assert.assertTrue(itemPage.getTitle().isDisplayed());
        // Check if cart button is displayed
        Assert.assertTrue(itemPage.getCart().isDisplayed());
    }

    @Test(priority = 3)
    public void shouldDisplayAllFilterOptionsInDropdown() {
        loginPage.userLogin();
        itemPage.checkIfAllFilterOptionsAreShown();
        itemPage.clickFilterDropDownMenu(); // optional
        Assert.assertTrue(itemPage.checkIfAllFilterOptionsAreShown(), "Not all the filter options are shown");
        // Checking if the URL is correct
        Assert.assertEquals(itemPage.getActualUrl(), driver.getCurrentUrl());
        // Check if page title is displayed
        Assert.assertTrue(itemPage.getTitle().isDisplayed());
        // Check if cart button is displayed
        Assert.assertTrue(itemPage.getCart().isDisplayed());

    }

    @Test(priority = 4)
    public void shouldShowCartBadgeAfterAddingItem() {
        loginPage.userLogin();
        itemPage.clickOnAddToCartButton();
        Assert.assertTrue(itemPage.getCartBadge().isDisplayed());
        // Checking if the URL is correct
        Assert.assertEquals(itemPage.getActualUrl(), driver.getCurrentUrl());
        // Check if page title is displayed
        Assert.assertTrue(itemPage.getTitle().isDisplayed());
        // Check if cart button is displayed
        Assert.assertTrue(itemPage.getCart().isDisplayed());
    }


    // After method to close the browser after every test
    @AfterMethod
    public void tearDown() {
        driver.quit();
    }


}
