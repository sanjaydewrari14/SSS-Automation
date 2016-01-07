package com.excelsoft.testautomation;


import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TC_ABMS_admin_ImportTrainee extends TC_ABMS_BaseClass 
{
	
	
	@Test

	public void tc_abms_importTrainee() throws IOException, InterruptedException
	{
     
		try
		{
			
		Thread.sleep(4000);	
		System.out.println("inside import trainee");
		act.moveToElement(dvr.findElement(By.xpath("//a[@class='dropdown-toggle']//span[@class='trainee-icon']"))).moveToElement(dvr.findElement(By.xpath("//a[text()='Bulk Upload']"))).click().build().perform();
		explicit_wait(By.xpath("//iframe[contains(@name,'fancybox-frame')]"), 10);
		dvr.switchTo().frame(dvr.findElement(By.xpath("//iframe[contains(@name,'fancybox-frame')]")));
		String path=System.getProperty("user.dir");
		dvr.findElement(By.xpath("//input[@id='uploadBtn'][@name='bulk_import']")).sendKeys(path+"/inputs/Cohort_Trainees.xlsx");
		dvr.findElement(By.xpath("//input[@value='Upload']")).click();
		Assert.assertEquals(dvr.findElement(By.xpath("//span[@class='success-popup popup_validation_message']")).getText(), "10 Records Imported Successfully.");
		captureScreenshot("importtrainee.png");
		
		dvr.switchTo().defaultContent();
		explicit_wait(By.xpath("//div[@class='fancybox-skin']//a[@title='Close']"), 10);
		dvr.findElement(By.xpath("//div[@class='fancybox-skin']//a[@title='Close']")).click();
		Thread.sleep(5000);
		act.moveToElement(dvr.findElement(By.xpath("//li[@id='header-user-dropdown']"))).moveToElement(dvr.findElement(By.xpath("//a[text()='Logout']"))).click().build().perform();
		}
		catch(Exception e)
		{
			System.out.println("Error while importing trainee..");
			captureScreenshot("importtrainee_err.png");
			Assert.fail(e.getMessage());
		}
		
	}
	

	
	

}
