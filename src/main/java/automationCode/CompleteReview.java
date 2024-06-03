package automationCode;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.openqa.selenium.JavascriptExecutor;
import io.github.bonigarcia.wdm.WebDriverManager;

public class CompleteReview {
	
	private WebDriver driver;

	 @BeforeClass
	    public void setUp() {
	        WebDriverManager.chromedriver().setup();
	        ChromeOptions options = new ChromeOptions();
	       // options.addArguments("--headless");
	       // driver = new ChromeDriver(options);
	        driver = new ChromeDriver();
	        driver.manage().window().maximize();
	        System.out.println("chrome launched successfully");
	        driver.get("https://trpps.cbwmoney.com/CEREVIEW/#/login");
	        System.out.println("CE REVIEW launched successfully");
	    }
	 
	 @Parameters({"action", "transactionNumber"})
	    @Test
	    public void performAction(String action, String transactionNumber) throws Exception {
	        performActionBasedOnParameters(action, transactionNumber);
	    }
	 
	 private void loginL1() {
	        driver.findElement(By.id("userName")).sendKeys("l1");
	        driver.findElement(By.id("password")).sendKeys("l1");
	        driver.findElement(By.xpath("//*[@id='loginForm']/button")).click();
	        System.out.println("Login successful by L1");
	    }
	 
	 private void searchFilterClickL1() throws InterruptedException {
		    Thread.sleep(2000);
     	    driver.findElement(By.xpath("/html/body/app/div/div/customertransaction-list/div[1]/ul/li[7]/a[2]")).click();
	        System.out.println("SEARCH FILTER clicked by l1 user");
	 }
	 
	 private void searchByTranNum(String transactionNumber) {
		 new Select(driver.findElement(By.xpath("//select[@name='serachBy']"))).selectByIndex(1);
	        System.out.println("TRANSACTION NUMBER option selected from SEARCH FILTER drop down");
	        driver.findElement(By.xpath("//*[@id='searchParameter']")).sendKeys(transactionNumber);
	        System.out.println("TRANSACTION NUMBER entered into the SEARCH TEXTBOX");
	        
	       //Thread.sleep(1000);
	       driver.findElement(By.xpath("//*[@id='allsearchfilter']/fieldset/div[3]/button[1]/i")).click();
           System.out.println("MAGNIFIER ICON clicked");
	 }
	 
	 private void checkUntilMonitoredSignal() throws InterruptedException {
		 Thread.sleep(1000);
	     driver.findElement(By.xpath("//*[@id='list-customerTransaction']/table/tbody/tr/td[6]/button")).click();
         System.out.println("REVIEW BUTTON clicked to start review for the transaction"); 
        
         Thread.sleep(1000);
       //cilck on the MATCHED SCENARIOS checkbox, if already clicked then skipping
	        try {
	        driver.findElement(By.xpath("//*[@id='Matched-Scenarios']/fieldset/button/i")).click();
	        System.out.println("MATCHED SCENARIOS checkbox clicked");
	        } catch (NoSuchElementException e) {
	            System.out.println("Matched Scenarios button not found. Skipping...");
	        } 
	        
	        try {
		        //click on the MATCHED TOKENS checkbox,  if already clicked then skipping
		        driver.findElement(By.xpath("//*[@id='Matched-Tokens']/fieldset/button/i")).click();
		        System.out.println("MATCHED TOKENS checkbox clicked");
		        } catch (NoSuchElementException e) {
		            System.out.println("Matched Tokens button not found. Skipping...");
		        }
	        
	        try {
		        //click on the MONITORED SIGNALS checkbox, if already clicked then skipping
		        driver.findElement(By.xpath("//*[@id='Monitored-Signals']/fieldset/button")).click(); 
		        System.out.println("MONITORED SIGNALS checkbox clicked");
		        } catch (NoSuchElementException e) {
		            System.out.println("MONITORED SIGNALS button not found. Skipping...");
		        }
	 }
	 
