package Base;

import Pages.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;

import java.io.IOException;

public class BaseTest {

    public WebDriver driver;
    public ExcelReader excelReader;
    public ExcelHelper  excelHelper;
    public  LoginPage loginPage;




    @BeforeClass
    public void setUp() throws IOException {
        WebDriverManager.firefoxdriver().setup();
        excelReader = new ExcelReader("src/test/java/TestData/DDT.xlsx");
        excelHelper = new ExcelHelper(excelReader);
        loginPage = new LoginPage(driver);


    }
}
