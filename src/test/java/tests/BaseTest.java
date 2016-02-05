package tests;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import utils.WebDriverFactory;

public class BaseTest {
    protected static WebDriver driver;

    @BeforeClass
    public static void openBrowser(){
        driver = WebDriverFactory.getFirefoxDriver();
    }

    @AfterClass
    public static void closeBrowser(){
        driver.close();
    }


}
