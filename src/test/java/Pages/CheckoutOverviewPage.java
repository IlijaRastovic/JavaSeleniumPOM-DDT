package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import java.util.List;

public class CheckoutOverviewPage {

    // Keeping the track of what Web Elements we used
    WebDriver driver;
    WebElement finishButton;
    WebElement cancelButton;
    WebElement item;
    WebElement cartQuantity;
    WebElement itemPrice;
    List<String> listOfCheckoutItemsTitles;
    List<WebElement> itemList;

    // Initialize WebDriver instance for CheckoutOverviewPage
    public CheckoutOverviewPage(WebDriver driver) {
        this.driver = driver;
    }
    // Locators for WebElements
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

    public List<WebElement> getItemList() {
        return driver.findElements(By.className("cart_item"));
    }

    public List<String> getListOfCheckoutItemsTitles() {
        List<String> titles = new ArrayList<>();

        // Iterate through all CheckoutOverviewPage items and extract titles
        for (WebElement item : getItemList()) {
            String title = item.findElement(By.className("inventory_item_name")).getText();
            titles.add(title);
        }
        return titles;
    }

    //-----------------------------------------------------------------

    // Methods that are used in tests
    public void clickFinishButton() {
        getFinishButton().click();
    }

    public void clickCancelButton() {
        getCancelButton().click();
    }
}
