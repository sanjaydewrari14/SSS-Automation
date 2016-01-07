package com.excelsoft.testautomation;

import java.io.IOException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_ABMS_admin_CreateIntake_AssociateQual extends TC_ABMS_BaseClass 
{

	@Test
	public void createIntake() throws InterruptedException, IOException
	{
		try
		{
			act.moveToElement(dvr.findElement(By.xpath("//a[@class='dropdown-toggle']//span[@class='qualification-icon']"))).moveToElement(dvr.findElement(By.xpath("//a[text()='Intake']"))).click().build().perform();
			dvr.findElement(By.xpath("//a[text()='CREATE']")).click();
			dvr.findElement(By.xpath("//input[@name='intake_name']")).sendKeys(prop.getProperty("abms_intake_name"));
			dvr.findElement(By.xpath("//textarea[@name='description']")).sendKeys("TestDescription");
			dvr.findElement(By.xpath("//input[@name='start_date']")).sendKeys("");
			dvr.findElement(By.xpath("//input[@name='start_date']")).click();
			String startdate=prop.getProperty("abms_intake_startdate");
			String[] split = startdate.split("/");
			int month=Integer.parseInt(split[1]);
			int newmonth=month-1;
			dvr.findElement(By.xpath("//td[@data-month='"+newmonth+"'][@data-year='"+split[2]+"']//a[text()='"+split[0]+"']")).click();


			dvr.findElement(By.xpath("//input[@name='end_date']")).sendKeys("");
			dvr.findElement(By.xpath("//input[@name='end_date']")).click();

			String enddate=prop.getProperty("abms_intake_enddate");
			String[] date = enddate.split("/");
			int endmonth=Integer.parseInt(date[1]);
			int endnewmonth=endmonth-1;


			dvr.findElement(By.xpath("//td[@data-month='"+endnewmonth+"'][@data-year='"+date[2]+"']//a[text()='"+date[0]+"']")).click();
			dvr.findElement(By.xpath("//input[@value='PUBLISH']")).click();
			Assert.assertEquals(dvr.findElement(By.xpath("//div[@class='success']")).getText(), "Intake added successfully");
            Thread.sleep(4000);
            captureScreenshot("Create_Intake.png");
			act.moveToElement(dvr.findElement(By.xpath("//a[@class='dropdown-toggle']//span[@class='qualification-icon']"))).moveToElement(dvr.findElement(By.xpath("//a[text()='Manage Qualification']"))).click().build().perform();
			dvr.findElement(By.xpath("//input[@name='search_box']")).sendKeys(prop.getProperty("abms_intake_associate_Qualname"));
			dvr.findElement(By.xpath("//span[@class='search-holder']//input[@name='searchbutton']")).click();
			Thread.sleep(6000);
			dvr.findElement(By.xpath("//span[@class='edit-icon']")).click();
			dvr.findElement(By.xpath("//a[@href='#tabs1-Intakes']")).click();
			Thread.sleep(4000);
			dvr.findElement(By.xpath("//div[@id='s2id_intake_name']/a")).click();
			Thread.sleep(3000);
			dvr.findElement(By.xpath("//div[text()='"+prop.getProperty("abms_intake_name")+"']")).click();
			Thread.sleep(3000);
			dvr.findElement(By.xpath("//input[@value='ADD INTAKE']")).click();
			Thread.sleep(5000);
			((JavascriptExecutor)dvr).executeScript("window.scroll(0,0)");
			Thread.sleep(3000);
			dvr.findElement(By.xpath("//input[@value='PUBLISH']")).click();

			Alert alr=dvr.switchTo().alert();
			alr.accept();
			Assert.assertEquals(dvr.findElement(By.xpath("//div[@class='success']")).getText(), "Qualification updated successfully.");
          
		}
		catch(Exception e)
		{
			System.out.println("Error while creating intake..");
			captureScreenshot("createIntake_err.png");
			Assert.fail(e.getMessage());
			

		}

	}
}
