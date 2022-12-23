package tests;

import java.util.concurrent.TimeUnit;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CommonPage;
import pages.SignUpPage;
import utils.Driver;
import utils.TestDataReader;

public class SignupTests {
	@BeforeMethod 
	public void setup() {
		Driver.getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	
	@Test(groups ="smoke-test")
	public void test2() throws InterruptedException {
		Driver.getDriver().get("https://google.com");
		System.out.println("Test2" + "Thread: " + Thread.currentThread().getId());
	}
	
	@Test(groups ="smoke-test")
	public void test3() throws InterruptedException {
		Driver.getDriver().get("https://ebay.com");
		System.out.println("Test3"+ "Thread: " + Thread.currentThread().getId());
	}
	@Test(groups = {"smoke-test", "signupPage"},description = "verify signup components")
	public void signUpPageTest() throws InterruptedException {
		CommonPage commonPage = new CommonPage();
		SignUpPage signuppage = new SignUpPage();

		// when
		Driver.getDriver().get(TestDataReader.getProperty("appurl"));
		// and click
		Assert.assertTrue(commonPage.welocomLink.isDisplayed()); // verify we're on the correct page
		commonPage.welocomLink.click();
		// and click
		Thread.sleep(500);
		Assert.assertTrue(commonPage.signUpButton.isDisplayed());
		commonPage.signUpButton.click();
		Thread.sleep(500);
		// verify the sign up page web components
		Assert.assertTrue(signuppage.signUpText.isDisplayed());
		Thread.sleep(500);
		// email field verification
		Assert.assertTrue(signuppage.emailFieldLabel.isDisplayed());
		Assert.assertEquals(signuppage.emailField.getAttribute("placeholder"), "Please Enter Your Email");
		Thread.sleep(500);
		// first name field verification
		Assert.assertTrue(signuppage.firstNameLabel.isDisplayed());
		Assert.assertEquals(signuppage.firstNameField.getAttribute("placeholder"), "Please Enter Your First Name");
		Thread.sleep(500);
		// last name field verification
		Assert.assertTrue(signuppage.lastNameLabel.isDisplayed());
		Assert.assertEquals(signuppage.lastNameField.getAttribute("placeholder"), "Please Enter Your Last Name");

		Thread.sleep(500);
		// password name field verification
		Assert.assertTrue(signuppage.passwordLabel.isDisplayed());
		Assert.assertEquals(signuppage.passwordField.getAttribute("placeholder"), "Please Enter Your Password");

		Thread.sleep(500);
		// google and faceBook login verification
		Assert.assertTrue(signuppage.signWithGoogleLink.isDisplayed());
		Assert.assertTrue(signuppage.signWithFaceBookLink.isDisplayed());

		// check box verification
		Assert.assertTrue(signuppage.subscribeCheckBox.isDisplayed());
		Assert.assertFalse(signuppage.subscribeCheckBox.isSelected());
		Assert.assertTrue(signuppage.subscribeToNewsLetter.isDisplayed());

		// verify back and signup buttons
		Assert.assertTrue(signuppage.SignUpBtn.isDisplayed());
		Assert.assertTrue(signuppage.backToLoginLink.isDisplayed());
	}

	@AfterMethod
	
	public void quitDriver() {
	Driver.getDriver().quit();
	}

}
