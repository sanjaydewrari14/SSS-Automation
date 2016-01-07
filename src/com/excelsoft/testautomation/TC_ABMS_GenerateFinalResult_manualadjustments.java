package com.excelsoft.testautomation;

import java.io.IOException;
import java.security.spec.KeySpec;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_ABMS_GenerateFinalResult_manualadjustments extends TC_ABMS_BaseClass
{

	
	@Test
	public void generateFinalResult() throws IOException
	{
		try
		{
			Thread.sleep(5000);
			explicit_wait(By.xpath("//a[@class='dropdown-toggle']//span[@class='grading-icon']"), 10);
			WebElement mainMenu = dvr.findElement(By.xpath("//a[@class='dropdown-toggle']//span[@class='grading-icon']"));
			act.moveToElement(mainMenu).moveToElement(dvr.findElement(By.xpath("//a[text()='Publish Final Result']"))).click().build().perform();
			dvr.findElement(By.xpath("//div[@id='s2id_qualificationCode']/a")).click();
			explicit_wait(By.xpath("//div[text()='"+prop.getProperty("abms_intake_associate_Qualname")+"']"), 10);
			dvr.findElement(By.xpath("//div[text()='"+prop.getProperty("abms_intake_associate_Qualname")+"']")).click();
			Thread.sleep(4000);
			dvr.findElement(By.xpath("//div[@id='s2id_intakeName']/a")).click();
			Thread.sleep(3000);
			dvr.findElement(By.xpath("//div[text()='"+prop.getProperty("abms_intake_name")+"']")).click();
			dvr.findElement(By.xpath("//div[@id='s2id_centerName']/a")).click();
			Thread.sleep(3000);
			dvr.findElement(By.xpath("//div[text()='"+prop.getProperty("abms_center_name")+"']")).click();
			Thread.sleep(3000);
			dvr.findElement(By.xpath("//input[@value='SHOW FINAL MARKS']")).click();
			Thread.sleep(7000);
			manualadjustment();
			Thread.sleep(3000);
			captureScreenshot("finalresult_publish.png");
			explicit_wait(By.xpath("//input[@value='PUBLISH FINAL RESULT']"), 10);
			dvr.findElement(By.xpath("//input[@value='PUBLISH FINAL RESULT']")).click();
			Thread.sleep(2000);
			handleAlert();
			Assert.assertEquals(dvr.findElement(By.xpath("//div[@class='success']")).getText(), "Final Result Published successfully");
		}
		catch(Exception e)
		{
			System.out.println("Error while generating final result");
			captureScreenshot("Generate_finalresult_error.png");
			Assert.fail(e.getMessage());

		}
		
		
		
	}
	
	public void manualadjustment() throws InterruptedException
	{
		String nid=String.valueOf((long)sh.getRow(1).getCell(0).getNumericCellValue());
		dvr.findElement(By.xpath("//tr[@id='"+nid+"']//td[@class='actions']//a")).click();
		Thread.sleep(3000);
		dvr.findElement(By.xpath("//tr[@id='"+nid+"']//td[@class='capstone-grade']/span")).sendKeys(Keys.DELETE);
		dvr.findElement(By.xpath("//tr[@id='"+nid+"']//td[@class='capstone-grade']/span")).sendKeys(Keys.chord("F"));
		dvr.findElement(By.xpath("//tr[@id='"+nid+"']//a[@class='btnUpdate']")).click();
		Thread.sleep(4000);
		
		String nid1=String.valueOf((long)sh.getRow(2).getCell(0).getNumericCellValue());
		dvr.findElement(By.xpath("//tr[@id='"+nid1+"']//span[@title='edit']")).click();
		Thread.sleep(3000);
		dvr.findElement(By.xpath("//tr[@id='"+nid1+"']//span[contains(@id,'sp_cbt')]")).sendKeys(Keys.DELETE);
		dvr.findElement(By.xpath("//tr[@id='"+nid1+"']//span[contains(@id,'sp_cbt')]")).sendKeys(Keys.chord(Keys.chord("F")));
		dvr.findElement(By.xpath("//tr[@id='"+nid1+"']//span[@title='update']")).click();
		Thread.sleep(4000);

	}
}
