package steps;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.HomePage;
import pages.LoginPage;
import tests.TestBase;

public class LoginFeature extends TestBase {
    HomePage homeObj ;
    LoginPage loginObj;
    @Given("^user in the home page$")
    public void user_in_the_home_page()  {
        homeObj = new HomePage(driver);
        homeObj.openLoginPage();
    }
    @When("i click on login link")
    public void i_click_on_login_link() {
        Assert.assertTrue(driver.getCurrentUrl().contains("login"));
    }
    @When("i enter {string},{string}")
    public void i_enter(String email, String password) {
        loginObj = new LoginPage(driver);
        loginObj.userLogin(email,password);
    }
    @Then("user login successfully")
    public void user_login_successfully() {
       Assert.assertTrue(loginObj.logoutlink.isDisplayed());
    }
}
