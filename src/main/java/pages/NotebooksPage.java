package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NotebooksPage extends PageBase {
    public NotebooksPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(linkText = "Apple MacBook Pro 13-inch")
    WebElement gotoProductdetails;
    public void selectProduct()
    {
        clickBTN(gotoProductdetails);
    }
}
