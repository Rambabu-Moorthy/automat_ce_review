package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.Select;

@WebServlet("/clearingReview")
public class SubmitServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {  
		
		String transactionNumber = req.getParameter("transactionNumber");
        String selectedRole = req.getParameter("role");
         
    /*  // Launching chrome and passing ce review system URL and Login
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://trpps.cbwmoney.com/CEREVIEW/#/login");
        driver.findElement(By.id("userName")).sendKeys(selectedRole);
        driver.findElement(By.id("password")).sendKeys(selectedRole);
        driver.findElement(By.xpath("//*[@id='loginForm']/button")).click(); */
           
        
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        WebDriver driver = new ChromeDriver(options);
        driver.get("https://trpps.cbwmoney.com/CEREVIEW/#/login");
        System.out.println("ce review application opened");
        driver.findElement(By.id("userName")).sendKeys(selectedRole);
        driver.findElement(By.id("password")).sendKeys(selectedRole);
        driver.findElement(By.xpath("//*[@id='loginForm']/button")).click();
        System.out.println("ce review login successfull");
        
        try {
        Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        //clicking the SEARCH FILTER in the home page of ce review system
        driver.findElement(By.xpath("/html/body/app/div/div/customertransaction-list/div[1]/ul/li[7]/a[2]")).click();
        System.out.println("SEARCH FILTER clicked");
        try {
        Thread.sleep(1000);
        } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    	} 
               
        // selecting the TRANSACTION NUMBER option from the drop down
        new Select(driver.findElement(By.xpath("//select[@name='serachBy']"))).selectByIndex(1);
        System.out.println("TRANSACTION NUMBER selected from SEARCH FILTER drop down");
            
        // entering TRANSACTION NUMBER into the TEXT BOX 
        driver.findElement(By.xpath("//*[@id='searchParameter']")).sendKeys(transactionNumber);
        System.out.println("TRANSACTION NUMBER entered into the SEARCH TEXTBOX");
            
        // click MAGNIFIER icon to search transaction
        driver.findElement(By.xpath("//*[@id='allsearchfilter']/fieldset/div[3]/button[1]/i")).click();
        System.out.println("MAGNIFIER ICON clicked");
            
        try {
        Thread.sleep(1000);
    	} catch (InterruptedException e) {
        // TODO Auto-generated catch block
    	e.printStackTrace();
    	} 
        
        // click on the REVIEW BUTTON
        driver.findElement(By.xpath("//*[@id='list-customerTransaction']/table/tbody/tr/td[6]/button")).click();
        System.out.println("REVIEW BUTTON clicked for the transaction");
            
        try {
    	Thread.sleep(1000);
    	} catch (InterruptedException e) {
    	// TODO Auto-generated catch block
    	e.printStackTrace();
    	} 
            
        //cilck on the MATCHED SCENARIOS checkbox
        try {
        driver.findElement(By.xpath("//*[@id='Matched-Scenarios']/fieldset/button/i")).click();
        System.out.println("MATCHED SCENARIOS checkbox clicked");
        } catch (NoSuchElementException e) {
            System.out.println("Matched Scenarios button not found. Skipping...");
        }
           
        try {
        //click on the MATCHED TOKENS checkbox
        driver.findElement(By.xpath("//*[@id='Matched-Tokens']/fieldset/button/i")).click();
        System.out.println("MATCHED TOKENS checkbox clicked");
        } catch (NoSuchElementException e) {
            System.out.println("Matched Tokens button not found. Skipping...");
        }

        try {
        //click on the MONITORED SIGNALS checkbox
        driver.findElement(By.xpath("//*[@id='Monitored-Signals']/fieldset/button")).click(); 
        System.out.println("MONITORED SIGNALS checkbox clicked");
        } catch (NoSuchElementException e) {
            System.out.println("MONITORED SIGNALS button not found. Skipping...");
        }
           
