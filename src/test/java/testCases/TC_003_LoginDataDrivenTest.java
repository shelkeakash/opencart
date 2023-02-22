package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC_003_LoginDataDrivenTest extends BaseClass {
	
	@Test(dataProvider="LoginData",dataProviderClass=DataProviders.class)
	public void testLogin(String email, String password, String exp) {
		logger.info("**** Starting TC_003_LoginDataDrivenTest ****");
		
		LoginPage loginpage = new LoginPage(driver);
		HomePage homepage = new HomePage(driver);
		MyAccountPage myaccountpage = new MyAccountPage(driver);
		try {
		homepage.clickMyAccount();
		homepage.clickLogin();
		
		logger.info("Entered email and password");
		
		loginpage.setEmail(email);
		loginpage.setPassword(password);
		loginpage.clickLogin();
		
		boolean status = myaccountpage.isMyAccountPageExists();
		
		if(exp.equals("Valid")) {
			if(status==true) {
				myaccountpage.clickMyAccount();
				myaccountpage.clickLogout();
				Assert.assertTrue(true);
			}
			else {
				Assert.assertTrue(false);
			}
		}
		
		if(exp.equals("Invalid")) {
			if(status==true) {
				myaccountpage.clickMyAccount();
				myaccountpage.clickLogout();
				Assert.assertTrue(false);
			}
			else {
				Assert.assertTrue(true);
			}
		}
		}
		catch(Exception e) {
			Assert.fail();
		}
		
		logger.info("**** TC_003 Test Passed ****");
	}
	
	

}
