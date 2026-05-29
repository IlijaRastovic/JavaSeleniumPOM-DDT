package Tests;

import Base.BaseTest;
import Pages.LoginPage;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginTest extends BaseTest {

    @BeforeMethod
    public void pageSetUp(){
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        loginPage = new LoginPage(driver);
    }

    @Test
    public void loginWithValidCredentialsTest(){
        int usernameColumn = 0;
        int passwordColumn = 1;
        loginPage.enterValidUsername(excelHelper.loadValues("Sheet1", usernameColumn));
        loginPage.enterValidPassword(excelHelper.loadSingleValue("Sheet1", 1, passwordColumn));

    }
}
