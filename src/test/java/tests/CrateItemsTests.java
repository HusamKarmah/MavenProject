package tests;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CraterCommonPage;
import pages.CraterItemsPage;
import pages.craterLoginPage;
import utils.BrowserUtils;
import utils.DButils;
import utils.Driver;
import utils.TestDataReader;

public class CrateItemsTests {

	// Create Crater CommonPage object, so can can call the elements and verify
	// them.
	CraterCommonPage commonpage = new CraterCommonPage();
	CraterItemsPage itempage = new CraterItemsPage();
	BrowserUtils utils = new BrowserUtils();
	craterLoginPage loginpage;
	DButils dbutils;
	String newItemName = "Iphoe 11 case";

	/*
	 * Create an item on UI. Then go to database, and query from the items table
	 * using select * to get the information Then verify the information that you
	 * have provided on UI is correct. 
	 * Then update your Item on the UI, come back to
	 * database and verify the update is in effect. 
	 * Then delete the Item on the UI,
	 * come back to database and verify the estimate no longer exist.
	 * CraterCommonPage commonpage
	 */

	@Test
	public void craeteItem() {
		// Navigate to crater web site:

		Assert.assertTrue(commonpage.commonPageItmesLink.isDisplayed());

		// click on the items tab
		commonpage.commonPageItmesLink.click();

		// Verify user is at Add Items page:
		Assert.assertTrue(itempage.addButton.isDisplayed());

		// click on the add item button

		itempage.addButton.click();

		// verify that add new item model displayed
		Assert.assertTrue(itempage.newItemHeaderText.isDisplayed());

		// provide item information
		newItemName = newItemName + utils.randomNumber();
		itempage.additemNameField.sendKeys(newItemName);
		itempage.addItemPriceField.sendKeys("5000");
		itempage.additemUnitField.click();
		// wait until pc unit is displayed to give some time to catch up
		utils.waitUntilElementVisible(itempage.pc_unit);
		// click on pc unit
		itempage.pc_unit.click();
		// give description
		itempage.addItemDescription.sendKeys("Variety colors, solid protection, free shipping");
		// click on save item button
		itempage.saveitemButton.click();

		// Wait until the message new item added displayed
		utils.waitUntilElementVisible(itempage.itemSuccessMessage);
		Assert.assertTrue(itempage.itemSuccessMessage.isDisplayed());

		WebElement newItem = Driver.getDriver().findElement(By.xpath("//a[text()='"+newItemName+"']")); 
		Assert.assertTrue(newItem.isDisplayed()); 

		// go to the database and select the created item
		System.out.println("New Item added with number: " + newItemName);
		dbutils = new DButils();
		String query = "select*from items where name='" + newItemName + "';";
		List<String> itemData = dbutils.selectArecord(query);
		System.out.println("Item id: " + itemData.get(0));
		System.out.println("Item Name: " + itemData.get(1));
		// verify item created with database 
		Assert.assertEquals(itemData.get(1), newItemName);
		

		//Then update your Item on the UI. 
		newItem.click(); 
		//verify user is on the edit item page 
		Assert.assertTrue(itempage.editItemHeaderText.isDisplayed()); 
		
		// Edit the item description 
		
		//Clear the description field 
		utils.clearTextOfTheFieldWindows(itempage.addItemDescription); 
		
		//Send the new description
		itempage.addItemDescription.sendKeys("New colors are arrived, free shipping all over USA"); 
		
		//Click the update button
		itempage.updateItemButton.click(); 
		// wait for the update message showing up
		utils.waitUntilElementVisible(itempage.itemUpdatedSuccessMessage); 
		
		//Verify the message is visible
		Assert.assertTrue(itempage.itemUpdatedSuccessMessage.isDisplayed()); 
		
		
		//come back to database and verify the update is in effect. 
		
		System.out.println("New Item added with number: " + newItemName);
		dbutils = new DButils();
		String updateQuery = "select*from items where name='" + newItemName + "';";
		List<String> itemUpdateData = dbutils.selectArecord(updateQuery);
		System.out.println("Item updated description: " + itemUpdateData.get(2));
		// verify item description is updated in database 
		Assert.assertEquals(itemUpdateData.get(2),"New colors are arrived, free shipping all over USA");
		
		 //Then delete the Item on the UI.
		 //come back to database and verify the estimate no longer exist.
		
		//(//a[text()='"+newItemName+"']//parent::td)//following-sibling::td[4]//button"
	
		// finding the 3 dots and click it
		Driver.getDriver().findElement
			(By.xpath("(//a[text()='"+newItemName+"']//parent::td)//following-sibling::td[4]//button")).click(); 
		
		// click on delete button
		utils.waitUntilElementVisible(itempage.deleteitemButton); 
		itempage.deleteitemButton.click(); 
		utils.waitUntilElementVisible(itempage.deleteOkButton); 
		itempage.deleteOkButton.click(); 
		
		//verify the delete message displayed
		utils.waitUntilElementVisible(itempage.itemDeletedSuccessMessage); 
		Assert.assertTrue(itempage.itemDeletedSuccessMessage.isDisplayed()); 
		
		// Database query and verify the item is no longer exist 
		String deleteQuery = "select*from items where name='" + newItemName + "';";
		List<String> itemDeleteData = dbutils.selectArecord(deleteQuery);
		Assert.assertTrue(itemDeleteData.isEmpty()); 
	}

	// we need to set up some wait before each function to allow driver enough
	// processing time
	@BeforeMethod
	public void setup() throws InterruptedException{
		loginpage = new craterLoginPage();
		Driver.getDriver().get(TestDataReader.getProperty("craterUrl"));
		loginpage.login();
		Driver.getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	@AfterMethod
	public void wraper() {
		Driver.quitDriver();
	}

}
