package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CompletePage {

    WebDriver driver;
    WebElement thankYouMessage;
    WebElement backHomeButton;
    WebElement actualURL;
    WebElement pageTitle;



    public CompletePage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getThankYouMessage() {
        return driver.findElement(By.className("complete-header"));
    }

    public WebElement getBackHomeButton() {
        return driver.findElement(By.id("back-to-products"));
    }

    public String getActualURL() {
        return driver.getCurrentUrl();
    }

    public WebElement getPageTitle() {
        return driver.findElement(By.className("title"));
    }
}
