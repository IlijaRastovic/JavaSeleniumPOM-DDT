package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.concurrent.ThreadLocalRandom;
import java.util.List;

public class CheckoutPage {

    // Keeping the track of what Web Elements we used
    WebDriver driver;
    WebElement firstNameField;
    WebElement lastNameField;
    WebElement zipCodeField;
    WebElement cancelButton;
    WebElement continueButton;
    WebElement checkoutErrorMsg;
    String actualURL;
    List<WebElement> itemList;



    // Initialize WebDriver instance for CheckoutPage
    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }
    // Locators for WebElements
    public WebElement getFirstNameField() {
        return driver.findElement(By.id("first-name"));
    }

    public WebElement getLastNameField() {
        return driver.findElement(By.id("last-name"));
    }

    public WebElement getZipCodeField() {
        return driver.findElement(By.id("postal-code"));
    }

    public WebElement getCancelButton() {
        return driver.findElement(By.id("cancel"));
    }

    public WebElement getContinueButton() {
        return driver.findElement(By.id("continue"));
    }

    public WebElement getCheckoutErrorMsg() {
        return driver.findElement(By.cssSelector("h3[data-test='error'"));
    }

    public String getActualURL() {
        return driver.getCurrentUrl();
    }

    public List<WebElement> getItemList() {
        return driver.findElements(By.className("cart_item"));
    }



    //------------------------------------------------------------

    // Methods that are used in tests
    public void enterFirstName(List<String> loadValues){
        getFirstNameField().clear();
        int randomIndex = ThreadLocalRandom.current().nextInt(1, loadValues.size()); // Getting random index number, for random pick from Excel data file
        getFirstNameField().sendKeys(loadValues.get(randomIndex));
    }

    public void enterLastName(List<String> loadValues){
        getLastNameField().clear();
        int randomIndex = ThreadLocalRandom.current().nextInt(1, loadValues.size()); // Getting random index number, for random pick from Excel data file
        getLastNameField().sendKeys(loadValues.get(randomIndex));
    }

    public void enterZipCode(int zipCode){
        getZipCodeField().clear();
        getZipCodeField().sendKeys(String.valueOf(zipCode));
    }

    public void clickContinueButton(){
        getContinueButton().click();
    }


}
