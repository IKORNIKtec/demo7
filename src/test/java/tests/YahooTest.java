package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.yahoo.YahooMainPage;
import utils.WebDriverFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertTrue;

@RunWith(value = Parameterized.class)
public class YahooTest {
    private static final Logger logger = LoggerFactory.getLogger(YahooTest.class);
    private WebDriver driver;
    private String menuItem= "Mail";
    private static List<WebElement> menuItems;
    private YahooMainPage yahooPage;

    public YahooTest(String value) {
        this.menuItem = value;
    }

    @Before
    public void openBrowser(){
        driver = WebDriverFactory.getFirefoxDriver();
        yahooPage = new YahooMainPage(driver);
    }

    @After
    public void closeBrowser(){
        driver.close();
    }

    @Parameters(name = "{index}: menu is {0}")
    public static Iterable<Object[]> data() {
        HtmlUnitDriver driverLocal = new HtmlUnitDriver();
        YahooMainPage yahoo = new YahooMainPage(driverLocal);
        yahoo.open();
        menuItems = yahoo.getSiteLinks();
        Collection<Object[]> result = new ArrayList<Object[]>();
        for (WebElement e: menuItems){
            String link = e.getText();
            logger.info("Yahoo menu item is {}", link);
            result.add(new Object[]{link});
            break;
        }
        driverLocal.quit();
        return result;
    }

    @Test
    public void verifyTimeOfLoadingYahooSitesTest()  {
        long expLoadPageTime = 7;
        String message = "'Yahoo %s' loading over %s sec";
        yahooPage.open();
        yahooPage.clickNavigationMenuItem(menuItem);
        yahooPage.waitPageLoadingCompleted(expLoadPageTime);
        assertTrue(String.format(message, menuItem, expLoadPageTime) , yahooPage.isPageLoaded());
    }

}

