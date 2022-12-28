package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.Driver;
import utils.TestDataReader;

public class craterLoginPage {
	
	public craterLoginPage() {
		
		PageFactory.initElements(Driver.getDriver(), this);
	
	}
	
	
	@FindBy(xpath ="//div[@name='email']/input")
	public WebElement useremail; 
	
	@FindBy(xpath = "//div[@name='password']/input")
	public WebElement password; 
	
	@FindBy(linkText ="Forgot Password?")
	public WebElement forgotPasswordLink; 


	@FindBy(xpath ="//button[text() ='Login']")
	public WebElement loginButton; 
	
	@FindBy(xpath ="//p[contains(text(), 'Copyright @ Crater Invoice, Inc. 2022')]")
	public WebElement copyRightText; 
	
	
	@FindBy(xpath ="//h1[contains(text(), 'Simple Invoicing for')]")
	public WebElement bussinesSlogan; 
	
	@FindBy(xpath ="//p[contains(text(), 'Crater helps you track expenses')]")
	public WebElement bussinessSubText; 
	
	@FindBy(xpath ="//p[contains(text(), 'These credentials do not match our records.')]")
	public WebElement invalidUserErrorMessage; 
	
	@FindBy(xpath = "//span[text()='Field is required']")
	public WebElement fieldRequired; 
	
	
	public void login() throws InterruptedException {
		 useremail.sendKeys(TestDataReader.getProperty("craterVaildEmail"));
		  Thread.sleep(1000);
		 password.sendKeys(TestDataReader.getProperty("craterValidPassword"));
		  Thread.sleep(1000);
		  loginButton.click();
	}
	

}
