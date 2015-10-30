package LearningTestNG;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class TestNGAnotation_1 {
	
	
	public static final String USERNAME = "earlwillis1";
	public static final String AUTOMATE_KEY = "XsPyFTirN4mH8aCLMB9A";
	public static final String URL = "http://" + USERNAME + ":" + AUTOMATE_KEY + "@hub.browserstack.com/wd/hub";	
	public WebDriver driver;
	
	static String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
	
	@BeforeClass
	@org.testng.annotations.Parameters(value={"browser","version","platform"})

	public void beforeClass(String browser, String version, String platform) throws Exception {
        
		System.out.println("Opening Browser");
	   	// driver = new FirefoxDriver();
        // driver.get("http://www.vicks.com");
		
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
	public void login(){		
		System.out.println("Login-Test 1");
		try{
	    driver.findElement(By.linkText("Login")).click();
	   
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	    driver.findElement(By.id("phdesktopbody_0_username")).clear();
	    driver.findElement(By.id("phdesktopbody_0_username")).sendKeys("earl.willis@publicismodem.com");
	    driver.findElement(By.id("phdesktopbody_0_password")).clear();
	    driver.findElement(By.id("phdesktopbody_0_password")).sendKeys("Zaq12wsx!");
	    driver.findElement(By.id("phdesktopbody_0_submit")).click();
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	    String ActualText = driver.findElement(By.id("phdesktopbody_0_StepOneMessage")).getText();
	    
	    System.out.println("User logs in");
	    Assert.assertEquals(ActualText, "YOUR BASIC INFORMATION");
	    
		} catch(Exception e){ Assert.fail(); 
		//8888To fail test in case of any elementt identification failurefdsfafds sf	

		} 

				
	}


	
	@Test(dependsOnMethods={"login"})
	public void logout(){
		System.out.println("Logout - 3");
		
		try{
	    driver.findElement(By.id("phdesktopheader_0_phdesktopheadertop_2_LogOffLink")).click();
	    driver.findElement(By.id("phdesktopheader_0_phdesktopheadertop_2_anchrContinue")).click();
	    String ActualLabel = driver.findElement(By.id("phdesktopbody_0_Header")).getText();
	    System.out.println("Assers Sign-In Label after logging out");
	    Assert.assertEquals(ActualLabel , "Sign In");
	    
		} catch(Exception e){ Assert.fail(); 
		//To fail a test in case of any element identification failurefasdfdsaddfadsfadsfsadfadsfafdgds	

		} 

				
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