        try {
        //click on the PARTICIPANT SENDER checkbox
        WebElement participantSenderCheckbox = driver.findElement(By.xpath("//*[@id='Participants']/fieldset/div[1]/div/div[1]/button/i"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", participantSenderCheckbox);
        participantSenderCheckbox.click();
        System.out.println("PARTICIPANT SENDER checkbox clicked");
        } catch (NoSuchElementException e) {
            System.out.println("PARTICIPANT SENDER button not found. Skipping...");
        }
           
         try {
    	 Thread.sleep(2000);
    	 } catch (InterruptedException e) {
    	 // TODO Auto-generated catch block
    	 e.printStackTrace();
    	 } 
            
         try {
         //click on the PARTICIPANT RECEIVER checkbox
         WebElement participantReceiverCheckbox = driver.findElement(By.xpath("//*[@id='Participants']/fieldset/div[2]/div/div[1]/button"));
         ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", participantReceiverCheckbox);
         participantReceiverCheckbox.click(); 
         System.out.println("PARTICIPANT RECEIVER checkbox clicked");
         } catch (NoSuchElementException e) {
             System.out.println("PARTICIPANT RECEIVER button not found. Skipping...");
         }
            
         try {
         //click on the ROUTE STATISTICS checkbox
         WebElement routeStatisticsCheckbox = driver.findElement(By.xpath("//*[@id='Route-Statistics']/fieldset/div[1]/div[1]/h1/button/i"));
         ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", routeStatisticsCheckbox);
         routeStatisticsCheckbox.click();
         System.out.println("ROUTE STATISTICS checkbox clicked");
         } catch (NoSuchElementException e) {
             System.out.println("ROUTE STATISTICS button not found. Skipping...");
         }
         
          try {
    	  Thread.sleep(1000);
    	  } catch (InterruptedException e) {
    	  // TODO Auto-generated catch block
    	  e.printStackTrace();
    	  } 
            
          // scroll down until TRANSACTION PAYMENT PURPOSE section visible
          WebElement paymentPurposeDropdown = driver.findElement(By.xpath("//*[@id='paymentPurpose']"));
          ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", paymentPurposeDropdown); 
            
          //selecting value from PAYMENT PURPOSE dropdown FIRST OPTION
          new Select(paymentPurposeDropdown).selectByIndex(1);
          System.out.println("PAYMENT PURPOSE dropdown FIRST OPTION selected");
            
          try {
    	  Thread.sleep(1000);
    	  } catch (InterruptedException e) {
    	  // TODO Auto-generated catch block
    	  e.printStackTrace();
    	  }
            
          // click on the SENDER-RECEIVER RELATIONSHIP dropdown and select the first value
          new Select(driver.findElement(By.xpath("//*[@id='relationship']"))).selectByIndex(1);
          System.out.println("SENDER-RECEIVER RELATIONSHIP dropdown and select the first value selected");
          
          try {
    	  Thread.sleep(1000);
    	  } catch (InterruptedException e) {
    	  // TODO Auto-generated catch block
    	  e.printStackTrace();
    	  }
          
          //click on the PAYMENT PURPOSE SUBMIT button
          driver.findElement(By.xpath("//*[@id='Transaction-Purpose']/fieldset/button")).click();
          System.out.println("PAYMENT PURPOSE SUBMIT button clicked");
            
          try {
    	  Thread.sleep(1000);
    	  } catch (InterruptedException e) {
    	  // TODO Auto-generated catch block
    	  e.printStackTrace();
    	  }
            
          try {
          //click on the EVIDENCE checkbox
          driver.findElement(By.xpath("//*[@id='Evidence']/fieldset/button/i")).click(); 
          System.out.println("EVIDENCE checkbox clicked");
          } catch (NoSuchElementException e) {
              System.out.println("EVIDENCE checkbox button not found. Skipping...");
          }
            
          //click on the NO NEGATIVE REVIEW button
          driver.findElement(By.xpath("//*[@id='Negative-News-Observations']/fieldset/div[2]/button[1]")).click();
          System.out.println("NO NEGATIVE REVIEW button clicked");
            
          //scroll down until final SUBMIT button visible
          WebElement finalSubmit = driver.findElement(By.xpath("//*[@id='Decision']/fieldset/div[4]/button[3]"));
          ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", finalSubmit); 
          
          try {
    	  Thread.sleep(1000);
    	  } catch (InterruptedException e) {
    	  // TODO Auto-generated catch block
    	  e.printStackTrace();
    	  }
            
          //Click on the REASON CODES dropdown 
          driver.findElement(By.xpath("//*[@id='applicableUnblockingCodes']/div/div[1]/span")).click();
          System.out.println("REASON CODES dropdown clicked");
            
          // selectng the FIRST REASON CODE by clicking since it is a multi-select 
          WebElement noSuspious = driver.findElement(By.xpath("//*[@id='applicableUnblockingCodes']/div/div[2]/ul[2]/li[2]"));
          noSuspious.click();
          System.out.println("FIRST REASON CODE checked");
            
          // after selecting REASON CODES from the multi-select checkbox dropdown "Its a TAB out click"
          driver.findElement(By.xpath("//*[@id='applicableUnblockingCodes']/div/div[2]/ul[1]/li/input")).click();
          System.out.println("TAB OUT done"); 
          
          //selecting the SELECT DECISION dropdown
          new Select(driver.findElement(By.xpath("/html/body/app/div/div/customertransactionreview/div/div[15]/div/fieldset/div[2]/div/select"))).selectByIndex(1);
          System.out.println("SELECT DECISION dropdown clicked"); 
          
          // entering remarks for releasing the transaction
          driver.findElement(By.id("remarks")).sendKeys("No suspicious found - Release");
          System.out.println("remarks entered");
            
          // clicking SUBMIT button
          finalSubmit.click(); 
          System.out.println("final submit clicked");
          
          driver.quit();
          System.out.println("driver closed");
                  
          String successMessage = "Review Completed - Please check";
          PrintWriter out = resp.getWriter();
          out.print(successMessage);
          out.flush();
           
	}
}
