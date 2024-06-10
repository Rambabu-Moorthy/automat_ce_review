package automationCode;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.openqa.selenium.JavascriptExecutor;
import io.github.bonigarcia.wdm.WebDriverManager;

public class CompleteReview {
	
	private WebDriver driver;
	 
	 public void launchChromePassUrl() throws InterruptedException {
//          Thread.sleep(2000);
		    WebDriverManager.chromedriver().setup();
	        ChromeOptions options = new ChromeOptions();
	        options.addArguments("--headless");
	        driver = new ChromeDriver(options);
//	        driver = new ChromeDriver();
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
	 
	 //Web element-searchFilter changes for l1 and l2 user so two different methods
	 private void searchFilterClickL1() throws InterruptedException {
		    Thread.sleep(2000);
		    //l1 user searchFilter
     	    driver.findElement(By.xpath("/html/body/app/div/div/customertransaction-list/div[1]/ul/li[7]/a[2]")).click();
	        System.out.println("SEARCH FILTER clicked by l1 user");
	 }
	 
	 private void searchFilterClickL2() throws InterruptedException {
		    Thread.sleep(2000);
		  //l2 user searchFilter
  	        driver.findElement(By.xpath("/html/body/app/div/div/customertransaction-list/div[1]/ul/li[8]/a[2]")).click();
	        System.out.println("SEARCH FILTER clicked by l1 user");
	 }
	 
	 
	 private void searchByTranNum(String transactionNumber) {
		    new Select(driver.findElement(By.xpath("//select[@name='serachBy']"))).selectByIndex(1);
	        System.out.println("TRANSACTION NUMBER option selected from SEARCH FILTER drop down");
	        
	        driver.findElement(By.xpath("//*[@id='searchParameter']")).sendKeys(transactionNumber);
	        System.out.println("TRANSACTION NUMBER entered into the SEARCH TEXTBOX");
	        
	        driver.findElement(By.xpath("//*[@id='allsearchfilter']/fieldset/div[3]/button[1]/i")).click();
            System.out.println("MAGNIFIER ICON clicked");
	 }
	 
	 private void reviewButtonClicked() throws InterruptedException {
		 Thread.sleep(1000);
	     driver.findElement(By.xpath("//*[@id='list-customerTransaction']/table/tbody/tr/td[6]/button")).click();
         System.out.println("REVIEW BUTTON clicked to start review for the transaction"); 
	 }
	 
	 private void checkUntilMonitoredSignal() throws InterruptedException {
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
         System.out.println("scroll down untl final submit button visible");
	 }
	 
	 private void decisionBlock(String action) throws InterruptedException {
		 Thread.sleep(1000);
		 //scroll down until final SUBMIT button visible
		 WebElement finalSubmit = driver.findElement(By.xpath("//*[@id='Decision']/fieldset/div[4]/button[3]"));
         //((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", finalSubmit);
         
         //Click on the REASON CODES dropdown 
         driver.findElement(By.xpath("//*[@id='applicableUnblockingCodes']/div/div[1]/span")).click();
         System.out.println("REASON CODES dropdown clicked");
         
         Thread.sleep(1000);
      // selectng the last reason code by clicking since it is a multi-select 
         WebElement suspious = driver.findElement(By.xpath("//*[@id='applicableUnblockingCodes']/div/div[2]/ul[2]/li[43]/div"));
         ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", suspious); 
         suspious.click();
         System.out.println("last REASON CODE checked");
         
         
      // after selecting REASON CODES from the multi-select checkbox dropdown "Its a TAB out click"
         driver.findElement(By.xpath("//*[@id='applicableUnblockingCodes']/div/div[2]/ul[1]/li/input")).click();
         System.out.println("TAB OUT done");
         
         if(action.equals("block_with_case_details_by_l1")||action.equals("block_without_case_details_by_l1")) {
        	 
         Thread.sleep(1000);
       //selecting the SELECT DECISION dropdown and choose BLOCK
         new Select(driver.findElement(By.xpath("/html/body/app/div/div/customertransactionreview/div/div[15]/div/fieldset/div[2]/div/select"))).selectByIndex(1);
         System.out.println("SELECT DECISION dropdown clicked and chose BLOCK"); 
             
      // entering remarks for blocking the transaction
         driver.findElement(By.id("remarks")).sendKeys("suspicious found - BLOCK by l1");
             System.out.println("remarks entered"); 
             } else if(action.equals("block_with_review_no_case_details_l1_l2")|| action.equals("block_with_review_with_case_details_l1_l2")) {
            	  new Select(driver.findElement(By.xpath("/html/body/app/div/div/customertransactionreview/div/div[15]/div/fieldset/div[2]/div/select"))).selectByIndex(2);
                  System.out.println("SELECT DECISION dropdown clicked and chose BLOCK with Review");
                  
                  ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", finalSubmit); 
                  Thread.sleep(1000);
                  
                 // click SECTIONS TO REVIEW drop down 
                 driver.findElement(By.xpath("//*[@id='sectionToReviewList']/div/div[1]/span")).click();
                 
                 //chose TRANSACTION_DETAILS from the multi select check box
                 WebElement TRAN_DETAILS = driver.findElement(By.xpath("//*[@id='sectionToReviewList']/div/div[2]/ul[2]/li[1]/div"));
                 TRAN_DETAILS.click();
                 System.out.println("FIRST REASON CODE checked");
                  
                 Thread.sleep(1000);
               // entering remarks for moving it to L2 review
                  driver.findElement(By.id("remarks")).sendKeys("Need L2 review then block");
                  System.out.println("remarks entered");
             }
	 }
	 
	 private void decisionUnblock(String action) throws InterruptedException {
		 Thread.sleep(2000);
		 //scroll down until final SUBMIT button visible
         WebElement finalSubmit = driver.findElement(By.xpath("//*[@id='Decision']/fieldset/div[4]/button[3]"));
         ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", finalSubmit); 
         
   	    Thread.sleep(1000);
   	 
         // if l1 user reviewing the transaction then reason code selection, decision selection & remarks entering will happen
         // else l2 user reviewing the transaction then only remarks entering will happen
         //Click on the REASON CODES dropdown 
         driver.findElement(By.xpath("//*[@id='applicableUnblockingCodes']/div/div[1]/span")).click();
         System.out.println("REASON CODES dropdown clicked by L1");
           
         // selectng the FIRST REASON CODE by clicking since it is a multi-select 
         WebElement noSuspious = driver.findElement(By.xpath("//*[@id='applicableUnblockingCodes']/div/div[2]/ul[2]/li[2]"));
         noSuspious.click();
         System.out.println("FIRST REASON CODE checked by L1 user");
           
         // after selecting REASON CODES from the multi-select checkbox dropdown "Its a TAB out click"
         driver.findElement(By.xpath("//*[@id='applicableUnblockingCodes']/div/div[2]/ul[1]/li/input")).click();
         System.out.println("TAB OUT done yb L1 user"); 
         
         if(action.equals("unblock_release_by_l1")) {
         //selecting the SELECT DECISION dropdown and choose UNBLOCK AND RELEASE
         new Select(driver.findElement(By.xpath("/html/body/app/div/div/customertransactionreview/div/div[15]/div/fieldset/div[2]/div/select"))).selectByIndex(1);
         System.out.println("SELECT DECISION dropdown clicked and chose UNBLOCK AND RELEASE by L1 user"); 
         
         // entering remarks for releasing the transaction
         driver.findElement(By.id("remarks")).sendKeys("No suspicious found - Release by l1");
         System.out.println("remarks entered");   
         
         } else if(action.equals("unblock_release_with_review_no_case_details_by_l1_l2")) {
        	//selecting the SELECT DECISION dropdown and choose UNBLOCK AND RELEASE WITH REVIEW
        	 new Select(driver.findElement(By.xpath("/html/body/app/div/div/customertransactionreview/div/div[15]/div/fieldset/div[2]/div/select"))).selectByIndex(2);
             System.out.println("SELECT DECISION dropdown clicked and chose UNBLOCK AND RELEASE WITH REVIEW by L1 user"); 
             
             ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", finalSubmit); 
             Thread.sleep(1000);
             System.out.println("Scroll down until Final Submit button visible by L1 user"); 
             
            // click SECTIONS TO REVIEW drop down 
            driver.findElement(By.xpath("//*[@id='sectionToReviewList']/div/div[1]/span")).click();
            System.out.println("Clicking Sections to Review drop down by L1 user "); 
            
            //chose TRANSACTION_DETAILS from the multi select check box
            WebElement TRAN_DETAILS = driver.findElement(By.xpath("//*[@id='sectionToReviewList']/div/div[2]/ul[2]/li[1]/div"));
            TRAN_DETAILS.click();
            System.out.println("FIRST REASON CODE checked by L1 user");
             
            Thread.sleep(1000);
          // entering remarks for moving it to L2 review
             driver.findElement(By.id("remarks")).sendKeys("Need L2 review then Release");
             System.out.println("remarks entered by L1 user"); 
             
         } else if(action.equals("unblock_release_with_review_add_case_details_by_l1_l2")) {
        	//selecting the SELECT DECISION dropdown and choose UNBLOCK AND RELEASE WITH REVIEW
        	 new Select(driver.findElement(By.xpath("/html/body/app/div/div/customertransactionreview/div/div[15]/div/fieldset/div[2]/div/select"))).selectByIndex(2);
             System.out.println("SELECT DECISION dropdown clicked and chose UNBLOCK AND RELEASE WITH REVIEW"); 
         
             
             ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", finalSubmit); 
             Thread.sleep(1000);
             
            // click SECTIONS TO REVIEW drop down 
            driver.findElement(By.xpath("//*[@id='sectionToReviewList']/div/div[1]/span")).click();
            
            //chose TRANSACTION_DETAILS from the multi select check box
            WebElement TRAN_DETAILS = driver.findElement(By.xpath("//*[@id='sectionToReviewList']/div/div[2]/ul[2]/li[1]/div"));
            TRAN_DETAILS.click();
            System.out.println("FIRST REASON CODE checked");
             
            Thread.sleep(1000);
          // entering remarks for moving it to L2 review
             driver.findElement(By.id("remarks")).sendKeys("Need L2 review then Release");
         } else if (action.equals("hold_by_l1")) {
        	 //selecting the SELECT DECISION dropdown and choose UNBLOCK AND RELEASE
             new Select(driver.findElement(By.xpath("/html/body/app/div/div/customertransactionreview/div/div[15]/div/fieldset/div[2]/div/select"))).selectByIndex(1);
             System.out.println("SELECT DECISION dropdown clicked and chose UNBLOCK AND RELEASE "); 
             
             // entering remarks for releasing the transaction
             driver.findElement(By.id("remarks")).sendKeys("No suspicious found - Hold by l1");
             System.out.println("remarks entered to hold");
             
            // Thread.sleep(1000);
             //Clicking the Hold buton
             driver.findElement(By.xpath("//*[@id='Decision']/fieldset/div[4]/button[1]")).click();
             System.out.println("hold button clicked");
             
             Thread.sleep(1000);
             WebElement holdSubmit = driver.findElement(By.xpath("//*[@id='Case']/fieldset/div[3]/button"));
             ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", holdSubmit); 
             
             Thread.sleep(1000);
             new Select(driver.findElement(By.xpath("//*[@id='categoryType']"))).selectByIndex(1);
             System.out.println("hold reason selected"); 
             
             driver.findElement(By.xpath("//*[@id='Case']/fieldset/div[3]/button")).click();
             System.out.println("submit button clicked after selecting the hold reason");
             
             driver.quit();
             System.out.println("driver closed after move the transaction to HOLD status");
         }  
	 }
	  
	 //final submit button web element differs for L1 user & L2 user so two different methods
	 private void clickFinalSubmitL1() throws InterruptedException {
		     Thread.sleep(1000);
		     WebElement finalSubmit = driver.findElement(By.xpath("/html/body/app/div/div/customertransactionreview/div/div[15]/div/fieldset/div[4]/button[3]"));
		     ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", finalSubmit); 
		     finalSubmit.click(); 
	         System.out.println("final submit clicked from L1 user");  
	         
	         driver.quit();
     	     System.out.println("closing the driver from l1 screen");
	 }
	 
	 private void clickFinalSubmitL2(String action) throws InterruptedException {
	     Thread.sleep(1000);
	     if(action.equals("block_with_review_no_case_details_l1_l2")||action.equals("block_with_review_with_case_details_l1_l2")) {
	    	 WebElement finalSubmit = driver.findElement(By.xpath("/html/body/app/div/div/customertransactionreview/div/div[15]/div/fieldset/div[4]/button[2]"));
		     ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", finalSubmit); 
		     finalSubmit.click(); 
	         System.out.println("final submit clicked from L2 user");  
	     }else if(action.equals("unblock_release_by_l1")|| action.equals("unblock_release_with_review_no_case_details_by_l1_l2")||action.equals("unblock_release_with_review_add_case_details_by_l1_l2")){
	     WebElement finalSubmit = driver.findElement(By.xpath("/html/body/app/div/div/customertransactionreview/div/div[15]/div/fieldset/div[4]/button[3]"));
	     ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", finalSubmit); 
	     finalSubmit.click(); 
         System.out.println("final submit clicked from L2 user");  
	     }
	     
	 //    Thread.sleep(5000);
//	     finalSubmit.click(); 
//         System.out.println("final submit clicked from L2 user");  
         
         driver.quit();
 	     System.out.println("closing the driver from l2 screen");
 }
	 
	 private void releaseWithReviewL2() throws InterruptedException {
		 Thread.sleep(1000);
		 //scroll until Submit button to view
		 WebElement finalSubmit = driver.findElement(By.xpath("/html/body/app/div/div/customertransactionreview/div/div[15]/div/fieldset/div[4]/button[2]"));
         ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", finalSubmit); 
         System.out.println("scroll down until Submit button visible by L2 user");  
         
         //enter remarks in the text area
         driver.findElement(By.xpath("//*[@id='Decision']/fieldset/div[3]/div[2]/textarea")).sendKeys("No suspicious found - Release by l2");
         System.out.println("entering remarks by L2 user");  
	 }
	 
	 private void blockWithReviewL2() throws InterruptedException {
		 Thread.sleep(1000);
		 //scroll until Submit button to view
		 WebElement finalSubmit = driver.findElement(By.xpath("/html/body/app/div/div/customertransactionreview/div/div[15]/div/fieldset/div[4]/button[2]"));
         ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", finalSubmit); 
         System.out.println("scroll until Submit button to view");
         
         //selecting BLOCK from the decision dropdown
         new Select(driver.findElement(By.xpath("/html/body/app/div/div/customertransactionreview/div/div[15]/div/fieldset/div[2]/div[1]/select"))).selectByIndex(1);
         System.out.println("change DECISION dropdown option from BLOCK WITH REVIEW to BLOCK"); 
         
         //enter remarks in the text area
         driver.findElement(By.xpath("//*[@id='Decision']/fieldset/div[3]/div[2]/textarea")).sendKeys("suspicious found - Block by l2");
         System.out.println("remarks entered in the text area");
	 }
	 
	 private void loginL2() throws InterruptedException {
		    Thread.sleep(1000);	
		    // login as L2 user
	        driver.findElement(By.id("userName")).sendKeys("l2");
	        driver.findElement(By.id("password")).sendKeys("l2");
	        driver.findElement(By.xpath("//*[@id='loginForm']/button")).click();
	        System.out.println("Login successful by L2");
	    }
	 
	 private void caseDetailsByL1() throws InterruptedException {
		 Thread.sleep(1000);
		 driver.findElement(By.xpath("//button[text() = 'Case Details']")).click();
		 System.out.println("case details button clicked");
		 
		 new Select(driver.findElement(By.xpath("//*[@id='categoryType']"))).selectByIndex(2);
		 System.out.println("category type selected from the dropdown");
		 
		 Thread.sleep(1000);
		 new Select(driver.findElement(By.xpath("//*[@id='category']"))).selectByIndex(2);
		 System.out.println("category selected from the dropdown");
		 
		 driver.findElement(By.xpath("//*[@id='Case']/fieldset/div[3]/table/tbody/tr[2]/td/input")).click();
		 System.out.println("checkbox for case clicked");
		 
		 Thread.sleep(1000);
	     driver.findElement(By.xpath("/html/body/app/div/div/customertransactionreview/div/div[18]/div/fieldset/button")).click();
		 System.out.println("case submit button clicked");
	 }
	 
	  private void performActionBasedOnParameters(String action, String transactionNumber) throws Exception {
	        switch (action) {
	            case "unblock_release_by_l1":
	            	launchChromePassUrl();
	            	loginL1();
	            	searchFilterClickL1();
	            	searchByTranNum(transactionNumber);
	            	reviewButtonClicked();
	            	checkUntilMonitoredSignal();
	            	checkParticipants();
	            	tranPurposeEvidence();
	            	decisionUnblock(action);
	            	clickFinalSubmitL1();
	                break;
	            case "unblock_release_with_review_no_case_details_by_l1_l2":
	            	launchChromePassUrl();
	            	loginL1();
	            	searchFilterClickL1();
	            	searchByTranNum(transactionNumber);
	            	reviewButtonClicked();
	            	checkUntilMonitoredSignal();
	            	checkParticipants();
	            	tranPurposeEvidence();
	            	decisionUnblock(action);
	            	clickFinalSubmitL1();
	            	launchChromePassUrl();
	            	loginL2();
	            	searchFilterClickL2();
	            	searchByTranNum(transactionNumber);
	            	reviewButtonClicked();
	            	releaseWithReviewL2();
	            	clickFinalSubmitL2(action);
	                System.out.println("Performing action: " + action);
	                break;
	            case "unblock_release_with_review_add_case_details_by_l1_l2":
	            	launchChromePassUrl();
	            	loginL1();
	            	searchFilterClickL1();
	            	searchByTranNum(transactionNumber);
	            	reviewButtonClicked();
	            	checkUntilMonitoredSignal();
	            	checkParticipants();
	            	tranPurposeEvidence();
	            	decisionUnblock(action);
	            	caseDetailsByL1();
	            	decisionUnblock(action);
	            	clickFinalSubmitL1();
	            	launchChromePassUrl();
	            	loginL2();
	            	searchFilterClickL2();
	            	searchByTranNum(transactionNumber);
	            	reviewButtonClicked();
	            	releaseWithReviewL2();
	            	clickFinalSubmitL2(action);
	                System.out.println("Performing action: " + action);
	                break;
	            case "hold_by_l1":
	            	launchChromePassUrl();
	             	loginL1();
	             	searchFilterClickL1();
	            	searchByTranNum(transactionNumber);
	            	reviewButtonClicked();
	            	checkUntilMonitoredSignal();
	            	checkParticipants();
	            	tranPurposeEvidence();
	            	decisionUnblock(action);
	                System.out.println("Performing action: " + action);
	                break;
	            case "block_with_case_details_by_l1":
	            	launchChromePassUrl();
	            	loginL1();
	             	searchFilterClickL1();
	            	searchByTranNum(transactionNumber);
	            	reviewButtonClicked();
	            	checkUntilMonitoredSignal();
	            	checkParticipants();
	            	tranPurposeEvidence();
	            	decisionBlock(action);
	            	caseDetailsByL1();
	            	decisionBlock(action);
	            	clickFinalSubmitL1();
	                System.out.println("Performing action: " + action);
	                break;
	            case "block_without_case_details_by_l1":
	            	launchChromePassUrl();
	            	loginL1();
	             	searchFilterClickL1();
	            	searchByTranNum(transactionNumber);
	            	reviewButtonClicked();
	            	checkUntilMonitoredSignal();
	            	checkParticipants();
	            	tranPurposeEvidence();
	            	decisionBlock(action);
	            	clickFinalSubmitL1();
	                System.out.println("Performing action: " + action);
	                break;
	            case "block_with_review_no_case_details_l1_l2":
	            	launchChromePassUrl();
	            	loginL1();
	            	searchFilterClickL1();
	            	searchByTranNum(transactionNumber);
	            	reviewButtonClicked();
	            	checkUntilMonitoredSignal();
	            	checkParticipants();
	            	tranPurposeEvidence();
	            	decisionBlock(action);
	            	clickFinalSubmitL1();
	            	launchChromePassUrl();
	            	loginL2();
	            	searchFilterClickL2();
	            	searchByTranNum(transactionNumber);
	            	reviewButtonClicked();
	            	blockWithReviewL2();
	            	clickFinalSubmitL2(action);
	                System.out.println("Performing action: " + action);
	                break;
	            case "block_with_review_with_case_details_l1_l2":
	            	launchChromePassUrl();
	            	loginL1();
	            	searchFilterClickL1();
	            	searchByTranNum(transactionNumber);
	            	reviewButtonClicked();
	            	checkUntilMonitoredSignal();
	            	checkParticipants();
	            	tranPurposeEvidence();
	            	decisionBlock(action);
	            	caseDetailsByL1();
	            	decisionBlock(action);
	            	clickFinalSubmitL1();
	            	launchChromePassUrl();
	            	loginL2();
	            	searchFilterClickL2();
	            	searchByTranNum(transactionNumber);
	            	reviewButtonClicked();
	            	blockWithReviewL2();
	            	clickFinalSubmitL2(action);
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
}
