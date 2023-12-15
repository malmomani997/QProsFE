package QProsFETask.PagesHelper;

import QProsFETask.SharedElements.SharedElementsHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPageHelper extends SharedElementsHelper {

    WebDriver driver;

    public CartPageHelper(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public @FindBy (xpath= "//tr/td[@class='product-name']")
    WebElement productNameInCart;

    public @FindBy (xpath = "//tr/td[@class='product-price']")
    WebElement productPriceInCart;

    public @FindBy(css = "a[class='checkout-button button alt wc-forward']")
    WebElement proceedToCheckoutButton;

    public String getProductNameInCart(){

        return productNameInCart.getText();
    }

    public String getProductPriceInCart(){

        return productPriceInCart.getText();
    }

    public void proceedToCheckoutPageButton(){

        proceedToCheckoutButton.click();
    }

}
