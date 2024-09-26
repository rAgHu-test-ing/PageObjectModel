package tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageobjects.HomePage;
import pageobjects.LoginPage;
import pageobjects.MyAccountPage;

public class Login {
	
	WebDriver driver = null;
	Properties prop = null;
	HomePage homePage;
	LoginPage loginPage;
	MyAccountPage myAccountPage;
	
	@BeforeMethod
	public void setUp() throws FileNotFoundException, IOException {
		
		prop = new Properties();
		prop.load(new FileInputStream(new File("src/test/java/properties/ProjectData.properties")));
		
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(prop.getProperty("url"));
		
		homePage = new HomePage(driver);
		homePage.clickOnMyAccountDropdown();
		homePage.selectLogin();
	}
	
	@AfterMethod
	public void tearDown() {
		
		if(driver!=null) {
			driver.quit();
		}
	}
	
	@Test (priority=1)
	public void loginWithValidDetails() {
		
		loginPage.enterLoginEmailAddress(prop.getProperty("validemail"));
		loginPage.enterLoginPassword(prop.getProperty("validpassword"));
		
		loginPage.clickLoginButton();
		
		Assert.assertTrue(myAccountPage.loggedInStatus());
	}
	
	@Test (priority=2)
	public void loginWithInvalidDetails() {
		
		loginPage.enterLoginEmailAddress(prop.getProperty("invalidemail"));
		loginPage.enterLoginPassword(prop.getProperty("invalidpassword"));
		
		loginPage.clickLoginButton();
		
		String expectedWarning = "Warning: No match for E-Mail Address and/or Password.";
		String actualWarning = loginPage.actualWarningMessage();
		
		Assert.assertEquals(actualWarning, expectedWarning);
	}
	
	@Test(priority=3)
	public void loginWithInvalidEmailValidPassword() {
		
		loginPage.enterLoginEmailAddress(prop.getProperty("invalidemail"));
		loginPage.enterLoginPassword(prop.getProperty("validpassword"));
		
		loginPage.clickLoginButton();
		
		String expectedWarning = "Warning: No match for E-Mail Address and/or Password.";
		String actualWarning = loginPage.actualWarningMessage();
		
		Assert.assertEquals(actualWarning, expectedWarning);
	}
	
	@Test(priority=4)
	public void loginWithValidEmailInvalidPassword() {
		
		loginPage.enterLoginEmailAddress(prop.getProperty("validemail"));
		loginPage.enterLoginPassword(prop.getProperty("invalidpassword"));
		
		loginPage.clickLoginButton();
		
		String expectedWarning = "Warning: No match for E-Mail Address and/or Password.";
		String actualWarning = loginPage.actualWarningMessage();
		
		Assert.assertEquals(actualWarning, expectedWarning);
	}
	
	@Test(priority=5)
	public void loginWithNoDetails() {
		
		loginPage = new LoginPage(driver);
		loginPage.clickLoginButton();
		
		String expectedWarning = "Warning: No match for E-Mail Address and/or Password.";
		String actualWarning = loginPage.actualWarningMessage();
		
		Assert.assertEquals(actualWarning, expectedWarning);
		
	}

}
