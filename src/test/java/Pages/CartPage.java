package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class CartPage {

    WebDriver driver;
    WebElement continueShoppingButton;
    WebElement checkoutButton;
    WebElement item;
    WebElement removeItemButton;
    List<WebElement> listOfItems;
    List<WebElement> listOfRemoveItemsButtons;
    List<String> listOfCartItemsTitles;


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

    public List<WebElement> getListOfItems() {
        return driver.findElements(By.className("cart_item"));
    }

    public List<WebElement> getListOfRemoveItemsButtons() {
        return driver.findElements(By.cssSelector("button[data-test^='remove']"));
    }

    public List<String> getListOfCartItemsTitles() {
        List<String> titles = new ArrayList<>();

        for (WebElement item : getListOfItems()) {
            String title = item.findElement(By.className("inventory_item_name")).getText();
            titles.add(title);
        }

        return titles;
    }

    //-------------------------------------------------------------------------

    public void clickCheckoutButton(){
        getCheckoutButton().click();
    }

    public int sizeOdItemList(){ // To check if this method is working correctly
        return getListOfItems().size();
    }

    public void checkRemoveButtonListSize(){ // Helper method to check if locator is getting all of the buttons
        System.out.println(getListOfRemoveItemsButtons().size());
    }

    public void clickRemoveButton(int quantity ) throws IllegalAccessException {

        if(quantity > getListOfRemoveItemsButtons().size()) {
            throw new IllegalAccessException("Size of the list is smaller then the passed number!");
        }
        for(int i=0;i<quantity;i++){
            getListOfRemoveItemsButtons().get(i).click();
        }
    }


}
