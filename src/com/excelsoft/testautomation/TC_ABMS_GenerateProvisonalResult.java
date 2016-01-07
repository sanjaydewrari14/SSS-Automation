package com.excelsoft.testautomation;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_ABMS_GenerateProvisonalResult extends TC_ABMS_BaseClass 
{

	@Test
	public void generateProvisonalResult() throws InterruptedException, IOException
	{
		try
		{
			Thread.sleep(5000);
			explicit_wait(By.xpath("//a[@class='dropdown-toggle']//span[@class='grading-icon']"), 10);
			WebElement mainMenu = dvr.findElement(By.xpath("//a[@class='dropdown-toggle']//span[@class='grading-icon']"));
			act.moveToElement(mainMenu).moveToElement(dvr.findElement(By.xpath("//a[text()='Publish Provisional Result']"))).click().build().perform();
			dvr.findElement(By.xpath("//div[@id='s2id_qualificationCode']/a")).click();
			explicit_wait(By.xpath("//div[text()='"+prop.getProperty("abms_intake_associate_Qualname")+"']"), 10);
			dvr.findElement(By.xpath("//div[text()='"+prop.getProperty("abms_intake_associate_Qualname")+"']")).click();
			Thread.sleep(3000);
			dvr.findElement(By.xpath("//div[@id='s2id_intakeName']/a")).click();
			Thread.sleep(3000);
			dvr.findElement(By.xpath("//div[text()='"+prop.getProperty("abms_intake_name")+"']")).click();
			dvr.findElement(By.xpath("//div[@id='s2id_centerName']/a")).click();
			Thread.sleep(3000);
			dvr.findElement(By.xpath("//div[text()='"+prop.getProperty("abms_center_name")+"']")).click();
			Thread.sleep(3000);
			dvr.findElement(By.xpath("//input[@value='FETCH MARKS']")).click();
			Thread.sleep(6000);
			((JavascriptExecutor)dvr).executeScript("window.scroll(0,700);");
			Thread.sleep(2000);
			captureScreenshot("provisonalresult_publish.png");
			explicit_wait(By.xpath("//input[@value='PUBLISH PROVISIONAL RESULT']"), 10);
			dvr.findElement(By.xpath("//input[@value='PUBLISH PROVISIONAL RESULT']")).click();
			Assert.assertEquals(dvr.findElement(By.xpath("//div[@class='success']")).getText(), "Provisional Result Published successfully");
			Thread.sleep(4000);
			act.moveToElement(dvr.findElement(By.xpath("//li[@id='header-user-dropdown']"))).moveToElement(dvr.findElement(By.xpath("//a[text()='Logout']"))).click().build().perform();
			Thread.sleep(3000);

		}



		catch(Exception e)
		{
			System.out.println("Error while generating provisonal result");
			captureScreenshot("Generate_provisonalresult_error.png");
			Assert.fail(e.getMessage());

		}


	}



}
