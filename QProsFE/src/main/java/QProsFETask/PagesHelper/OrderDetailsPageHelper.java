package QProsFETask.PagesHelper;

import QProsFETask.SharedElements.SharedElementsHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class OrderDetailsPageHelper extends SharedElementsHelper {

    WebDriver driver;

    public OrderDetailsPageHelper(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public @FindBy (css = "div[class='woocommerce-billing-fields']")
    WebElement billingDetailsForm;

    public @FindBy (id = "billing_first_name")
    WebElement firstNameField;

    public @FindBy (id = "billing_last_name")
    WebElement lastNameField;

    public @FindBy (id = "billing_email")
    WebElement emailFiled;

    public @FindBy (id = "billing_phone")
    WebElement phoneField;

    public @FindBy (id = "billing_address_1")
    WebElement addressField;

    public @FindBy (id = "billing_city")
    WebElement cityField;

    public @FindBy (id = "billing_state")
    WebElement stateField;

    public @FindBy (id = "billing_postcode")
    WebElement postalCodeFiled;

    public @FindBy (css = "b[role='presentation']")
    WebElement openCountryList;

    public @FindBy (css = "input[aria-owns='select2-results-1']")
    WebElement countrySearchField;

    public @FindBy (css = "div[class='select2-result-label']")
    WebElement selectWantedCountry;

    public @FindBy (id = "place_order")
    WebElement placeOrderButton;

    public @FindBy (css = "div[class='blockUI blockOverlay']")
    WebElement loadingIcon;

    public @FindBy (css = "p[class='woocommerce-thankyou-order-received']")
    WebElement orderConfirmationMessage;


    public boolean billingDetailsFormIsDisplayed(){

        return billingDetailsForm.isDisplayed();
    }

    public void SelectCountry(){

        openCountryList.click();
        countrySearchField.sendKeys("Jordan");
        selectWantedCountry.click();

    }
    public void fillBillingForm(String firstName , String lastName , String email , String phoneNumber , String address , String city , String state , String postalCode){

        firstNameField.sendKeys(firstName);
        lastNameField.sendKeys(lastName);
        emailFiled.sendKeys(email);
        phoneField.sendKeys(phoneNumber);
        SelectCountry();
        addressField.sendKeys(address);
        cityField.sendKeys(city);
        stateField.sendKeys(state);
        waitForElementToAppearWithFindElement(postalCodeFiled);
        postalCodeFiled.sendKeys(postalCode);

    }

    public void completeOrder(){

        waitForElementToDisappear(loadingIcon);
        waitForElementToBeClickable(placeOrderButton);
        placeOrderButton.click();
    }

    public boolean orderConfirmationMessageIsDisplayed(){

        return orderConfirmationMessage.isDisplayed();
    }


}
