package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
	
	WebDriver driver;
	
	public HomePage(WebDriver driver) {
		
		this.driver = driver;
	}
	
	private WebElement myAccountDropdown;
	private WebElement registerOption;
	private WebElement loginOption;
	private WebElement searchOption;
	private WebElement searchButton;
	
	public void clickOnMyAccountDropdown() {
		
		myAccountDropdown = driver.findElement(By.xpath("//span[text()='My Account']"));
		myAccountDropdown.click();
	}
	
	public void registerOption() {
		
		registerOption = driver.findElement(By.linkText("Register"));
		registerOption.click();
	}
	
	public LoginPage selectLogin() {
		
		loginOption = driver.findElement(By.linkText("Login"));
		loginOption.click();
		return new LoginPage(driver);
	}
	
	public WebElement searchField() {
		
		searchOption = driver.findElement(By.name("search"));
		return searchOption;
	}
	
	public WebDriver searchButton() {
		
		searchButton = driver.findElement(By.xpath("//button[@class='btn btn-default btn-lg']"));
		searchButton.click();
		return driver;
	}
	
}
