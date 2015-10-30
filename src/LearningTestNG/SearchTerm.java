package LearningTestNG;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;



public class SearchTerm {
	
	public static final String USERNAME = "earlwillis1";
	public static final String AUTOMATE_KEY = "XsPyFTirN4mH8aCLMB9A";
	public static final String URL = "http://" + USERNAME + ":" + AUTOMATE_KEY + "@hub.browserstack.com/wd/hub";	
	public WebDriver driver;
	
	static String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
	
	@BeforeClass
	@org.testng.annotations.Parameters(value={"browser","version","platform"})

	public void beforeClass(String browser, String version, String platform) throws Exception {

        
		System.out.println("Opening Browser");
		
		
		
		DesiredCapabilities capability = new DesiredCapabilities();
		capability.setCapability("platform",platform);
	    capability.setCapability("browserName", browser);
	    capability.setCapability("browserVersion", version);
	    capability.setCapability("Red Lobster", "P1");
	    capability.setCapability("build", "1.0");
	    capability.setCapability("browserstack.debug", "true");
	    	    
	    try {
			driver = new RemoteWebDriver(
			  new URL("http://earlwillis1:XsPyFTirN4mH8aCLMB9A@hub.browserstack.com/wd/hub"),
			  capability
			  
			);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    driver.manage().window().maximize();
	    driver.get("http://www.metamucil.com");
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
                     

	}
	

	@BeforeMethod
	public void beforeMethod(){
		System.out.println("This is the beforeMethod-blanks");		

				
	}
	
	@Test(priority=1)
	public void searchproducts(){		
		System.out.println("Search - Product Results");
		
	//	driver.findElement(By.id("phdesktopheader_0_phdesktopheadermiddle_1_txtSearchBox")).click();
		driver.findElement(By.id("phdesktopheader_0_phdesktopheadermiddle_1_txtSearchBox")).sendKeys("vicks");
		driver.findElement(By.id("phdesktopheader_0_phdesktopheadermiddle_1_imgSearch")).click();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		//String ActualSearch = driver.findElement(By.id("phdesktopbody_0_phdesktopproductresults_1_RptrProductList_lblProductName_0")).getText();
		//Assert.assertEquals(ActualSearch, ActualSearch);
				
	}

	@Test(dependsOnMethods={"searchproducts"})
	public void searchcontent(){
		System.out.println("Search - Content Results");
		
		//driver.findElement(By.id("phdesktopbody_0_phdesktoptabheader_0_anchorContentResult")).click();
		
		//String ActualSearch2 = driver.findElement(By.cssSelector("p.search-page-link > a > span")).getText();
		//Assert.assertEquals(ActualSearch2, "Vicks History");
  
			

	}
			

		


	
	@AfterMethod
	public void afterMethod(ITestResult testResult) throws IOException{
		System.out.println("This is the after method");
		

			if (testResult.getStatus() == ITestResult.FAILURE) { 
			System.out.println("Test Results=" + testResult.getStatus()); 
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE); 
			FileUtils.copyFile(scrFile, new File(timeStamp + "-testScreenShot.jpg")); 
		
	}
	}

	
	@AfterClass
	public void afterClass(){
		System.out.println("Destroy Selenium for class 1");
		driver.close();
		driver.quit();
	}
}