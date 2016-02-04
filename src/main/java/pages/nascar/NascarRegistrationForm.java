package pages.nascar;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import pages.BasePage;

public class NascarRegistrationForm extends BasePage {

    @FindBy(css=".navigationLinks [href~='/Login.aspx']")
    WebElement manageMyBookingBtn;

    @FindBy(id="sddDepartureAirportID")
    WebElement fromDropdown;

    @FindBy(id="acpGeographyLevel2ID")
    WebElement destinationField;

    @FindBy(xpath="//*[@id='acpGeographyLevel2ID_Results']/div[1]/span[1]")
    WebElement destinationResultSuggestingField;

    @FindBy(id="btnSearch")
    WebElement searchBtn;

    @FindBy(id="calDepartureDate_MonthYear")
    WebElement monthDropdown;

    @FindBy(id="calDepartureDate_Day")
    WebElement dayDropdown;

    @FindBy(className="warning")
    WebElement warningMessage;

    public NascarRegistrationForm(WebDriver driver) {
        super(driver);
    }

    public void clickManageMyBooking() {
        manageMyBookingBtn.click();
    }

    public void selectFrom(String value){
        waitForElementPresence(fromDropdown);
        Select from = new Select(fromDropdown);
        from.selectByVisibleText(value);
    }

    public void typeDestination(String value) {
        destinationField.sendKeys(value);
        waitForElementPresence(destinationResultSuggestingField);
        destinationResultSuggestingField.click();
    }

    public void clickSearchButton() {
        searchBtn.click();
    }
}

