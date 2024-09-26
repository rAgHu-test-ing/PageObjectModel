package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchResultsPage {
	
	WebDriver driver;
	
	public SearchResultsPage(WebDriver driver) {
		
		this.driver=driver;
	}

	public Boolean searchResultText() {
		
		Boolean searchResultText = driver.findElement(By.linkText("HP LP3065")).isDisplayed();
		return searchResultText;
	}
	
	public String searchResultTextForEmptySearch() {
		
		String resultErrorText = driver.findElement(By.xpath("//p[text()='There is no product that matches the search criteria.']")).getText();
		return resultErrorText;
	}

}
