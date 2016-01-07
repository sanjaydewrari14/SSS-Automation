package com.excelsoft.testautomation;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import junit.framework.Assert;

public class TC_ABMS_centeradmin_Traineee extends TC_ABMS_BaseClass
{

	@SuppressWarnings("deprecation")
	@Test
	public void createTrainee() throws InterruptedException
	{
		
		WebDriverWait wd=new WebDriverWait(dvr, 20);
		wd.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(prop.getProperty("abms_loginpage_submitbtn_xpath"))));
		Thread.sleep(10000);
		waitForLoad(dvr);
		mouseHover(By.xpath(prop.getProperty("abms_centeradmin_homepage_menu_trainee_xpath")));	
		//	dvr.findElement(By.xpath(prop.getProperty("abms_centeradmin_homepage_submenu_manageTrainee_xpath"))
		
		explicit_wait(By.xpath(prop.getProperty("abms_centeradmin_homepage_submenu_manageTrainee_xpath")), 10);
		dvr.findElement(By.xpath(prop.getProperty("abms_centeradmin_homepage_submenu_manageTrainee_xpath"))).click();
		dvr.findElement(By.xpath("//a[text()='CREATE']")).click();
		dvr.findElement(By.xpath("//input[@id='national_id']")).sendKeys("2341567895");
		dvr.findElement(By.xpath("//input[@name='first_name']")).sendKeys("TestTrainee");
		dvr.findElement(By.xpath("//input[@name='last_name']")).sendKeys("Decfournewtwo");
		dvr.findElement(By.xpath("//input[@name='email']")).sendKeys("ttdec4new3@yopmail.com");
		dvr.findElement(By.xpath("//input[@name='dob1']")).sendKeys("");
		dvr.findElement(By.xpath("//input[@name='dob1']")).click();
		Thread.sleep(2000);
		dvr.findElement(By.xpath("//a[text()='14']")).click();
		Thread.sleep(3000);
		dvr.findElement(By.xpath("//input[@name='dob2']")).sendKeys("");
		dvr.findElement(By.xpath("//input[@name='dob2']")).click();
		Thread.sleep(2000);
		dvr.findElement(By.xpath("//a[text()='14']")).click();
		
		dvr.findElement(By.xpath("//input[@name='school_gpa']")).sendKeys("67");
		dvr.findElement(By.xpath("//div[@id='s2id_autogen1']")).click();
		Thread.sleep(2000);
		dvr.findElement(By.xpath("//div[text()='Complete']")).click();
		dvr.findElement(By.xpath("//div[@id='s2id_foundation_center']")).click();
		dvr.findElement(By.xpath("//input[@type='text']")).sendKeys("0007");
		Thread.sleep(3000);
		dvr.findElement(By.xpath("//span[text()='0007']")).click();
		dvr.findElement(By.xpath("//div[@id='s2id_enrollment_month']")).click();
		dvr.findElement(By.xpath("//div[text()='Feb']")).click();
		dvr.findElement(By.xpath("//div[@id='s2id_academic_year']")).click();
		dvr.findElement(By.xpath("//div[text()='2009/2010']")).click();
			
		dvr.findElement(By.xpath("//input[@name='qiyas']")).sendKeys("87");
		dvr.findElement(By.xpath("//input[@name='english_grades']")).sendKeys("B1");
		dvr.findElement(By.xpath("//input[@name='IT_grades']")).sendKeys("B");
		dvr.findElement(By.xpath("//div[@id='s2id_trainee_region']")).click();
		dvr.findElement(By.xpath("//div[text()='Eastern Province']")).click();
		explicit_wait(By.xpath("//div[@id='s2id_trainee_city'][@class='select2-container']"), 10);
		Thread.sleep(4000);
		dvr.findElement(By.xpath("//div[@id='s2id_trainee_city']")).click();
		dvr.findElement(By.xpath("//div[text()='Dammam']")).click();
		dvr.findElement(By.xpath("//textarea[@name='address']")).sendKeys("B-20, Sec-20, Noida (U.P)");
		dvr.findElement(By.xpath("//input[@name='pincode']")).sendKeys("321312");
		dvr.findElement(By.xpath("//input[@name='phone']")).sendKeys("43434344343");
		dvr.findElement(By.xpath("//input[@value='Create Trainee']")).click();
		Assert.assertEquals("Trainee added successfully",dvr.findElement(By.xpath("//div[@class='success']")).getText());
	}
	
	

}
