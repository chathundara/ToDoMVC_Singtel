package utilities;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitHelper {
	public WebDriver driver;
	
	public WaitHelper(WebDriver driver) {
		this.driver=driver;
	}
	
	
	public WebElement waitForElement(WebElement element, long timeOutSeconds) {
		WebElement firstResult= new WebDriverWait
				(driver, Duration.ofSeconds(timeOutSeconds)).
		until(ExpectedConditions.elementToBeClickable(element));
		return firstResult;
	}
	
}


