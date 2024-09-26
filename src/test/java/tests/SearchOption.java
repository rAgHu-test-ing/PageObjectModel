package tests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageobjects.HomePage;
import pageobjects.SearchResultsPage;

public class SearchOption {
	
	WebDriver driver = null;
	Properties prop = null;
	HomePage homePage;
	SearchResultsPage searchResultsPage;
	
	@BeforeMethod
	public void setUp() throws FileNotFoundException, IOException {
		
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		prop = new Properties();
		prop.load(new FileInputStream(new File("src/test/java/properties/ProjectData.properties")));
		
		driver.get(prop.getProperty("url"));
		
	}
	
	@AfterMethod
	public void tearDown() {
		
		if(driver!=null) {
			
			driver.quit();
		}
	}
	@Test (priority=1)
	public void searchExistingProduct() {
		
		homePage = new HomePage(driver);
		homePage.searchField().sendKeys(prop.getProperty("validproduct"));
		homePage.searchButton();
		
		searchResultsPage = new SearchResultsPage(driver);
		
		Assert.assertTrue(searchResultsPage.searchResultText());
	}
	
	@Test(priority=2)
	public void searchInvalidProduct() {
		
		homePage = new HomePage(driver);
		homePage.searchField().sendKeys(prop.getProperty("invalidproduct"));
		homePage.searchButton();
		
		searchResultsPage = new SearchResultsPage(driver);
		String actualErrorText = searchResultsPage.searchResultTextForEmptySearch();
		String expectedErrorText = "There is no product that matches the search criteria.";
		
		Assert.assertEquals(actualErrorText, expectedErrorText);
	}
	@Test(priority=3)
	public void clickingSearchButtonWithOutText() {
		
		homePage = new HomePage(driver);
		homePage.searchButton();
		
		searchResultsPage = new SearchResultsPage(driver);
		String actualErrorText = searchResultsPage.searchResultTextForEmptySearch();
		String expectedErrorText = "There is no product that matches the search criteria.";
		
		Assert.assertEquals(actualErrorText, expectedErrorText);
	}

}
