package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckoutOverviewPage {

    WebDriver driver;
    WebElement finishButton;
    WebElement cancelButton;
    WebElement item;
    WebElement cartQuantity;
    WebElement itemPrice;

    public CheckoutOverviewPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getFinishButton() {
        return driver.findElement(By.id("finish")) ;
    }

    public WebElement getCancelButton() {
        return driver.findElement(By.id("cancel")) ;
    }

    public WebElement getItem() {
        return driver.findElement(By.className("cart_item_label")) ;
    }

    public WebElement getCartQuantity() {
        return driver.findElement(By.className("cart_quantity"));
    }

    public WebElement getItemPrice() {
        return driver.findElement(By.className("inventory_item_price"));
    }

    //-----------------------------------------------------------------

    public void clickFinishButton() {
        getFinishButton().click();
    }
}
