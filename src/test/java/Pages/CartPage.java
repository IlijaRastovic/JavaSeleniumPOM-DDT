package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import java.util.List;

public class CartPage {

    // Keeping the track of what Web Elements we used
    WebDriver driver;
    WebElement continueShoppingButton;
    WebElement checkoutButton;
    WebElement item;
    WebElement removeItemButton;
    List<WebElement> listOfItems;
    List<WebElement> listOfRemoveItemsButtons;
    List<String> listOfCartItemsTitles;

    // Initialize WebDriver instance for CartPage
    public CartPage(WebDriver driver) {
        this.driver = driver;
    }
    // Locators for WebElements
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
        return driver.findElement(By.id("remove-sauce-labs-backpack"));
    }

    public List<WebElement> getListOfItems() {
        return driver.findElements(By.className("cart_item"));
    }

    public List<WebElement> getListOfRemoveItemsButtons() {
        // Locate and return all "Remove" buttons using partial attribute match
        return driver.findElements(By.cssSelector("button[data-test^='remove']"));
    }

    public List<String> getListOfCartItemsTitles() {
        List<String> titles = new ArrayList<>();

        // Iterate through all cart items and extract product names
        for (WebElement item : getListOfItems()) {
            String title = item.findElement(By.className("inventory_item_name")).getText();
            titles.add(title);
        }
        return titles;
    }

    //-------------------------------------------------------------------------

    // Methods that are or will be used in tests
    public void clickCheckoutButton(){
        getCheckoutButton().click();
    }

    public int sizeOfItemList(){
        return getListOfItems().size();
    }
    // Helper method to check if locator is getting all the buttons
    public void checkRemoveButtonListSize(){
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
