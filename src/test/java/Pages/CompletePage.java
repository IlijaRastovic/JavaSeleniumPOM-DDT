package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CompletePage {

    // Keeping the track of what Web Elements we used
    WebDriver driver;
    WebElement thankYouMessage;
    WebElement backHomeButton;
    WebElement actualURL;
    WebElement pageTitle;


    // Initialize WebDriver instance for CheckoutPage
    public CompletePage(WebDriver driver) {
        this.driver = driver;
    }
    // Locators for WebElements
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
