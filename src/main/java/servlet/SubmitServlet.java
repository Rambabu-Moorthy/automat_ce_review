package servlet;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.testng.TestNG;

@WebServlet("/clearingReview")
public class SubmitServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {  
		
	//	CompleteReview cr = CompleteReview.getInstance();
		String transactionNumber = req.getParameter("transactionNumber");
        String action = req.getParameter("action");
        System.out.println("entered transaction number is " +transactionNumber);
        System.out.println("selected Action is "+action);
   
        // Create TestNG XML dynamically
        String xmlContent = generateTestNGXml(action, transactionNumber);
        String xmlFilePath = getServletContext().getRealPath("/"+"testNg.xml");
        try (FileWriter writer = new FileWriter(xmlFilePath)) {
            writer.write(xmlContent);
        }
        
     // Set parameters to pass to TestNG
        List<String> suiteFiles = new ArrayList<>();
        suiteFiles.add(xmlFilePath);
        
        TestNG testng = new TestNG();
        testng.setTestSuites(suiteFiles);
        testng.run();
      
        
        
          String successMessage = "Review Completed - Please check";
          PrintWriter out = resp.getWriter();
          out.print(successMessage);
          out.flush(); 
	}
	
	 private String generateTestNGXml(String action, String transactionNumber) {
	        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
	                "<!DOCTYPE suite SYSTEM \"dtd/testng-1.0.dtd\">\n" +
	                "<suite name=\"Suite\">\n" +
	                "    <test name=\"Test\">\n" +
	                "        <parameter name=\"action\" value=\"" + action + "\"/>\n" +
	                "        <parameter name=\"transactionNumber\" value=\"" + transactionNumber + "\"/>\n" +
	                "        <classes>\n" +
	                "            <class name=\"automationCode.CompleteReview\"/>\n" +
	                "        </classes>\n" +
	                "    </test>\n" +
	                "</suite>";
	    }
	 
	
	
//	private void runTestNGTests() {
//        TestListenerAdapter tla = new TestListenerAdapter();
//        TestNG testng = new TestNG();
//        List<String> suites = new ArrayList<>();
//        suites.add("/home/rambabu/Music/webappauto/testNg.xml"); // Path to your testng.xml or can specify classes programmatically
//        testng.setTestSuites(suites);
//        testng.addListener(tla);
//        testng.run();
//    }
}
