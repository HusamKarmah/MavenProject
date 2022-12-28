package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.Driver;

public class CraterCommonPage {
	
	// we need constructor to initialize the elements on that page, force the driver to read the elements 
	public CraterCommonPage() {
		
		PageFactory.initElements(Driver.getDriver(), this);
	}

		@FindBy(xpath = "//a[contains(text(), 'Items')]")
		public WebElement commonPageItmesLink; 
		
		
	
	
	
	
	
	
}
