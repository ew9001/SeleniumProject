package LearningTestNG;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SearchTerm {
	
	public WebDriver driver;
	
	static String timeStamp1 = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
	
	@BeforeClass

	public void beforeClass(){
        
		System.out.println("Opening Browser");
	   	driver = new FirefoxDriver();
        driver.get("http://www.vicks.com");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();

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
	public void afterMethod(){
		System.out.println("This is the after method");

	}

	
	@AfterClass
	public void afterClass(){
		System.out.println("Destroy Selenium for class 1");
		driver.close();
		driver.quit();
	}
}