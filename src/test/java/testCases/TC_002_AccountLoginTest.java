package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC_002_AccountLoginTest extends BaseClass{
	
	@Test(groups={"Sanity","Master"})
	public void login() {
		
		logger.info("**** Starting TC_002_AccountLoginTest ****");
		
		LoginPage loginpage = new LoginPage(driver);
		HomePage homepage = new HomePage(driver);
		MyAccountPage myaccountpage = new MyAccountPage(driver);
		try {
		homepage.clickMyAccount();
		homepage.clickLogin();
		
		logger.info("Entered email and password");
		
		loginpage.setEmail(rb.getString("email"));
		loginpage.setPassword(rb.getString("password"));
		loginpage.clickLogin();
		
		boolean status = myaccountpage.isMyAccountPageExists();
		Assert.assertEquals(status, true);
		}
		catch(Exception e) {
			logger.error("**** Test Failed ****");
			Assert.fail();
		}
		
		logger.info("**** TC_002 Test Passed ****");
		
		
	}
	

}
