package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RegisterPage {
	
	WebDriver driver;
	
	public RegisterPage(WebDriver driver) {
		
		this.driver = driver;
	}
	
	private WebElement firstNameField;
	private WebElement lastNameField;
	private WebElement emailField;
	private WebElement telephoneField;
	private WebElement passwordField;
	private WebElement confirmPasswordField;
	private WebElement subscribeOption;
	private WebElement privacyOption;
	private WebElement continueOption;
	
	public WebElement firstName() {
		
		firstNameField = driver.findElement(By.id("input-firstname"));
		return firstNameField;
	}
	
	public WebElement lastName() {
		
		lastNameField = driver.findElement(By.id("input-lastname"));
		return lastNameField;
	}
	
	public WebElement email() {
		
		emailField = driver.findElement(By.id("input-email"));
		return emailField;
	}
	
	public WebElement telephoneNo() {
		
		telephoneField = driver.findElement(By.id("input-telephone"));
		return telephoneField;
	}
	
	public WebElement password() {
		
		passwordField = driver.findElement(By.id("input-password"));
		return passwordField;
	}
	
	public WebElement confirmPassword() {
		
		confirmPasswordField = driver.findElement(By.id("input-confirm"));
		return confirmPasswordField;
	}
	
	public void subscribe() {
		
		subscribeOption = driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']"));
		subscribeOption.click();
	}
	public void privacy() {
		
		privacyOption = driver.findElement(By.xpath("//input[@name='agree'][@value='1']"));
		privacyOption.click();
	}
	public void clickContinue() {
		
		continueOption = driver.findElement(By.xpath("//input[@class='btn btn-primary']"));
		continueOption.click();
	}
	
	public String firstNameErrorMessage() {
		
		String firstNameErrorMessage = driver.findElement(By.xpath("//input[@id='input-firstname']/following-sibling::div")).getText();
		
		return firstNameErrorMessage;
	}
	
	public String lastNameErrorMessage() {
		
		String lastNameErrorMessage = driver.findElement(By.xpath("//input[@id='input-lastname']/following-sibling::div")).getText();
		return lastNameErrorMessage;
	}
}
