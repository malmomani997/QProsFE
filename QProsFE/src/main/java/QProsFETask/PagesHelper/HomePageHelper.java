package QProsFETask.PagesHelper;

import QProsFETask.SharedElements.SharedElementsHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePageHelper extends SharedElementsHelper {

    WebDriver driver;

    public HomePageHelper(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public @FindBy (css = "div[class='col3-1 sub_column sub_column_1-0-2-1 sub_column_post_22']")
    WebElement thinkingHtmlCourse;


    public @FindBy (css = "a[data-product_id='163']")
    WebElement thinkingHtmlAddToCartButton;

    public @FindBy (css = "a[class='wpmenucart-contents']")
    WebElement shoppingCartIcon;


    public boolean thinkingHtmlCourseIsVisible(){

       return thinkingHtmlCourse.isDisplayed();
   }

   public void clickAddToCartButton(WebElement webElement){

        waitForElementToAppearWithFindElement(webElement);
        webElement.click();
   }

   public void clickOnCartIcon(){


        shoppingCartIcon.click();
   }

   public String getCourseName(){

      return  thinkingHtmlCourse.findElement(By.cssSelector("h3")).getText();
   }

   public String getCoursePrice(){


        return thinkingHtmlCourse.findElement(By.xpath("//ins/span")).getText();
   }

}
