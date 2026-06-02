package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ItemPage {
    WebDriver driver;
    String actualUrl;
    WebElement cart;
    WebElement title;
    List<WebElement> itemTitle;
    WebElement itemAddToCart;
    WebElement burgerMenu;
    WebElement burgerLogout;
    WebElement filterMenu;
    List<WebElement> itemPicture;
    List<WebElement> itemPrice;
    WebElement cartBadge;


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

    public List<WebElement> getItemTitle() {
        return driver.findElements(By.cssSelector("a[id*='item_']"));
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
        return driver.findElement(By.cssSelector("[data-test='product-sort-container']"));
    }

    public List<WebElement> getItemsImg() {
        return driver.findElements(By.cssSelector(".inventory_item img"));
    }

    public List<WebElement> getItemPrice() {
        return driver.findElements(By.className("inventory_item_price"));
    }

    public WebElement getCartBadge() {
        return driver.findElement(By.className("shopping_cart_badge"));
    }

    //-----------------------------------------------------------------------------------------------------

    public boolean checkIfAllItemsAreShown(){
        for (WebElement img : getItemsImg()) {
            if(!img.isDisplayed()){
                return false;
            }
       }
        return true;
        //System.out.println(getItemsImg().size());   // Checking that all of the items are added to list
    }

    public boolean checkIfAllItemsShownAreDifferen(){
        Set<String> uniqueItems = new HashSet<String>();
        for (WebElement img : getItemsImg()) {
            String dataTest = img.getAttribute("data-test");

            if(!uniqueItems.add(dataTest)){
                return false; // False if duplicate is found
            }
        }
        return true;  // Returns true if all items added are unique
    }

    public void clickFilterDropDownMenu(){
        getFilterMenu().click();
    }

    public boolean checkIfAllFilterOptionsAreShown(){
        Select select = new Select(getFilterMenu());
        List<String> filterOptions = new ArrayList<>();

        for (WebElement option : select.getOptions()) {
            filterOptions.add(option.getText());
        }
        if(filterOptions.size()==4){
            return true;
        }else{
            return false;
        }
    }

    public void clickOnAddToCartButton(){
        getItemAddToCart().click();
    }


}
