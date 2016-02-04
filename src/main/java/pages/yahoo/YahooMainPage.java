package pages.yahoo;

import config.ConfigProperties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.BasePage;
import java.util.List;

public class YahooMainPage extends BasePage {
    private static final Logger logger = LoggerFactory.getLogger(YahooMainPage.class);
    private final String MENU_ITEM_LOCATOR = "//*[@id='Navigation']//span[contains(.,'%s')]";

    @FindBy(css = ".navlist li[style=''] span")
    List<WebElement> sitesUrls;

    public YahooMainPage(WebDriver driver) {
        super(driver);
    }

    public List<WebElement> getSiteLinks(){
        return sitesUrls;
    }

    public void open(String url){
        driver.get(url);
    }
    public void open(){
        driver.get(ConfigProperties.YAHOO_BASE_URL);
    }

    private WebElement getNavigationMenuItem(String menuName) {
        return driver.findElement(By.xpath(String.format(MENU_ITEM_LOCATOR, menuName)));
    }

    public void clickNavigationMenuItem(String menuName) {
        logger.info("Click menu item '{}'", menuName);
        getNavigationMenuItem(menuName).click();
    }

}