	 private void checkParticipants() throws Exception {
		 try {
		        //click on the PARTICIPANT SENDER checkbox,if already clicked then skipping , scrooling the scroll bar and clicking
		        WebElement participantSenderCheckbox = driver.findElement(By.xpath("//*[@id='Participants']/fieldset/div[1]/div/div[1]/button/i"));
		        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", participantSenderCheckbox);
		        participantSenderCheckbox.click();
		        System.out.println("PARTICIPANT SENDER checkbox clicked");
		        } catch (NoSuchElementException e) {
		            System.out.println("PARTICIPANT SENDER button not found. Skipping...");
		        } 
		 
		 try {
	         //click on the PARTICIPANT RECEIVER checkbox, if already clicked then skipping
	         WebElement participantReceiverCheckbox = driver.findElement(By.xpath("//*[@id='Participants']/fieldset/div[2]/div/div[1]/button"));
	         ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", participantReceiverCheckbox);
	         participantReceiverCheckbox.click(); 
	         System.out.println("PARTICIPANT RECEIVER checkbox clicked");
	         } catch (NoSuchElementException e) {
	             System.out.println("PARTICIPANT RECEIVER button not found. Skipping...");
	         }
	            
	         try {
	         //click on the ROUTE STATISTICS checkbox, if already clicked then skipping
	         WebElement routeStatisticsCheckbox = driver.findElement(By.xpath("//*[@id='Route-Statistics']/fieldset/div[1]/div[1]/h1/button/i"));
	         ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", routeStatisticsCheckbox);
	         routeStatisticsCheckbox.click();
	         System.out.println("ROUTE STATISTICS checkbox clicked");
	         } catch (NoSuchElementException e) {
	             System.out.println("ROUTE STATISTICS button not found. Skipping...");
	         }
	 }
	 
