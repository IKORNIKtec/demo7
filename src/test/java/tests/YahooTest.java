package tests;

import config.ConfigProperties;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.yahoo.YahooMainPage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RunWith(value = Parameterized.class)
public class YahooTest {
    private static final Logger logger = LoggerFactory.getLogger(YahooTest.class);
    private WebDriver driver;
    private String menuItem;
    private static List<WebElement> menuItems;
    private YahooMainPage yahooPage;

    //parameters pass via this constructor
    public YahooTest(String value) {
        this.menuItem = value;
    }

    //Declares parameters here
//    @Parameters(name = "{index}: url is {0} ")
//    public static Iterable<Object[]> data1() {
//        return Arrays.asList(new Object[][]{
//                {1},
//                {2},
//                {8},
//                {4}
//        });
//    }

//    @BeforeClass
//    public static void prepareTestData(){
//
//    }

    @Before
    public void openBrowser(){
        driver = new FirefoxDriver();
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
    public void verifyTimeOfLoadingYahooSitesTest() {
        yahooPage.open();
        yahooPage.clickNavigationMenuItem(menuItem);
        yahooPage.waitPageLoadingCompleted(700);
    }

}

