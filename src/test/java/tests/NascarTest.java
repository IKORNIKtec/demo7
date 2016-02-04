package tests;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;

public class NascarTest extends BaseTest{

    @Test
    public void testPrintMessage() {
        FirefoxDriver driver = new FirefoxDriver();
        driver.get("http://www.google.com");
    }
}
