
package tests;

import org.testng.annotations.Test;
import pages.caterDashboardPage;
import pages.craterLoginPage;
import utils.BrowserUtils;
import utils.Driver;
import utils.TestDataReader;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;

public class craterUserManagement {

	BrowserUtils utils;

	@Test
	public void validLogin() throws InterruptedException {

		Driver.getDriver().get(TestDataReader.getProperty("craterUrl"));
		craterLoginPage loginPage = new craterLoginPage();
		loginPage.login();

//	  Thread.sleep(1000);
//	  utils = new BrowserUtils(); 
//	  utils.sendKeyswithActionsClass(loginPage.useremail, TestDataReader.getProperty("craterVaildEmail"));
//	 // loginPage.useremail.sendKeys(TestDataReader.getProperty("craterVaildEmail"));
//	  Thread.sleep(1000);
//	  utils.sendKeyswithActionsClass(loginPage.password, TestDataReader.getProperty("craterValidPassword"));
//	 // loginPage.password.sendKeys(TestDataReader.getProperty("craterValidPassword"));
//	  Thread.sleep(1000);
//	  loginPage.loginButton.click();
//	  

		// Verify the amount due element display
		caterDashboardPage dashboardPage = new caterDashboardPage();
		Assert.assertTrue(dashboardPage.amountDueText.isDisplayed());

		// Verify the url contains dashboard
		String dashboardUrl = Driver.getDriver().getCurrentUrl();
		Assert.assertTrue(dashboardUrl.contains("dashboard"));

	}

	@Test(dataProvider = "credential")
	public void invalidLogin(String useremail, String password) throws InterruptedException {

		Driver.getDriver().get(TestDataReader.getProperty("craterUrl"));
		craterLoginPage loginPage = new craterLoginPage();
		Thread.sleep(1000);
		BrowserUtils utils = new BrowserUtils();

		if (useremail.isBlank() || password.isBlank()) {
			loginPage.useremail.sendKeys(useremail);
			loginPage.password.sendKeys(password);
			loginPage.loginButton.click();

			// we need to wait until the text be visibile
			// This is why we used the object wait from browser utils
			utils.waitUntilElementVisible(loginPage.fieldRequired);
			Assert.assertTrue(loginPage.fieldRequired.isDisplayed());
		} else
			loginPage.useremail.sendKeys(useremail);
		loginPage.password.sendKeys(password);
		loginPage.loginButton.click();
		utils.waitUntilElementVisible(loginPage.invalidUserErrorMessage);
		Assert.assertTrue(loginPage.invalidUserErrorMessage.isDisplayed());

		// checking we are still at the login page
		Assert.assertTrue(loginPage.loginButton.isDisplayed());
	}

	@DataProvider
	public String[][] credential() {
		String[][] names = new String[4][2];
		names[0][0] = "hussamsalman79@yahoo.com";
		names[0][1] = "invalidpassword";

		names[1][0] = "invalidemail@yahoo.com";
		names[1][1] = "validpassword";

		names[2][0] = "";
		names[2][1] = "validpassword";

		names[3][0] = "invalidemail@yahoo.com";
		names[3][1] = "";

		return names;
	}

	@BeforeMethod
	public void setUp() {

		Driver.getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

	}

	@AfterMethod
	public void tearDown() {

		Driver.quitDriver();

	}

}
