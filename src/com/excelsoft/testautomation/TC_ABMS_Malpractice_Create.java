package com.excelsoft.testautomation;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_ABMS_Malpractice_Create extends TC_ABMS_BaseClass 
{
	
	@Test
	public void generateProvisonalResult() throws InterruptedException, IOException
	{
		try
		{
			Thread.sleep(3000);
			explicit_wait(By.xpath("//a[@class='dropdown-toggle']//span[@class='grading-icon']"), 10);
			WebElement mainMenu = dvr.findElement(By.xpath("//a[@class='dropdown-toggle']//span[@class='grading-icon']"));
			act.moveToElement(mainMenu).moveToElement(dvr.findElement(By.xpath("//a[text()='Manage Malpractice']"))).click().build().perform();
			dvr.findElement(By.xpath("//a[text()='CREATE']")).click();
			dvr.findElement(By.xpath("//div[@id='s2id_mp_center_id']/a")).click();
			Thread.sleep(2000);
			dvr.findElement(By.xpath("//div[text()='"+prop.getProperty("abms_center_name")+"']")).click();
			Thread.sleep(2000);
			dvr.findElement(By.xpath("//div[@id='s2id_mp_qualification_id']/a")).click();
			Thread.sleep(2000);
			dvr.findElement(By.xpath("//div[text()='"+prop.getProperty("abms_intake_associate_Qualname")+"']")).click();
			Thread.sleep(2000);
			dvr.findElement(By.xpath("//div[@id='s2id_mp_intake_id']/a")).click();;
			Thread.sleep(2000);
			dvr.findElement(By.xpath("//div[text()='"+prop.getProperty("abms_intake_name")+"']")).click();
			dvr.findElement(By.xpath("//textarea[@id='mp_comment']")).sendKeys("TestComments");
			dvr.findElement(By.xpath("//input[@id='next_step']")).click();
			dvr.findElement(By.xpath("//a[@id='select_trainee']")).click();
			
			
			explicit_wait(By.xpath("//iframe[contains(@name,'fancybox-frame')]"), 10);
			dvr.switchTo().frame(dvr.findElement(By.xpath("//iframe[contains(@name,'fancybox-frame')]")));
			
			String nid=String.valueOf((long)sh.getRow(3).getCell(0).getNumericCellValue());
			dvr.findElement(By.xpath("//input[@name='search_box']")).sendKeys(nid);
			dvr.findElement(By.xpath("//input[@id='submitbutton']")).click();
			explicit_wait(By.xpath("//input[@value='"+nid+"']"), 10);
			dvr.findElement(By.xpath("//span[@class='custom-check on']")).click();
			Thread.sleep(2000);
			explicit_wait(By.xpath("//a[text()='Add To List']"), 10);
			dvr.findElement(By.xpath("//a[text()='Add To List']")).click();
			handleAlert();
			Thread.sleep(2000);
			dvr.findElement(By.xpath("//input[@name='search_box']")).clear();
			
			String nid1=String.valueOf((long)sh.getRow(4).getCell(0).getNumericCellValue());
			dvr.findElement(By.xpath("//input[@name='search_box']")).sendKeys(nid1);
			dvr.findElement(By.xpath("//input[@id='submitbutton']")).click();
			explicit_wait(By.xpath("//input[@value='"+nid1+"']"), 10);
			dvr.findElement(By.xpath("//span[@class='custom-check on']")).click();
			Thread.sleep(2000);
			explicit_wait(By.xpath("//a[text()='Add To List']"), 10);
			dvr.findElement(By.xpath("//a[text()='Add To List']")).click();
			handleAlert();
			Thread.sleep(2000);
			dvr.switchTo().defaultContent();
			explicit_wait(By.xpath("//div[@class='fancybox-skin']//a[@title='Close']"), 10);
			dvr.findElement(By.xpath("//div[@class='fancybox-skin']//a[@title='Close']")).click();
			Thread.sleep(2000);
			dvr.findElement(By.xpath("//input[@value='HOLD RESULT']")).click();
			Assert.assertEquals(dvr.findElement(By.xpath("//div[@class='success']")).getText(),"Malpractice has been created successfully");
			Thread.sleep(4000);
			if(dvr.findElement(By.xpath("//table[@class='table malpractice-listing']//tr[1]//td[contains(text(),'"+prop.getProperty("abms_center_cohortname")+"')]")).isDisplayed())
			{
				dvr.findElement(By.xpath("//table[@class='table malpractice-listing']//tr[1]//a[@class='blue-anchor']")).click();
				Thread.sleep(3000);
				dvr.findElement(By.xpath("//input[@id='publish']")).click();
				Assert.assertEquals(dvr.findElement(By.xpath("//div[@class='success']")).getText(),"Malpractice status for trainees has been updated successfully.");
				captureScreenshot("malpractice.png");
			}
			
			
			
		}



		catch(Exception e)
		{
			System.out.println("Error while creating malpractice..");
			captureScreenshot("create_malpractice_err.png");
			Assert.fail(e.getMessage());

		}


	}
	

}
