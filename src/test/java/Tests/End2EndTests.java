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
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));


        loginPage = new LoginPage(driver);
        itemPage = new ItemPage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);
        checkoutOverviewPage = new CheckoutOverviewPage(driver);
        completePage = new CompletePage(driver);
    }

    @Test (priority=1)
    public void E2ECompleteCheckoutTest() {
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
        Assert.assertEquals(completePage.getActualURL(),driver.getCurrentUrl());
        Assert.assertTrue(completePage.getBackHomeButton().isDisplayed());
        Assert.assertTrue(completePage.getThankYouMessage().isDisplayed());
        Assert.assertTrue(completePage.getPageTitle().getText().contains("Complete"));

    }

    @Test (priority=2)
    public void emptyCheckoutFormErrorMessageShownTest(){
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
        Assert.assertEquals(checkoutPage.getActualURL(), driver.getCurrentUrl());
        Assert.assertTrue(checkoutPage.getFirstNameField().isDisplayed());
        Assert.assertTrue(checkoutPage.getLastNameField().isDisplayed());
        Assert.assertTrue(checkoutPage.getZipCodeField().isDisplayed());
        Assert.assertTrue(checkoutPage.getCancelButton().isDisplayed());

    }


    @Test (priority=3)
    public void cancelCheckoutTest(){

    }

    @Test (priority=4)
    public void E2EWithRemoveItemFromCartTest(){ // Assert that item is removed

    }

    @Test (priority=5)
    public void LogOutTest(){

    }













    @AfterMethod
    public void tearDown() {
        //driver.quit();
    }
}
