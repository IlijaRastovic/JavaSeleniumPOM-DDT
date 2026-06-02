package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CartPage {

    WebDriver driver;
    WebElement continueShoppingButton;
    WebElement checkoutButton;
    WebElement item;
    WebElement removeItemButton;

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getContinueShoppingButton() {
        return driver.findElement(By.id("continue-shopping")) ;
    }

    public WebElement getCheckoutButton() {
        return driver.findElement(By.id("checkout")) ;
    }

    public WebElement getItem() {
        return driver.findElement(By.className("cart_item_label"));
    }

    public WebElement getRemoveItemButton() {
        return driver.findElement(By.id("remove-sauce-labs-backpack")); //Every item has its own remove button, i will later get all the buttons with a list
    }

    //-------------------------------------------------------------------------

    public void clickCheckoutButton(){
        getCheckoutButton().click();
    }
}
