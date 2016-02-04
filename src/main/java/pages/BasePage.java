package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {
    public static long WAIT_TIME_SECONDS = 30;
    private static By WARN_LOCATOR = By.className("warning");
    protected WebDriver driver;

    public BasePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isWarningDisplayed() {
        waitPageLoadingCompleted();
        return driver.findElements(WARN_LOCATOR).size()>0;
    }

    protected void waitForElementPresence(WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, WAIT_TIME_SECONDS);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitPageLoadingCompleted(){
        waitPageLoadingCompleted(WAIT_TIME_SECONDS);
    }

    public void waitPageLoadingCompleted(final long timeoutSeconds){
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeoutSeconds);

            ExpectedCondition<Boolean> pageLoadCondition = new
                    ExpectedCondition<Boolean>() {
                        public Boolean apply(WebDriver driver) {
                            return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
                        }
                    };

            wait.until(pageLoadCondition);
        }catch (TimeoutException e){

        }
    }

    public boolean isPageLoaded(){
        return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
    }
}

