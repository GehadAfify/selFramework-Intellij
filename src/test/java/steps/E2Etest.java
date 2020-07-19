package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.*;
import tests.TestBase;

public class E2Etest  extends TestBase {
	SearchPage searchObj;
	ProductDetailsPage detailsObj;
	ShoppingCartPage cartpageObj;
	CheckOutPage checkoutObj;
	OrderDetailsPage orderObj;
	String productName = "Apple MacBook Pro 13-inch" ;
	
	
	@Given("^user is in home page$")
	public void the_user_in_the_home_page() {
		driver.navigate().to("https://demo.nopcommerce.com/");
	}
	@When ("he search for \"<productName>\"")
	public void he_search_for_productName()throws InterruptedException
	{
		searchObj = new SearchPage(driver);
		searchObj.ProductSearchUsingAutoSuggest(productName);
		detailsObj = new ProductDetailsPage(driver);
		Assert.assertEquals(detailsObj.productNamebreadCrumb.getText(), productName);
	}
	
	@When ("choose to buy two item")
	public void choose_to_buy_item()
	{
		detailsObj = new ProductDetailsPage(driver);
		detailsObj.AddProductToCart();
		driver.navigate().to("https://demo.nopcommerce.com/"+"cart");
	}
	@When ("moves to checkout cart and enter personal details on checkout page and place the order")
	public void moves_to_checkout_cart_and_enter_personal_details_on_checkout_page_and_place_the_order() throws InterruptedException
	{
		checkoutObj = new CheckOutPage(driver);
		cartpageObj.OpenCheckOutPage();
		checkoutObj.GuestUserCheckoutProduct("gehadafify","gog", "testw@mail.com", "Egypt", "22ds", "111211","cairo","896342", productName);
		Assert.assertTrue(checkoutObj.prodcutname.isDisplayed());
		Assert.assertTrue(checkoutObj.prodcutname.getText().contains(productName));
		checkoutObj.ConfirmOrder();
		Assert.assertTrue(checkoutObj.thankyouLBL.isDisplayed());
	}
	@Then ("he can view the order and download the invoice")
	public void he_can_view_the_order_and_download_the_invoice() throws InterruptedException {
		orderObj = new OrderDetailsPage(driver);
		checkoutObj.ViewOrderDetails();
		Assert.assertTrue(driver.getCurrentUrl().contains("orderdetails"));
		orderObj.DownloadPDFinvoiceOrder();
		Thread.sleep(2000);
		orderObj.printOrderetails();
	}
}
