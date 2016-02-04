package tests;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.yahoo.YahooMainPage;
import utils.WebDriverFactory;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;

@RunWith(value = Parameterized.class)
public class YahooTest {
    private static final Logger logger = LoggerFactory.getLogger(YahooTest.class);
    private static WebDriver driver;
    private String menuItem = "Mail";
    private static List<WebElement> menuItems;
    private YahooMainPage yahooPage;
    private long timeout;

    public YahooTest(String value, long timeout) {
        this.menuItem = value;
        this.timeout = timeout;
    }

    @BeforeClass
    public static void openBrowser(){
        driver = WebDriverFactory.getFirefoxDriver();

    }

    @Before
    public void clearCookie(){
        driver.manage().deleteAllCookies();
        yahooPage = new YahooMainPage(driver);
    }

    @AfterClass
    public static void closeBrowser(){
        driver.close();
    }

    @Parameters(name = "{index}: Yahoo {0}")
    public static Iterable<Object[]> data() {
//        HtmlUnitDriver driverLocal = new HtmlUnitDriver();
//        YahooMainPage yahoo = new YahooMainPage(driverLocal);
//        yahoo.open();
//        menuItems = yahoo.getSiteLinks();
//        assertTrue("Unable get Yahoo left-side menu items", menuItems.size()>0);
//        Collection<Object[]> result = new ArrayList<Object[]>();
//        for (WebElement e: menuItems){
//            String link = e.getText();
//            logger.info("Yahoo menu item is {}", link);
//            result.add(new Object[]{link});
//        }
//        driverLocal.quit();

        return Arrays.asList(new Object[][]{
               // {"Mail"},{"News"},{"Sports"},{"Finance"},{"Mail"}
//                {"Mail", 15, true},{"Mail", 6, true},{"Mail",8, true},{"Mail", 4, true},{"Mail", 15, true}
                {"Makers", 15},{"Mail", 15}
        });
    }

    @Test
    public void verifyTimeOfLoadingYahooSitesTest()  {
        long expLoadPageTime = timeout;
        String message = "'Yahoo %s' loading over %s sec";
        yahooPage.open();
        yahooPage.clickNavigationMenuItem(menuItem);
        yahooPage.waitPageLoadingCompleted(expLoadPageTime);
        assertTrue(String.format(message, menuItem, expLoadPageTime) , yahooPage.isPageLoaded());
    }

}

