package Tests;

import Base.BaseTest;
import Pages.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.time.Duration;

public class End2EndTests extends BaseTest {
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
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);
        checkoutOverviewPage = new CheckoutOverviewPage(driver);
        completePage = new CompletePage(driver);
    }

    @Test (priority=1)
    public void shouldCompleteCheckoutHappyPath() {

        // Location of columns in Excel file where we have one or multiple values of needed kind
        int usernameColumn = 0;
        int passwordColumn = 1;
        int firstNameColumn = 2;
        int lastNameColumn = 3;
        int zipCodeColumn = 4;

        loginPage.enterValidUsername(excelHelper.loadValues("Sheet1", usernameColumn));
        loginPage.enterValidPassword(excelHelper.loadSingleValue("Sheet1", 1, passwordColumn));
        loginPage.clickLoginButton();
        itemPage.clickOnAddToCartButton();
        itemPage.clickOnCartIcon();
        cartPage.clickCheckoutButton();
        checkoutPage.enterFirstName(excelHelper.loadValues("Sheet1", firstNameColumn));
        checkoutPage.enterLastName(excelHelper.loadValues("Sheet1", lastNameColumn));
        checkoutPage.enterZipCode(excelHelper.loadSingleIntValue("Sheet1", 1,zipCodeColumn));
        checkoutPage.clickContinueButton();
        checkoutOverviewPage.clickFinishButton();
        // Checking if the URL is correct
        Assert.assertEquals(completePage.getActualURL(),driver.getCurrentUrl());
        // Checking if , Back home ' button is displayed
        Assert.assertTrue(completePage.getBackHomeButton().isDisplayed());
        // Checking if message is displayed
        Assert.assertTrue(completePage.getThankYouMessage().isDisplayed());
        // Checking if page title contains string , Complete '
        Assert.assertTrue(completePage.getPageTitle().getText().contains("Complete"));

    }

    @Test (priority=2)
    public void shouldShowErrorMessageWhenCheckoutFormIsEmpty(){

        // Location of columns in Excel file where we have one or multiple values of needed kind
        int usernameColumn = 0;
        int passwordColumn = 1;

        loginPage.enterValidUsername(excelHelper.loadValues("Sheet1", usernameColumn));
        loginPage.enterValidPassword(excelHelper.loadSingleValue("Sheet1", 1, passwordColumn));
        loginPage.clickLoginButton();
        itemPage.clickOnAddToCartButton();
        itemPage.clickOnCartIcon();
        cartPage.clickCheckoutButton();
        checkoutPage.clickContinueButton();
        Assert.assertTrue(checkoutPage.getCheckoutErrorMsg().isDisplayed());
        // Checking if the URL is correct
        Assert.assertEquals(checkoutPage.getActualURL(), driver.getCurrentUrl());
        // Checking if form fields are displayed
        Assert.assertTrue(checkoutPage.getFirstNameField().isDisplayed());
        Assert.assertTrue(checkoutPage.getLastNameField().isDisplayed());
        Assert.assertTrue(checkoutPage.getZipCodeField().isDisplayed());
        // Checking if , Cancel ' button is displayed
        Assert.assertTrue(checkoutPage.getCancelButton().isDisplayed());

    }


    @Test (priority=3)
    public void shouldNavigateBackToInventoryOnCancelCheckout(){

        // Location of columns in Excel file where we have one or multiple values of needed kind
        int firstNameColumn = 2;
        int lastNameColumn = 3;
        int zipCodeColumn = 4;

        loginPage.userLogin();
        itemPage.clickOnAddToCartButton();
        itemPage.clickOnCartIcon();
        cartPage.clickCheckoutButton();
        checkoutPage.enterFirstName(excelHelper.loadValues("Sheet1", firstNameColumn));
        checkoutPage.enterLastName(excelHelper.loadValues("Sheet1", lastNameColumn));
        checkoutPage.enterZipCode(excelHelper.loadSingleIntValue("Sheet1", 1,zipCodeColumn));
        checkoutPage.clickContinueButton();
        checkoutOverviewPage.clickCancelButton();
        // Checking if the URL is correct
        Assert.assertEquals(itemPage.getActualUrl(), driver.getCurrentUrl());
        // Checking if cart badge is shown if item is added in cart, and then checkout is canceled and user is back to item page
        Assert.assertTrue(itemPage.getCartBadge().isDisplayed());
        // Checking if all items are shown on item page
        Assert.assertTrue(itemPage.checkIfAllItemsAreShown());


    }

    @Test (priority=4)
    public void shouldCheckoutOnlyRemainingItemsAfterRemovingFromCart() throws IllegalAccessException {

        // Location of columns in Excel file where we have one or multiple values of needed kind
        int firstNameColumn = 2;
        int lastNameColumn = 3;
        int zipCodeColumn = 4;
        // How many items we want to add to cart
        int addedItems = 6;
        // How many items we want to remove from cart in this test
        int itemsToRemove = 2;

        loginPage.userLogin();
        itemPage.clickOnMultipleAddToCartButton(addedItems);
        itemPage.clickOnCartIcon();
        cartPage.clickRemoveButton(itemsToRemove);
        // Checking if all the removed items are removed
        Assert.assertEquals(cartPage.sizeOfItemList(), (addedItems-itemsToRemove));
        cartPage.clickCheckoutButton();
        checkoutPage.enterFirstName(excelHelper.loadValues("Sheet1", firstNameColumn));
        checkoutPage.enterLastName(excelHelper.loadValues("Sheet1", lastNameColumn));
        checkoutPage.enterZipCode(excelHelper.loadSingleIntValue("Sheet1",1,zipCodeColumn));
        checkoutPage.clickContinueButton();
        // Checking if all items from cart are shown in checkout
        Assert.assertEquals(cartPage.getListOfCartItemsTitles(),checkoutOverviewPage.getListOfCheckoutItemsTitles());
        checkoutOverviewPage.clickFinishButton();
        // Checking if the URL is correct
        Assert.assertEquals(completePage.getActualURL(),driver.getCurrentUrl());
        // Checking if , Back home '  button is displayed
        Assert.assertTrue(completePage.getBackHomeButton().isDisplayed());
        // Checking if , Thank you '  message is displayed
        Assert.assertTrue(completePage.getThankYouMessage().isDisplayed());
        // Checking if page title contains string , Complete '
        Assert.assertTrue(completePage.getPageTitle().getText().contains("Complete"));




    }

    @Test (priority=5)
    public void shouldReturnToLoginPageAfterLogout() throws InterruptedException {
        loginPage.userLogin();
        itemPage.clickBurgerMenuButton();
        itemPage.clickLogoutButton();
        Assert.assertTrue(loginPage.getUsernameField().isDisplayed());
        Assert.assertTrue(loginPage.getPasswordField().isDisplayed());
        // Checking if , Log in ' button is displayed
        Assert.assertTrue(loginPage.getLoginButton().isDisplayed());
        // Checking if the URL is correct
        Assert.assertEquals(loginPage.getActualUrl(),driver.getCurrentUrl());
    }


    // After method to close the browser after every test
    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
