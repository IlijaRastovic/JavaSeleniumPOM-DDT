package Pages;

import Base.ExcelHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class LoginPage {
    WebDriver driver;
    WebElement usernameField;
    WebElement passwordField;
    WebElement loginButton;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getUsernameField() {
        return driver.findElement(By.id("user-name"));
    }

    public WebElement getPasswordField() {
        return driver.findElement(By.id("password"));
    }

    public WebElement getLoginButton() {
        return driver.findElement(By.id("login-button"));
    }

    //-----------------------------------------------------

    public void enterValidUsername(List<String> loadValues) {
        getUsernameField().clear();
        for (String username : loadValues) {
            if(username.equals("standard_user")){
                getUsernameField().sendKeys(username);
                break;
            }
        }
    }

    public void enterValidPassword(String password) {
        getPasswordField().clear();
        getPasswordField().sendKeys(password);
        }

    }

