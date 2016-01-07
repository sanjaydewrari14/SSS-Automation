package com.excelsoft.testautomation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.CopyUtils;
import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

public class TC_ABMS_BaseClass
{
	public static WebDriver dvr;
	public static Properties prop;
	public static Actions act;
	public static XSSFWorkbook wb;
	public static XSSFSheet sh;
	public static XSSFRow rw;
	public static XSSFCell cell;

	int i=0;

	@BeforeSuite

	public void initiateBrowser() throws FileNotFoundException, IOException
	{


		wb=new XSSFWorkbook(new FileInputStream("../SSS_ABMS_Automation/inputs/Cohort_Trainees.xlsx"));
		sh=wb.getSheet("Sheet1");


		System.setProperty("webdriver.chrome.driver", "../SSS_ABMS_Automation/inputs/chromedriver.exe");

		dvr=new ChromeDriver();
		dvr.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		dvr.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
		dvr.manage().window().maximize();
		dvr.get("http://qc.abms.imfinity.com/SSS_ABMS_3.1/public/login");
		//dvr=new FirefoxDriver();

	}

	@BeforeTest
	@Parameters("user")
	public void login(String user) throws FileNotFoundException, IOException
	{
       try
       {
    	   
		act=new Actions(dvr);
		prop=new Properties();
		prop.load(new FileInputStream("../SSS_ABMS_Automation/ABMS_OR/TestData.properties"));
		System.out.println(user);
		if(user.equals("abmsadmin"))
		{
			System.out.println("inside if");
			user_login(prop.getProperty("abms_loginpage_abms_email_value"),prop.getProperty("abms_loginpage_abms_password_value"));
		}
		else
		{
				user_login(prop.getProperty("abms_loginpage_center_email_value"),prop.getProperty("abms_loginpage_center_password_value"));
		
		}
       }
       catch(Exception e)
       {
    	   System.out.println("Error while login through user "+user);
           captureScreenshot("Error_login"+user+".png");
           Assert.fail(e.getMessage());
    	   
       }
       

	}





	public void user_login(String center_email, String center_password)
	{
		System.out.println("insideuser_login");
		dvr.findElement(By.xpath(prop.getProperty("abms_loginpage_email_xpath"))).sendKeys(center_email);
		dvr.findElement(By.xpath(prop.getProperty("abms_loginpage_password_xpath"))).sendKeys(center_password);
		dvr.findElement(By.xpath(prop.getProperty("abms_loginpage_submitbtn_xpath"))).click();
	}

	public static WebDriver getDriver() 
	{

		return dvr;
	}



	public void waitForLoad(WebDriver driver) {
		ExpectedCondition<Boolean> pageLoadCondition = new
				ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
			}
		};
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(pageLoadCondition);
	}


	public void captureScreenshot(String filename) throws IOException
	{
		
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy_hhmmss");
		Date curDate = new Date();
		String strDate = sdf.format(curDate);
		
		File f=((TakesScreenshot)dvr).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(f, new File("../SSS_ABMS_Automation/screenshots/"+strDate+"_"+filename));
		
		
	}

	public void mouseHover(By by)
	{
		Actions act=new Actions(dvr);
		act.moveToElement(dvr.findElement(by)).build().perform();

	}

	public void explicit_wait(By by, long time)
	{
		WebDriverWait wd=new WebDriverWait(dvr, time);
		wd.until(ExpectedConditions.presenceOfElementLocated(by));
	}


	public void handleAlert()
	{
		Alert alr=dvr.switchTo().alert();
		alr.accept();
	}

	@AfterTest
	public void closeSession()
	{
		//dvr.quit();
	}



}
