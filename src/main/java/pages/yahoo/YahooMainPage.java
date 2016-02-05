package pages.yahoo;

import config.ConfigProperties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.BasePage;
import java.util.List;

public class YahooMainPage extends BasePage {
    private static final Logger logger = LoggerFactory.getLogger(YahooMainPage.class);
    private final String MENU_ITEM_LOCATOR = "//*[@id='Navigation']//span[contains(.,'%s')]";
    private final String SEE_MORE_MENU_ITEM_LOCATOR = "//*[contains(@class,'js-navlinks-seemore-menu')]//span[contains(.,'%s')]";

    @FindBy(xpath = "//*[@id='Navigation']//span")
    List<WebElement> menuItems;

//    @FindBy(css = ".navlist li[style=''] span")
//    List<WebElement> menuItems;

    @FindBy(css = ".js-navlinks-seemore-menu span")
    List<WebElement> seeMoreMenuItems;


    @FindBy(css = "[aria-label='More Navlinks']")
    WebElement moreYahooLink;

    public YahooMainPage(WebDriver driver) {
        super(driver);
    }

    public List<WebElement> getSiteLinks(){
        return menuItems;
    }
    public List<WebElement> getMoreSiteLinks(){
        return seeMoreMenuItems;
    }

    public void open(String url){
        driver.get(url);
    }
    public void open(){
        open(ConfigProperties.YAHOO_BASE_URL);
    }

    private WebElement getNavigationMenuItem(String menuName) {
        return driver.findElement(By.xpath(String.format(MENU_ITEM_LOCATOR, menuName)));
    }

    private WebElement getSeeMoreMenuItem(String menuName) {
        return driver.findElement(By.xpath(String.format(SEE_MORE_MENU_ITEM_LOCATOR, menuName)));
    }

    public void clickNavigationMenuItem(String menuName) {
        if(!getNavigationMenuItem(menuName).isDisplayed()){
            logger.info("Click '...More' to see hidden menu items");
            Actions action = new Actions(driver);
            action.moveToElement(moreYahooLink).build().perform();
            getSeeMoreMenuItem(menuName).click();
        }else{
            getNavigationMenuItem(menuName).click();
        }
        logger.info("Menu item '{}' was been clicked", menuName);
    }

    public List<WebElement> getAllMenuItems(){
        List<WebElement> allItems =menuItems;
        allItems.addAll(seeMoreMenuItems);
        return allItems;
    }

}
