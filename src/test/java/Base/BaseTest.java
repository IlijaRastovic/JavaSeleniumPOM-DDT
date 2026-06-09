package Base;

import Pages.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import java.io.IOException;

public class BaseTest {

    //  References to WebDriver, test data utilities, and page objects
    public WebDriver driver;
    public ExcelReader excelReader;
    public ExcelHelper  excelHelper;
    public LoginPage loginPage;
    public ItemPage itemPage;
    public CartPage cartPage;
    public CheckoutPage checkoutPage;
    public CheckoutOverviewPage  checkoutOverviewPage;
    public CompletePage completePage;



    //  Initializes test resources before any test methods
    @BeforeClass
    public void setUp() throws IOException {
        // Set up FirefoxDriver
        WebDriverManager.firefoxdriver().setup();
        // Initialize Excel Test data reader
        excelReader = new ExcelReader("src/test/java/TestData/DDT.xlsx");
        // Initialize helper methods for working with Excel data
        excelHelper = new ExcelHelper(excelReader);


    }


}
