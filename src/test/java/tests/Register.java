package tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageobjects.HomePage;
import pageobjects.RegisterPage;

public class Register {
	
	WebDriver driver = null;
	Properties prop = null;
	HomePage homePage;
	RegisterPage registerPage;

	@BeforeMethod
	public void setUp() throws FileNotFoundException, IOException {
		
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		prop = new Properties();
		prop.load(new FileInputStream(new File("src/test/java/properties/ProjectData.properties")));
		
		driver.get(prop.getProperty("url"));
		
		homePage = new HomePage(driver);
		homePage.clickOnMyAccountDropdown();
		homePage.registerOption();
	}
	
	@AfterMethod
	public void tearDown() {
		
		if(driver!=null) {
			
			driver.quit();
		}
	}
	
	@Test(priority=1)
	public void registerWithAllFields() {
		
		registerPage = new RegisterPage(driver);
		
		registerPage.firstName().sendKeys(prop.getProperty("firstname"));
		registerPage.lastName().sendKeys(prop.getProperty("lastname"));
		registerPage.email().sendKeys(generateNewEmail());
		registerPage.telephoneNo().sendKeys(prop.getProperty("telephone"));
		registerPage.password().sendKeys(prop.getProperty("password"));
		registerPage.confirmPassword().sendKeys(prop.getProperty("confirmpassword"));
		registerPage.subscribe();
		registerPage.privacy();
		registerPage.clickContinue();
		
		String expectedTitle = "Your Account Has Been Created!";
		String actualTitle = driver.getTitle();
		Assert.assertEquals(actualTitle, expectedTitle, "Account Creation Failed");
	}
	
	@Test(priority=2)
	public void registerWithNoFields() {
		
		registerPage = new RegisterPage(driver);
		registerPage.privacy();
		registerPage.clickContinue();
		
		String actualFirstNameError = registerPage.firstNameErrorMessage();
		String expectedFirstNameError = "First Name must be between 1 and 32 characters!";
		
		Assert.assertEquals(actualFirstNameError, expectedFirstNameError);
		
		String actualLastNameError = registerPage.lastNameErrorMessage();
		String expectedLastNameError = "Last Name must be between 1 and 32 characters!";
		
		Assert.assertEquals(actualLastNameError, expectedLastNameError);
	}
	
	public String generateNewEmail() {
		
		Date date = new Date();
		return date.toString().replace(" ","_").replace(":","_").concat("@gmail.com");
	}
}
