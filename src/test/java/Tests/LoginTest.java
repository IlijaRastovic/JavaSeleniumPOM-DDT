package Tests;

import Base.BaseTest;
import Pages.ItemPage;
import Pages.LoginPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginTest extends BaseTest {

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
    public void loginWithValidCredentialsTest() {
        int usernameColumn = 0;
        int passwordColumn = 1;
        loginPage.enterValidUsername(excelHelper.loadValues("Sheet1", usernameColumn));
        loginPage.enterValidPassword(excelHelper.loadSingleValue("Sheet1", 1, passwordColumn));
        loginPage.clickLoginButton();
        Assert.assertEquals(itemPage.getActualUrl(), driver.getCurrentUrl());
        Assert.assertTrue(itemPage.getTitle().isDisplayed());
        Assert.assertTrue(itemPage.getCart().isDisplayed());
        Assert.assertFalse(itemPage.getItemPrice().isEmpty());

    }

    @Test(priority = 2)
    public void emptyFieldsTest() {
        loginPage.clearFields();
        loginPage.clickLoginButton();
        Assert.assertTrue(loginPage.getErrorMessage().isDisplayed());
        Assert.assertTrue(loginPage.getErrorBadge().isDisplayed());
        Assert.assertTrue(loginPage.getLoginButton().isDisplayed());
        Assert.assertTrue(loginPage.getPasswordField().isDisplayed());
        Assert.assertEquals(itemPage.getActualUrl(), driver.getCurrentUrl());
    }

    @Test(priority = 3)
    public void emptyUsenameFieldTest() {
        int passwordColumn = 1;
        loginPage.clearFields();
        loginPage.enterValidPassword(excelHelper.loadSingleValue("Sheet1", 1, passwordColumn));
        loginPage.clickLoginButton();
        Assert.assertTrue(loginPage.getErrorMessage().isDisplayed());
        Assert.assertTrue(loginPage.getErrorMessage().getText().contains("Username"));
        Assert.assertTrue(loginPage.getErrorBadge().isDisplayed());
        Assert.assertTrue(loginPage.getLoginButton().isDisplayed());
        Assert.assertTrue(loginPage.getPasswordField().isDisplayed());
        Assert.assertEquals(itemPage.getActualUrl(), driver.getCurrentUrl());
    }

    @Test(priority = 4)
    public void emptyPasswordFieldTest() {
        int usernameColumn = 0;
        loginPage.clearFields();
        loginPage.enterValidUsername(excelHelper.loadValues("Sheet1", usernameColumn));
        loginPage.clickLoginButton();
        Assert.assertTrue(loginPage.getErrorMessage().isDisplayed());
        Assert.assertTrue(loginPage.getErrorMessage().getText().contains("Password"));
        Assert.assertTrue(loginPage.getErrorBadge().isDisplayed());
        Assert.assertTrue(loginPage.getLoginButton().isDisplayed());
        Assert.assertTrue(loginPage.getPasswordField().isDisplayed());
        Assert.assertEquals(itemPage.getActualUrl(), driver.getCurrentUrl());
    }

    @Test(priority = 5)
    public void loginWithLockedOutCredentialsTest() {
        int usernameColumn = 0;
        int passwordColumn = 1;
        loginPage.clearFields();
        loginPage.enterLockedOutUsername(excelHelper.loadValues("Sheet1", usernameColumn));
        loginPage.enterValidPassword(excelHelper.loadSingleValue("Sheet1", 1, passwordColumn));
        loginPage.clickLoginButton();
        Assert.assertTrue(loginPage.getErrorMessage().isDisplayed());
        Assert.assertTrue(loginPage.getErrorMessage().getText().contains("locked out"));
        Assert.assertTrue(loginPage.getErrorBadge().isDisplayed());
        Assert.assertTrue(loginPage.getLoginButton().isDisplayed());
        Assert.assertTrue(loginPage.getPasswordField().isDisplayed());
        Assert.assertEquals(itemPage.getActualUrl(), driver.getCurrentUrl());

    }

    @Test(priority = 6)
    public void loginWithInvalidCredentialsTest() {
        int usernameColumn = 0;
        int passwordColumn = 1;
        loginPage.clearFields();
        loginPage.enterInvalidUsername(excelHelper.loadValues("Sheet1", usernameColumn));
        loginPage.enterInvalidPassword(excelHelper.loadSingleValue("Sheet1", 2, passwordColumn));
        //System.out.println(excelHelper.loadSingleValue("Sheet1",2, passwordColumn));  //---- Test to check if we are getting wanted password from Excel file
        loginPage.clickLoginButton();
        Assert.assertTrue(loginPage.getErrorMessage().isDisplayed());
        Assert.assertTrue(loginPage.getErrorBadge().isDisplayed());
        Assert.assertTrue(loginPage.getLoginButton().isDisplayed());
        Assert.assertTrue(loginPage.getPasswordField().isDisplayed());
        Assert.assertEquals(itemPage.getActualUrl(), driver.getCurrentUrl());
    }




    @AfterMethod
    public void tearDown() {
        driver.quit();
    }


}