	 private void tranPurposeEvidence() throws InterruptedException {
		 Thread.sleep(2000);
		// scroll down until TRANSACTION PAYMENT PURPOSE section visible
         WebElement paymentPurposeDropdown = driver.findElement(By.xpath("//*[@id='paymentPurpose']"));
         ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", paymentPurposeDropdown); 
           
         //selecting value from PAYMENT PURPOSE dropdown FIRST OPTION
         new Select(paymentPurposeDropdown).selectByIndex(1);
         System.out.println("PAYMENT PURPOSE dropdown FIRST OPTION selected");
         
         Thread.sleep(2000);
         // click on the SENDER-RECEIVER RELATIONSHIP dropdown and select the first value
         new Select(driver.findElement(By.xpath("//*[@id='relationship']"))).selectByIndex(1);
         System.out.println("SENDER-RECEIVER RELATIONSHIP dropdown and select the first value selected");
         
         
   	     Thread.sleep(1000); 
         //click on the PAYMENT PURPOSE SUBMIT button
         driver.findElement(By.xpath("//*[@id='Transaction-Purpose']/fieldset/button")).click();
         System.out.println("PAYMENT PURPOSE SUBMIT button clicked");
         
         Thread.sleep(1000);
         try {
	          //click on the EVIDENCE checkbox,if already clicked then skipping
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
	 }
	 
	 private void Decision() throws InterruptedException {
		 //scroll down until final SUBMIT button visible
         WebElement finalSubmit = driver.findElement(By.xpath("//*[@id='Decision']/fieldset/div[4]/button[3]"));
         ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", finalSubmit); 
         
   	    Thread.sleep(1000);
   	 
         // if l1 user reviewing the transaction then reason code selection, decision selection & remarks entering will happen
         // else l2 user reviewing the transaction then only remarks entering will happen
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
	 }
	 
	 
	 private void loginL2() {
	        driver.findElement(By.id("userName")).sendKeys("l2");
	        driver.findElement(By.id("password")).sendKeys("l2");
	        driver.findElement(By.xpath("//*[@id='loginForm']/button")).click();
	        System.out.println("Login successful by L2");
	    }
	 
	  private void performActionBasedOnParameters(String action, String transactionNumber) throws Exception {
	        switch (action) {
	            case "unblock_release_by_l1":
	                // Perform unblock_release_by_l1 action
	            	loginL1();
	            	searchFilterClickL1();
	            	searchByTranNum(transactionNumber);
	            	checkUntilMonitoredSignal();
	            	checkParticipants();
	            	tranPurposeEvidence();
	            	Decision();
	            	
	                break;
	            case "unblock_release_with_review_no_case_details_by_l1_l2":
	                // Perform unblock_release_with_review_no_case_details_by_l1_l2 action
	                System.out.println("Performing action: " + action);
	                break;
	            case "unblock_release_with_review_add_case_details_by_l1_l2":
	                // Perform unblock_release_with_review_add_case_details_by_l1_l2 action
	                System.out.println("Performing action: " + action);
	                break;
	            case "hold_by_l1":
	                // Perform hold_by_l1 action
	                System.out.println("Performing action: " + action);
	                break;
	            case "block_with_case_details_by_l1":
	                // Perform block_with_case_details_by_l1 action
	                System.out.println("Performing action: " + action);
	                break;
	            case "block_without_case_details_by_l1":
	                // Perform block_without_case_details_by_l1 action
	                System.out.println("Performing action: " + action);
	                break;
	            case "block_with_review_no_case_details_l1_l2":
	                // Perform block_with_review_no_case_details_l1_l2 action
	                System.out.println("Performing action: " + action);
	                break;
	            case "block_with_review_with_case_details_l1_l2":
	                // Perform block_with_review_with_case_details_l1_l2 action
	                System.out.println("Performing action: " + action);
	                break;
	            default:
	                System.out.println("Unknown action: " + action);
	                break;
	        }
	    }
	 
	 
	 
	 @AfterClass
	    public void tearDown() {
	        if (driver != null) {
	            driver.quit();
	        }
	    }


	 
/*	 @Test
	 public void launchReviewSystem() {
		    WebDriverManager.chromedriver().setup();
	        ChromeOptions options = new ChromeOptions();
	        options.addArguments("--headless");
	        WebDriver driver = new ChromeDriver(options);
	        driver.manage().window().maximize();
	        System.out.println("chrome launched successful");
	        driver.get("https://trpps.cbwmoney.com/CEREVIEW/#/login");
	 }
	 
	 @Test(dependsOnMethods = "launchReviewSystem")
	 public void login(String selectedRole) {
		 if(selectedRole.equals("unblock_release_by_l1")) {
			 driver.findElement(By.id("userName")).sendKeys("l1");
			 driver.findElement(By.id("password")).sendKeys("l1");
			
			 driver.findElement(By.xpath("//*[@id='loginForm']/button")).click();
			 System.out.println("l1 loginn successfull successful");
		        System.out.println("ce review login successfull");
		 }
	 }*/	 
	 
/*	 public void automateReview(String transactionNumber, String selectedRole) {
		 

	        WebDriverManager.chromedriver().setup();
	        ChromeOptions options = new ChromeOptions();
	        options.addArguments("--headless");
	        WebDriver driver = new ChromeDriver(options);
//	        WebDriver driver = new ChromeDriver();
	        driver.manage().window().maximize();
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
	        
	        //if role l1 then clicking search filter or l2 then clicking the same search filter but there is change in the web element that can viewed by l1 or l2 user
	        if(selectedRole.equals("unblock_release_by_l1")) {
	        driver.findElement(By.xpath("/html/body/app/div/div/customertransaction-list/div[1]/ul/li[7]/a[2]")).click();
	        System.out.println("SEARCH FILTER clicked by l1 user");
	        } 
	        else
	        {
	        	driver.findElement(By.xpath("/html/body/app/div/div/customertransaction-list/div[1]/ul/li[8]/a[2]")).click();
	            System.out.println("SEARCH FILTER clicked by l2 user");
	        }                                                                                                                     
	        
	        // for l1 there is seperate web element for SERCH FILTER click in the dom
	        // for l2 there is seperate web element for SERCH FILTER click in the dom so used if else condition
	        // used By locator & Fluent Waits to reduce the execution time
	        // Fluent wait method written in the Utilities class as a static method
	/*        if(selectedRole.equals("unblock_release_by_l1")) {
	          By searchFilterInDomL1 = By.xpath("/html/body/app/div/div/customertransaction-list/div[1]/ul/li[7]/a[2]");
		      WebElement foundL1SerachFilter = Utilities.waitForElement(driver, searchFilterInDomL1);
		      foundL1SerachFilter.click();
		      System.out.println("SEARCH FILTER clicked by l1 user");
		        } 
		        else
		        {
		        	 By searchFilterInDomL2 = By.xpath("/html/body/app/div/div/customertransaction-list/div[1]/ul/li[8]/a[2]");
		        	 WebElement foundL2SerachFilter = Utilities.waitForElement(driver, searchFilterInDomL2);
		        	 foundL2SerachFilter.click();
				     System.out.println("SEARCH FILTER clicked by l2 user");
		        }  */
	        
	       
	        // selecting the TRANSACTION NUMBER option from the drop down
/*	        new Select(driver.findElement(By.xpath("//select[@name='serachBy']"))).selectByIndex(1);
	        System.out.println("TRANSACTION NUMBER selected from SEARCH FILTER drop down");                              
	        
	        
	        // if driver unable to find the Transaction number option from the drop down then Wait for some seconds using Fluent Wait code below
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
	        // tried fluent wait and explicit wait but still STALE ELEMENT EXCEPTION occurs so i put thread.sleep above
	        driver.findElement(By.xpath("//*[@id='list-customerTransaction']/table/tbody/tr/td[6]/button")).click();
	        System.out.println("REVIEW BUTTON clicked to start review for the transaction"); 
	        
	          
	        try {
	    	Thread.sleep(1000);
	    	} catch (InterruptedException e) {
	    	// TODO Auto-generated catch block
	    	e.printStackTrace();
	    	}                                       
	            
	        //cilck on the MATCHED SCENARIOS checkbox, if already clicked then skipping
	        try {
	        driver.findElement(By.xpath("//*[@id='Matched-Scenarios']/fieldset/button/i")).click();
	        System.out.println("MATCHED SCENARIOS checkbox clicked");
	        } catch (NoSuchElementException e) {
	            System.out.println("Matched Scenarios button not found. Skipping...");
	        } 
	        
	           
	        try {
	        //click on the MATCHED TOKENS checkbox,  if already clicked then skipping
	        driver.findElement(By.xpath("//*[@id='Matched-Tokens']/fieldset/button/i")).click();
	        System.out.println("MATCHED TOKENS checkbox clicked");
	        } catch (NoSuchElementException e) {
	            System.out.println("Matched Tokens button not found. Skipping...");
	        }

	        try {
	        //click on the MONITORED SIGNALS checkbox, if already clicked then skipping
	        driver.findElement(By.xpath("//*[@id='Monitored-Signals']/fieldset/button")).click(); 
	        System.out.println("MONITORED SIGNALS checkbox clicked");
	        } catch (NoSuchElementException e) {
	            System.out.println("MONITORED SIGNALS button not found. Skipping...");
	        }
	           
	        try {
	        //click on the PARTICIPANT SENDER checkbox,if already clicked then skipping , scrooling the scroll bar and clicking
	        WebElement participantSenderCheckbox = driver.findElement(By.xpath("//*[@id='Participants']/fieldset/div[1]/div/div[1]/button/i"));
	        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", participantSenderCheckbox);
	        participantSenderCheckbox.click();
	        System.out.println("PARTICIPANT SENDER checkbox clicked");
	        } catch (NoSuchElementException e) {
	            System.out.println("PARTICIPANT SENDER button not found. Skipping...");
	        } 
	           
	  /*     try {
	    	 Thread.sleep(2000);
	    	 } catch (InterruptedException e) {
	    	 // TODO Auto-generated catch block
	    	 e.printStackTrace();
	    	 } */
	            
/*	         try {
	         //click on the PARTICIPANT RECEIVER checkbox, if already clicked then skipping
	         WebElement participantReceiverCheckbox = driver.findElement(By.xpath("//*[@id='Participants']/fieldset/div[2]/div/div[1]/button"));
	         ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", participantReceiverCheckbox);
	         participantReceiverCheckbox.click(); 
	         System.out.println("PARTICIPANT RECEIVER checkbox clicked");
	         } catch (NoSuchElementException e) {
	             System.out.println("PARTICIPANT RECEIVER button not found. Skipping...");
	         }
	            
	         try {
	         //click on the ROUTE STATISTICS checkbox, if already clicked then skipping
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
	          //click on the EVIDENCE checkbox,if already clicked then skipping
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
	          
	          // if l1 user reviewing the transaction then reason code selection, decision selection & remarks entering will happen
	          // else l2 user reviewing the transaction then only remarks entering will happen
	          if(selectedRole.equals("l1")) {
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
	          System.out.println("remarks entered"); } else {
	        	  driver.findElement(By.xpath("//*[@id='Decision']/fieldset/div[3]/div[2]/textarea")).sendKeys("Released with Reiew");
	        	  System.out.println("remarks added by l2 user");
	          }
	            
	          // clicking SUBMIT button
	          finalSubmit.click(); 
	          System.out.println("final submit clicked");    
	          
	          driver.quit();
	          System.out.println("driver closed");
	 }                 */
}
