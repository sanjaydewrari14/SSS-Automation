package com.excelsoft.testautomation;


import java.io.IOException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_ABMS_centeradmin_Cohort_Create extends TC_ABMS_BaseClass 
{

	@Test
	public void tc_CreateCohort() throws InterruptedException, IOException
	{
		try
		{
		Thread.sleep(5000);
		explicit_wait(By.xpath("//a[@class='dropdown-toggle']//span[@class='cohort-icon']"), 10);
		WebElement mainMenu = dvr.findElement(By.xpath("//a[@class='dropdown-toggle']//span[@class='cohort-icon']"));
		act.moveToElement(mainMenu).moveToElement(dvr.findElement(By.xpath("//a[text()='Cohort']"))).click().build().perform();
		dvr.findElement(By.xpath("//a[text()='CREATE']")).click();
		dvr.findElement(By.xpath("//input[@id='name']")).sendKeys(prop.getProperty("abms_center_cohortname"));
		dvr.findElement(By.xpath("//textarea[@id='description']")).sendKeys("TestDescription");
		dvr.findElement(By.xpath("//div[@id='s2id_qualification']/a")).click();
		explicit_wait(By.xpath("//div[text()='"+prop.getProperty("abms_intake_associate_Qualname")+"']"), 10);
		dvr.findElement(By.xpath("//div[text()='"+prop.getProperty("abms_intake_associate_Qualname")+"']")).click();
		Thread.sleep(3000);
		dvr.findElement(By.xpath("//div[@id='s2id_intake_id']/a")).click();
		//dvr.findElement(By.xpath("//input[@type='text']")).sendKeys("intakeDec1");
		Thread.sleep(3000);
		//explicit_wait(By.xpath("//div[text()='intakeDec1']"), 10);
		dvr.findElement(By.xpath("//div[text()='"+prop.getProperty("abms_intake_name")+"']")).click();
		Thread.sleep(3000);
		dvr.findElement(By.xpath("//input[@name='next']")).click();
		dvr.findElement(By.xpath("//span[text()='Select Trainees']")).click();
		explicit_wait(By.xpath("//iframe[contains(@name,'fancybox-frame')]"), 10);
		dvr.switchTo().frame(dvr.findElement(By.xpath("//iframe[contains(@name,'fancybox-frame')]")));
		
		int lastrow=sh.getLastRowNum();
		
		
		for(int i=1; i<=lastrow; i++)
		{
			rw=sh.getRow(i);
			cell=rw.getCell(0);
			String nid=String.valueOf((long)(cell.getNumericCellValue()));

			dvr.findElement(By.xpath("//input[@name='search_box']")).sendKeys(nid);
			dvr.findElement(By.xpath("//input[@id='searchbuttoncohort']")).click();
			explicit_wait(By.xpath("//input[@value='"+nid+"']"), 10);
			dvr.findElement(By.xpath("//span[@class='custom-check on']")).click();
			dvr.findElement(By.xpath("//a[text()='ADD TO LIST']")).click();
			handleAlert();
			Thread.sleep(2000);
			dvr.findElement(By.xpath("//input[@name='search_box']")).clear();

		}

		dvr.switchTo().defaultContent();
		explicit_wait(By.xpath("//div[@class='fancybox-skin']//a[@title='Close']"), 10);
		dvr.findElement(By.xpath("//div[@class='fancybox-skin']//a[@title='Close']")).click();
		Thread.sleep(4000);
		//((JavascriptExecutor)dvr).executeScript("window.scrollTo(0,"+dvr.findElement(By.xpath("//input[@id='savedraft']")).getLocation().y+")");
		dvr.findElement(By.xpath("//input[@id='publishcohort']")).click();
		Alert alr=dvr.switchTo().alert();
		alr.accept();
		dvr.switchTo().frame(dvr.findElement(By.xpath("//iframe[contains(@name,'fancybox-frame')]")));
		String suc_msg1=dvr.findElement(By.xpath("//ul[@class='status-popup']/li[1]")).getText().trim();
		Assert.assertEquals(suc_msg1, "Cohort is published/updated successfully");
		
		String suc_msg2=dvr.findElement(By.xpath("//ul[@class='status-popup']/li[2]")).getText().trim();
		Assert.assertEquals(suc_msg2, "Enrollment for pathway "+prop.getProperty("abms_intake_associate_Qualname")+"-a is successful");
		captureScreenshot("cohort.png");
		dvr.switchTo().defaultContent();
		explicit_wait(By.xpath("//div[@class='fancybox-skin']//a[@title='Close']"), 10);
		dvr.findElement(By.xpath("//div[@class='fancybox-skin']//a[@title='Close']")).click();
		Thread.sleep(8000);
		
		}
		catch(Exception e)
		{
			System.out.println("Error creating cohort..");
			captureScreenshot("cohort_err.png");
			Assert.fail(e.getMessage());
		}


	}
	
	public static WebDriver getDriver() 
	{
		
        return dvr;
    }


}
