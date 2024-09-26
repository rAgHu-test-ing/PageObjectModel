package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
	
	WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		
		this.driver = driver;
	}
	
	private WebElement emailField;
	private WebElement passwordField;
	private WebElement loginButton;
	private WebElement unsuccessfulLoginMessage;

	public void enterLoginEmailAddress(String emailText) {
		
		emailField = driver.findElement(By.id("input-email"));
		emailField.sendKeys(emailText);
		
	}
	
	public void enterLoginPassword(String password) {
		
		passwordField = driver.findElement(By.id("input-password"));
		passwordField.sendKeys(password);
	}
	
	public WebDriver clickLoginButton() {
		
		loginButton = driver.findElement(By.xpath("//input[@type='submit']"));
		loginButton.click();
		return driver;
	}
	
	public String actualWarningMessage() {
		
		unsuccessfulLoginMessage = driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']"));
		return unsuccessfulLoginMessage.getText();
	}

}
