package tests;
import java.util.concurrent.TimeUnit;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.AmazonPage;
import utils.Driver;

public class AmazonSearch {
	@BeforeMethod
	public void setup() {

		Driver.getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		Driver.getDriver().manage().window().maximize();
	}

	@Test(dataProvider = "searchItems")
	public void AmazonSearchTest(String item) {
		AmazonPage amazonpage = new AmazonPage();
		Driver.getDriver().get("https://amazon.com");

		amazonpage.searchBox.sendKeys(item);
		amazonpage.searchBtn.click();
		String actualText = amazonpage.searchItemText.getText().substring(1, item.length() + 1);
		System.out.println(actualText);
		Assert.assertEquals(actualText, item);

		
	}
	// "Coffee Maker" coffee maker
	@DataProvider
	public String[] searchItems() {

		String[] items = new String[6];
		items[0] = "coffee mug";
		items[1] = "coffee maker";
		items[2] = "Sugar container";
		items[3] = "Salt shaker";
		items[4] = "water filter";
		items[5] = "Kitchen Knife";
		return items;

	}

	@AfterMethod
	public void quitDriver() {
		Driver.quitDriver();
	}
}
