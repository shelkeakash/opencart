package testCases;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC_001_AccountRegistrationTest extends BaseClass {
	
	@Test(groups={"Regression","Master"})
	void test_account_registration() {
		logger.debug("***** Application logs..........");
		logger.info("**** Starting TC_001_AccountRegistrationTest ****");
		
		try {
		HomePage hp = new HomePage(driver);
		AccountRegistrationPage ar = new AccountRegistrationPage(driver);
		
		hp.clickMyAccount();
		logger.info("Clicked on My Account link");
		hp.clickRegister();
		logger.info("Clicked on My Register link");
		logger.info("Providing customer data");

		ar.setFirstName(randomString().toUpperCase());
		ar.setLastName(randomString().toUpperCase());
		ar.setEmail(randomString()+"@gmail.com");
		ar.setPassword("test");
		
		WebElement agree = driver.findElement(By.xpath("//input[@name='agree']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", agree);
		Thread.sleep(500);
		
		ar.clickAgreeCheckbox();
		ar.clickContinue();
		logger.info("Verifying expected message");

		String msg = ar.getConfirmationMessage();
		Assert.assertEquals(msg, "Your Account Has Been Created!","Not getting expected message");
		}
		catch(Exception e) {
			logger.error("Registration failed");

			Assert.fail();
		}
		logger.info("TC_001 Test case passed");
	}
	


}
