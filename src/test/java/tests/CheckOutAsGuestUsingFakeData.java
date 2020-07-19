package tests;

import com.github.javafaker.Faker;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

public class CheckOutAsGuestUsingFakeData extends TestBase {
    SearchPage searchObj;
    ProductDetailsPage detailsObj;
    ShoppingCartPage cartpageObj;
    CheckOutPage checkoutObj;
    OrderDetailsPage orderObj;
    HomePage homeObj;
    NotebooksPage notebooksObj;
    Faker fakeData = new Faker();
    String firstname = fakeData.name().firstName();
    String lastname = fakeData.name().lastName();
    String email = fakeData.internet().emailAddress();
    String country = fakeData.country().name();
    String address = fakeData.address().streetAddress();
    String postalcode = fakeData.address().zipCode();
    String city = fakeData.address().cityName();
    String phone = fakeData.phoneNumber().cellPhone();
    String productName = "Nikon D5500 DSLR" ;
    @Test (priority = 1)
    public void UserCanSelectSubCategoryFromMainMenu()  {
        homeObj = new HomePage(driver);
        notebooksObj=new NotebooksPage(driver);
        detailsObj= new ProductDetailsPage(driver);
        homeObj.selectNotebooksMenu();
        Assert.assertTrue(driver.getCurrentUrl().contains("notebooks"));
        notebooksObj.selectProduct();
        detailsObj.AddProductToCart();
    }
    @Test (priority = 2)
    public void UserCanSearchWithAutoSuggest()  {
            searchObj = new SearchPage(driver);
            searchObj.ProductSearchUsingAutoSuggest("nikon");
            detailsObj = new ProductDetailsPage(driver);
            Assert.assertEquals(detailsObj.productNamebreadCrumb.getText(), productName);
    }
    @Test(priority = 3)
    @Severity(SeverityLevel.CRITICAL)
    @Description("Checkout As A Guest User Using Java Faker")
    public void UserCanAddProductToShoppingcartAndCheckout() throws InterruptedException
    {
        detailsObj = new ProductDetailsPage(driver);
        detailsObj.AddProduct2ToCart();
        Thread.sleep(1000);
        driver.navigate().to("https://demo.nopcommerce.com/"+"cart");
        cartpageObj = new ShoppingCartPage(driver);
        cartpageObj.OpenCheckOutPage();
        Thread.sleep(1000);
        checkoutObj = new CheckOutPage(driver);
        checkoutObj.CheckOutAsGuest();
        checkoutObj.GuestUserCheckoutProduct(firstname,lastname, email, country, address, postalcode,city,phone, productName);
        checkoutObj.ConfirmOrder();
        Thread.sleep(1000);
        Assert.assertTrue(checkoutObj.thankyouLBL.isDisplayed());
    }
    @Test(priority = 4)
    public void UserCanViewOrderDetails() throws InterruptedException
    {
        checkoutObj.ViewOrderDetails();
        Assert.assertTrue(driver.getCurrentUrl().contains("orderdetails"));
        orderObj = new OrderDetailsPage(driver);
        orderObj.DownloadPDFinvoiceOrder();
        Thread.sleep(2000);
        orderObj.printOrderetails();
        Thread.sleep(2000);
    }
}
