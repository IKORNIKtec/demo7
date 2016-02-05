package tests;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.yahoo.YahooMainPage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertTrue;

@RunWith(value = Parameterized.class)
public class YahooTest extends BaseTest{
    private static final Logger logger = LoggerFactory.getLogger(YahooTest.class);

    private String menuItem;
    //private static List<WebElement> menuItems;
    private YahooMainPage yahooPage;
    private long timeout;

    public YahooTest(String value, long timeout) {
        this.menuItem = value;
        this.timeout = timeout;
    }

    @Before
    public void clearCookie(){
        driver.manage().deleteAllCookies();
        yahooPage = new YahooMainPage(driver);
    }

    @Parameters(name = "{index}: Yahoo {0}")
    public static Iterable<Object[]> data() {
//        HtmlUnitDriver driverLocal = new HtmlUnitDriver();
        FirefoxDriver driverLocal = new FirefoxDriver();
        YahooMainPage yahoo = new YahooMainPage(driverLocal);
        yahoo.open();
        List<WebElement> menuItems = yahoo.getSiteLinks();
        logger.info("Main menu items count is {}", menuItems.size());
        List<WebElement> seeMore = yahoo.getMoreSiteLinks();
        logger.info("'See more' menu items count is {}", seeMore.size());
        menuItems.addAll(seeMore);

        assertTrue("Unable get Yahoo left-side menu items", menuItems.size() > 0);
        Collection<Object[]> result = new ArrayList<Object[]>();
        for (WebElement e: menuItems){
            String link = e.getAttribute("textContent");
            logger.info("Yahoo menu item is {}", link);
            logger.info("Yahoo menu item is {}", e.getCssValue("style"));
            logger.info("Yahoo menu item is {}", e.getCssValue("style.display"));
            logger.info("Yahoo menu item is {}", e.getAttribute("style"));
            logger.info("Yahoo menu item is {}", e.getAttribute("style.display"));
            result.add(new Object[]{link});
        }
        driverLocal.quit();
        return  result;
//        return Arrays.asList(new Object[][]{
//               // {"Mail"},{"News"},{"Sports"},{"Finance"},{"Mail"}
////                {"Mail", 15, true},{"Mail", 6, true},{"Mail",8, true},{"Mail", 4, true},{"Mail", 15, true}
//                {"Makers", 15},{"Mail", 15}
//        });

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

