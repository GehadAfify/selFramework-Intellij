package tests;

import data.JsonDataReader;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.UserRegisterationPage;


import java.io.IOException;

public class UserRegisterationTesteithDDTandJSONFile extends TestBase 
{
	HomePage homeObj ;
	UserRegisterationPage registerObj;
	LoginPage loginObj;
	
	@Test (priority = 1 , alwaysRun = true)
	public void userRegisterSuccessfully() throws  IOException, ParseException
	{
		JsonDataReader jsonreader = new JsonDataReader();
		jsonreader.jsonReader();
		homeObj = new HomePage(driver);
		homeObj.openRegisterationPage();
		registerObj = new UserRegisterationPage(driver);
		registerObj.userRegisteration(jsonreader.firstname,jsonreader.lastname,jsonreader.email,jsonreader.password);
		Assert.assertTrue(registerObj.successMsg.getText().contains("Your registration completed"));
		registerObj.userLogout();
		homeObj.openLoginPage();
		loginObj = new LoginPage(driver);
		loginObj.userLogin(jsonreader.email,jsonreader.password);
		Assert.assertTrue(registerObj.logoutLink.isDisplayed());
		registerObj.userLogout();

	}
	
}
