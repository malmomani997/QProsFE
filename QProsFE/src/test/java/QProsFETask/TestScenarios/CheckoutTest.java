package QProsFETask.TestScenarios;


import QProsFETask.Resources.TestData;
import QProsFETask.TestHelpers.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class CheckoutTest extends BaseTest {

    @Test()
    public void completeCheckoutScenario()  {

        Assert.assertTrue(getHomePageHelper().thinkingHtmlCourseIsVisible() , "The course wasn't displayed");
        getHomePageHelper().clickAddToCartButton(getHomePageHelper().thinkingHtmlAddToCartButton);
        String courseNameInHomePage = getHomePageHelper().getCourseName();
        String coursePriceInHomePage = getHomePageHelper().getCoursePrice();


        getHomePageHelper().clickOnCartIcon();

        String courseNameInCartPage = getCartPageHelper().getProductNameInCart();
        String coursePriceInCartPage = getCartPageHelper().getProductPriceInCart();

        Assert.assertEquals(courseNameInCartPage , courseNameInHomePage , "The course wasn't found in cart");
        Assert.assertEquals(coursePriceInCartPage , coursePriceInHomePage , "Incorrect price of the course");

        getCartPageHelper().proceedToCheckoutPageButton();

        Assert.assertTrue(getOrderDetailsPageHelper().billingDetailsFormIsDisplayed() , "Billing Details Form isn't displayed");

        getOrderDetailsPageHelper().fillBillingForm(TestData.firstName , TestData.lastName ,TestData.email ,TestData.phoneNumber ,TestData.address ,TestData.city ,TestData.state, TestData.postalCode);


        getOrderDetailsPageHelper().completeOrder();

        Assert.assertTrue(getOrderDetailsPageHelper().orderConfirmationMessageIsDisplayed() , "Order confirmation message wasn't displayed");
    }
}