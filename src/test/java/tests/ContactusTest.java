package tests;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ContactusPage;
import pages.HomePage;

public class ContactusTest extends TestBase
{
	HomePage homeObj ;
	ContactusPage contactusOBJ;
	
	String fullname = "gehad afify";
	String email = "test@gmail.com";
	String enquiry = "test automation with selenium";

	@Test
	@Severity(SeverityLevel.CRITICAL)
	@Description("Contact us Test case")
	@Link(name = "hello",url = "http://google.com")
	@Epic("EP 120")
	@Flaky
	public void UserCanContatctUs() 
	{
		homeObj = new HomePage(driver);
		contactusOBJ = new ContactusPage(driver);
		homeObj.openContactusPage();
		contactusOBJ.ContactUs(fullname, email, enquiry);
		Assert.assertTrue(contactusOBJ.successMsg.getText().contains("Your enquiry has been successfully sent to the store owner."));
	}
}
