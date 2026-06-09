package Pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class LoginPage {

    // Keeping the track of what Web Elements we used
    WebDriver driver;
    WebElement usernameField;
    WebElement passwordField;
    WebElement loginButton;
    WebElement errorMessage;
    String actualUrl;
    WebElement errorBadge;

    // Initialize WebDriver instance for LoginPage
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

    public WebElement getErrorMessage() {
        return driver.findElement(By.cssSelector("h3[data-test='error']"));
    }

    public String getActualUrl() {
        return driver.getCurrentUrl();
    }

    public WebElement getErrorBadge() {
        return driver.findElement(By.cssSelector("svg[data-icon='times-circle']"));
    }

    //-----------------------------------------------------
    // Methods that are or will be used in tests
    public void userLogin() {
        clearFields();
        getUsernameField().sendKeys("standard_user");
        getPasswordField().sendKeys("secret_sauce");
        getLoginButton().click();
    }

    public void enterValidUsername(List<String> loadValues) {
        getUsernameField().clear();
        for (String username : loadValues) {
            if(username.equals("standard_user")){
                getUsernameField().sendKeys(username);
                break;
            }
        }
    }

    public void enterLockedOutUsername(List<String> loadValues) {
        getUsernameField().clear();
        for (String username : loadValues) {
            if(username.equals("locked_out_user")){
                getUsernameField().sendKeys(username);
                break;
            }
        }
    }

    public void enterInvalidUsername(List<String> loadValues) {
        getUsernameField().clear();
        for (String username : loadValues) {
            if(username.equals("invalid_username")){
                getUsernameField().sendKeys(username);
                break;
            }
        }
    }

    public void enterValidPassword(String password) {
        getPasswordField().clear();
        getPasswordField().sendKeys(password);
    }

    public void enterInvalidPassword(String password) {
        getPasswordField().clear();
        getPasswordField().sendKeys(password);
    }

    public void clickLoginButton() {
        getLoginButton().click();
    }

    public void clearFields() {
        getUsernameField().clear();
        getPasswordField().clear();
    }

}

