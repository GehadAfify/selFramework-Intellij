package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ComparePage extends PageBase {

	public ComparePage(WebDriver driver) {
		super(driver);
	}
	@FindBy (css = "a.clear-list")
	WebElement clearlistBTN;

	@FindBy (css = "div.no-data")
	public WebElement nodataLBL;
	
//	@FindBy (css = "table.compare-products-table")
//	WebElement table;

	@FindBy (tagName = "tr")
	public List<WebElement> rows;

	@FindBy (tagName = "td")
	public List<WebElement> columns;
	
	@FindBy(linkText = "Asus N551JK-XO076H Laptop")
	public WebElement firstproductname;
	
	@FindBy(linkText = "Apple MacBook Pro 13-inch")
	public WebElement secondproductname;
	
	public void ClearCompareList() 
	{
		clickBTN(clearlistBTN);
	}
	public void CompareProducts() 
	{
		//get all rows
		System.out.println(rows.size());
		//get data from each row
		for(WebElement row : rows) 
		{
			System.out.println(row.getText()+"\t");
			for (WebElement col : columns)
			{   System.out.println(col.getText()+"\t");            }
		}
	}
}
