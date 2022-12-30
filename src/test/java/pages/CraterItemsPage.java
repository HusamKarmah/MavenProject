package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.Driver;

public class CraterItemsPage {
	
public CraterItemsPage() {
		
		PageFactory.initElements(Driver.getDriver(), this);
	}


	@FindBy(xpath ="//h3[contains(text(), 'Items')]")
	public WebElement itemsText; 
	
	@FindBy(xpath ="//button[text() = ' Add Item']")
	public WebElement addButton; 
	
	@FindBy(xpath ="//button[text() = 'Filter ']")
	public WebElement filterItemButton; 
	
	@FindBy(xpath ="//div[@name= 'name']/input")
	public WebElement filterNameField; 
	
	
	// Add item Window
	@FindBy(xpath="//h3[text()= 'New Item']")
	public WebElement newItemHeaderText; 
	
	@FindBy(xpath="//div[@class='relative rounded-md shadow-sm font-base']/input")
	public WebElement additemNameField; 
	
	@FindBy(xpath="//div[@class='flex flex-col mt-1']/input")
	public WebElement addItemPriceField; 
	
	@FindBy(xpath="(//div[@class='flex flex-col mt-1']/div/input)[2]")
	public WebElement additemUnitField; 
	
	@FindBy(xpath="//textarea[@name='description']")
	public WebElement addItemDescription;
	
	@FindBy(xpath="//button[text()=' Save Item']")
	public WebElement saveitemButton;
	
	@FindBy(xpath="//span[text()='pc']")
	public WebElement pc_unit;
	
	@FindBy(xpath="//p[text()='Item created successfully']")
	public WebElement itemSuccessMessage; 
	
	
	//Edit item Window
	@FindBy(xpath="//h3[text()= 'Edit Item']")
	public WebElement editItemHeaderText; 
	
	@FindBy(xpath="//div[@class='relative rounded-md shadow-sm font-base']/input")
	public WebElement editNameField; 
	
	@FindBy(xpath="//button[text()=' Update Item']")
	public WebElement updateItemButton;
	
	@FindBy(xpath="//p[text()='Item updated successfully']")
	public WebElement itemUpdatedSuccessMessage; 
	
	
	// Delete the new Item
	@FindBy(xpath="//a[text()=' Delete']")
	public WebElement deleteitemButton; 
	
	@FindBy(xpath="//button[text()='Ok']")
	public WebElement deleteOkButton; 
	
	@FindBy(xpath="//p[text()='Item deleted successfully']")
	public WebElement itemDeletedSuccessMessage; 
	
	
}
