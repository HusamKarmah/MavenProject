package tests;

import java.util.List;
import java.util.concurrent.TimeUnit;
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
	
	// Create Crater CommonPage object, so can can call the elements and verify them.  
	CraterCommonPage commonpage = new CraterCommonPage(); 
	CraterItemsPage itempage = new CraterItemsPage(); 
	BrowserUtils utils = new BrowserUtils(); 
	craterLoginPage loginpage; 
	DButils dbutils; 
	/*	
	 	Create an item on UI.
    	Then go to database, and query from the items table using select * to get the information
    	Then verify the information that you have provided on UI is correct. 
    	Then update your Item on the UI, come back to database and verify the update is in effect.
    	Then delete the Item on the UI,  come back to database and verify the estimate no longer exist.
		CraterCommonPage commonpage */ 

  @Test
  public void craeteItem() {
	  // Navigate to crater wesite:
	
	  Assert.assertTrue(commonpage.commonPageItmesLink.isDisplayed());
	  
	  // click on the items tab
	  commonpage.commonPageItmesLink.click();
	  
	  // Verify user is at Add Items page:
	  Assert.assertTrue(itempage.addButton.isDisplayed());
	  
	  // click on the add item button 
	  
	  itempage.addButton.click();
	  
	  // verify that add new item model displayed 
	  Assert.assertTrue(itempage.newItemHeaderText.isDisplayed());
	  
	  //provide item information
	  itempage.additemNameField.sendKeys("Black sungalsses");
	  itempage.addItemPriceField.sendKeys("5000");
	  itempage.additemUnitField.click(); 
	  // wait until pc unit is displayed to give some time to catch up 
	  utils.waitUntilElementVisible(itempage.pc_unit);
	  // click on pc unit
	  itempage.pc_unit.click();
	  // give description
	  itempage.addItemDescription.sendKeys("Rectangle Sunglasses for Women Men Trendy Retro Fashion Sunglasses UV 400 Protection Square Frame");
	  // click on save item button
	  itempage.saveitemButton.click(); 
	  
	  // Wait until the message new item added displayed 
	  utils.waitUntilElementVisible(itempage.itemSuccessMessage);
	  Assert.assertTrue(itempage.itemSuccessMessage.isDisplayed()); 
	  
//	  WebElement newItem = Driver.getDriver().findElement(By.xpath("//a[text()='iphone case'")); 
//	  Assert.assertTrue(newItem.isDisplayed()); 
	  
	  // go to the database and select the created item 
	  
	  dbutils = new DButils(); 
	  String query ="select*from items where name='Black sungalsses'"; 
	  List <String> itemData = dbutils.selectArecord(query); 
	  System.out.println(itemData);
	  Assert.assertEquals(itemData.get(1), "Black sungalsses");
	  

	  
  }
  
  // we need to set up some wait before each function to allow driver enough processing time 
  @BeforeMethod
  public void setup() throws InterruptedException {
	  loginpage = new craterLoginPage(); 
	  Driver.getDriver().get(TestDataReader.getProperty("craterUrl"));
	  loginpage.login();
	  Driver.getDriver().manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);   
  }
  
  @AfterMethod
  public void wraper() {
	  Driver.quitDriver();
  }
  
}
