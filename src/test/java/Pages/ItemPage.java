package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class ItemPage {
    WebDriver driver;
    String actualUrl;
    WebElement cart;
    WebElement title;
    WebElement itemTitle;
    WebElement itemAddToCart;
    WebElement burgerMenu;
    WebElement burgerLogout;
    WebElement filterMenu;
    List<WebElement> itemPicture;
    WebElement itemPrice;


    public ItemPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getActualUrl() {
        return driver.getCurrentUrl();
    }

    public WebElement getCart() {
        return driver.findElement(By.cssSelector("a[data-test='shopping-cart-link']"));
    }

    public WebElement getTitle() {
        return driver.findElement(By.className("title"));
    }

    public WebElement getItemTitle() {
        return driver.findElement(By.cssSelector("div[data-test='inventory-item-name']"));
    }

    public WebElement getItemAddToCart() {
        return driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
    }

    public WebElement getBurgerMenu() {
        return driver.findElement(By.id("react-burger-menu-btn"));
    }

    public WebElement getBurgerLogout() {
        return driver.findElement(By.id("logout_sidebar_link"));
    }

    public WebElement getFilterMenu() {
        return driver.findElement(By.cssSelector("select[data-test='product-sort-container']"));
    }

    public List<WebElement> getItemsImg() {
        return driver.findElements(By.cssSelector(".inventory_item img"));
    }


    public WebElement getItemPrice() {
        return driver.findElement(By.className("inventory_item_price"));
    }
}
