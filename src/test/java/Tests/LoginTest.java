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

public class LoginTest extends BaseTest {

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
    public void shouldLoginWithValidCredentials() {

        // Location of columns in Excel file where we have one or multiple values of needed kind
        int usernameColumn = 0;
        int passwordColumn = 1;
        loginPage.enterValidUsername(excelHelper.loadValues("Sheet1", usernameColumn));
        loginPage.enterValidPassword(excelHelper.loadSingleValue("Sheet1", 1, passwordColumn));
        loginPage.clickLoginButton();
        // Check if URL is correct
        Assert.assertEquals(itemPage.getActualUrl(), driver.getCurrentUrl());
        // Check if page title is displayed
        Assert.assertTrue(itemPage.getTitle().isDisplayed());
        // Check if cart button is displayed
        Assert.assertTrue(itemPage.getCart().isDisplayed());
        // Check if we have price on one of the items displayed
        Assert.assertFalse(itemPage.getItemPrice().isEmpty());

    }

    @Test(priority = 2)
    public void shouldShowErrorMessageWhenFieldsAreEmpty() {
        loginPage.clearFields();
        loginPage.clickLoginButton();
        // Check if we get error elements after failed login
        Assert.assertTrue(loginPage.getErrorMessage().isDisplayed());
        Assert.assertTrue(loginPage.getErrorBadge().isDisplayed());
        // Check if we are still on login page
        Assert.assertTrue(loginPage.getLoginButton().isDisplayed());
        Assert.assertTrue(loginPage.getPasswordField().isDisplayed());
        // Check if URL is correct
        Assert.assertEquals(itemPage.getActualUrl(), driver.getCurrentUrl());
    }

    @Test(priority = 3)
    public void shouldShowErrorMessageWhenUsernameFieldIsEmpty() {

        // Location of columns in Excel file where we have one or multiple values of needed kind
        int passwordColumn = 1;
        loginPage.clearFields();
        loginPage.enterValidPassword(excelHelper.loadSingleValue("Sheet1", 1, passwordColumn));
        loginPage.clickLoginButton();
        // Check if we get error elements after failed login
        Assert.assertTrue(loginPage.getErrorMessage().isDisplayed());
        Assert.assertTrue(loginPage.getErrorMessage().getText().contains("Username"));
        Assert.assertTrue(loginPage.getErrorBadge().isDisplayed());
        // Check if we are still on login page
        Assert.assertTrue(loginPage.getLoginButton().isDisplayed());
        Assert.assertTrue(loginPage.getPasswordField().isDisplayed());
        // Check if URL is correct
        Assert.assertEquals(itemPage.getActualUrl(), driver.getCurrentUrl());
    }

    @Test(priority = 4)
    public void shouldShowErrorMessageWhenPasswordFieldIsEmpty() {

        // Location of columns in Excel file where we have one or multiple values of needed kind
        int usernameColumn = 0;
        loginPage.clearFields();
        loginPage.enterValidUsername(excelHelper.loadValues("Sheet1", usernameColumn));
        loginPage.clickLoginButton();
        // Check if we get error elements after failed login
        Assert.assertTrue(loginPage.getErrorMessage().isDisplayed());
        Assert.assertTrue(loginPage.getErrorMessage().getText().contains("Password"));
        Assert.assertTrue(loginPage.getErrorBadge().isDisplayed());
        // Check if we are still on login page
        Assert.assertTrue(loginPage.getLoginButton().isDisplayed());
        Assert.assertTrue(loginPage.getPasswordField().isDisplayed());
        // Check if URL is correct
        Assert.assertEquals(itemPage.getActualUrl(), driver.getCurrentUrl());
    }

    @Test(priority = 5)
    public void shouldShowErrorMessageWhenUserIsLockedOut() {

        // Location of columns in Excel file where we have one or multiple values of needed kind
        int usernameColumn = 0;
        int passwordColumn = 1;
        loginPage.clearFields();
        loginPage.enterLockedOutUsername(excelHelper.loadValues("Sheet1", usernameColumn));
        loginPage.enterValidPassword(excelHelper.loadSingleValue("Sheet1", 1, passwordColumn));
        loginPage.clickLoginButton();
        // Check if we get error elements after failed login
        Assert.assertTrue(loginPage.getErrorMessage().isDisplayed());
        Assert.assertTrue(loginPage.getErrorMessage().getText().contains("locked out"));
        Assert.assertTrue(loginPage.getErrorBadge().isDisplayed());
        // Check if we are still on login page
        Assert.assertTrue(loginPage.getLoginButton().isDisplayed());
        Assert.assertTrue(loginPage.getPasswordField().isDisplayed());
        // Check if URL is correct
        Assert.assertEquals(itemPage.getActualUrl(), driver.getCurrentUrl());

    }

    @Test(priority = 6)
    public void shouldShowErrorMessageWhenCredentialsAreInvalid() {

        // Location of columns in Excel file where we have one or multiple values of needed kind
        int usernameColumn = 0;
        int passwordColumn = 1;
        loginPage.clearFields();
        loginPage.enterInvalidUsername(excelHelper.loadValues("Sheet1", usernameColumn));
        loginPage.enterInvalidPassword(excelHelper.loadSingleValue("Sheet1", 2, passwordColumn));
        loginPage.clickLoginButton();
        // Check if we get error elements after failed login
        Assert.assertTrue(loginPage.getErrorMessage().isDisplayed());
        Assert.assertTrue(loginPage.getErrorBadge().isDisplayed());
        // Check if we are still on login page
        Assert.assertTrue(loginPage.getLoginButton().isDisplayed());
        Assert.assertTrue(loginPage.getPasswordField().isDisplayed());
        // Check if URL is correct
        Assert.assertEquals(itemPage.getActualUrl(), driver.getCurrentUrl());
    }

    // After method to close the browser after every test
    @AfterMethod
    public void tearDown() {
        driver.quit();
    }


}
