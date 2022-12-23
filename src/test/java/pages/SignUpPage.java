package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.Driver;

public class SignUpPage {
	
	public  SignUpPage() {
		PageFactory.initElements(Driver.getDriver(), this);
	}
	
	@FindBy(xpath="//h2[text()='Sign Up']")
	public WebElement signUpText; 
	
	
	@FindBy(xpath= "//label[text()='Email Address']")
	public WebElement emailFieldLabel; 
	
	@FindBy(name= "email")
	public WebElement emailField; 
	
	@FindBy(xpath= "//label[text()='First Name']")
	public WebElement firstNameLabel; 
	
	@FindBy(name = "firstName")
	public WebElement firstNameField;
	
	@FindBy(xpath= "//label[text()='Last Name']")
	public WebElement lastNameLabel; 
	
	@FindBy(name = "lastName")
	public WebElement lastNameField;
	
	@FindBy(xpath="//label[text()='Password']")
	public WebElement passwordLabel; 
	
	@FindBy(name = "password")
	public WebElement passwordField;
	
	@FindBy(xpath ="//span[text()='Login with Google']")
	public WebElement signWithGoogleLink; 
	
	@FindBy(xpath ="//span[text()='Login with Facebook']")
	public WebElement signWithFaceBookLink; 
	
	@FindBy (id = "subscribe")
	public WebElement subscribeCheckBox;
	
	@FindBy (xpath="//label[text()='Subscribe To Our Newsletter']")
	public WebElement subscribeToNewsLetter; 
	
	@FindBy(xpath="//*[@id=\"root\"]/div/main/div/div/div/form/div[3]/button")
	public WebElement SignUpBtn; 
	
	@FindBy(xpath="//*[@id=\"root\"]/div/main/div/div/div/form/div[3]/a")
	public WebElement backToLoginLink; 
	
	
	
}
