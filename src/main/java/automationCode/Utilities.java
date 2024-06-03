package automationCode;

import java.time.Duration;
import java.util.function.Function;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utilities {
	
	 public static WebElement waitForElement(WebDriver driver, By locator) {
	        FluentWait<WebDriver> wait = new FluentWait<>(driver)
	                .withTimeout(Duration.ofSeconds(30)) // Maximum wait time
	                .pollingEvery(Duration.ofSeconds(5)) // Check interval
	                .ignoring(NoSuchElementException.class);

	        return wait.until(new Function<WebDriver, WebElement>() {
	            public WebElement apply(WebDriver driver) {
	                return driver.findElement(locator);
	            }
	        });
	 }

	 public static void waitForElementToBeDisplayed(WebDriver driver, By locator, int timeoutInSeconds) {
	        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
	        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	    }
}



