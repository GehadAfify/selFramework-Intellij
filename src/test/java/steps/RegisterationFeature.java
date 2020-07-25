package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.HomePage;
import pages.UserRegisterationPage;
import tests.TestBase;

public class RegisterationFeature extends TestBase {
    HomePage homeObj;
    UserRegisterationPage registerObj;
    @Given("^the user in the home page$")
    public void the_user_in_the_home_page()  {
        homeObj = new HomePage(driver);
        homeObj.openRegisterationPage();
    }
    @When("^I click on register link$")
    public void i_click_on_register_link()  {
        Assert.assertTrue(driver.getCurrentUrl().contains("register"));
    }
    @When("^I entered \"([^\"]*)\" , \"([^\"]*)\" , \"([^\"]*)\" , \"([^\"]*)\"$")
    public void i_entered(String firstname, String lastname, String email, String password) throws InterruptedException {
        registerObj = new UserRegisterationPage(driver);
        Thread.sleep(1000);
        registerObj.userRegisteration(firstname, lastname,email,password);
    }
    @Then("^The registration page displayed successfully$")
    public void the_registration_page_displayed_successfully()  {
        registerObj.userLogout();
    }
}
