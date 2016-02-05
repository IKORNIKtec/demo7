package tests;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.yahoo.YahooMainPage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertTrue;

@RunWith(value = Parameterized.class)
public class YahooDynamicTest extends BaseTest{
    private static final Logger logger = LoggerFactory.getLogger(YahooDynamicTest.class);

    private String menuItem;

    private YahooMainPage yahooPage;



    @Before
    public void clearCookie(){
        yahooPage = new YahooMainPage(driver);
    }

    @Test
    public void verifyTimeOfLoadingYahooSitesTest()  {
        long expLoadPageTime = 7;
        String message = "'Yahoo %s' loading over %s sec";
        for(WebElement e:yahooPage.getAllMenuItems()) {
            yahooPage.open();
            yahooPage.clickNavigationMenuItem(menuItem);
            yahooPage.waitPageLoadingCompleted(expLoadPageTime);
            assertTrue(String.format(message, menuItem, expLoadPageTime), yahooPage.isPageLoaded());
        }
    }


}

