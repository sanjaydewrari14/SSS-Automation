package com.excelsoft.testautomation;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_ABMS_ResitCBTbooking extends TC_ABMS_BaseClass 

{

	@Test

	public void resitCBTbooking() throws InterruptedException, IOException
	{
		
		try
		{
			Thread.sleep(4000);
			WebElement mainMenu = dvr.findElement(By.xpath("//a[@class='dropdown-toggle']//span[@class='booking-icon']"));
			act.moveToElement(mainMenu).moveToElement(dvr.findElement(By.xpath("//a[text()='Book Assessment']"))).click().build().perform();
			dvr.findElement(By.xpath("//a[text()='ADD ASSESSMENT BOOKING']")).click();
			dvr.findElement(By.xpath("//span[@class='custom-radio resit']")).click();
			dvr.findElement(By.xpath("//div[@id='s2id_qualification']/a")).click();
			Thread.sleep(2000);
			dvr.findElement(By.xpath("//div[text()='"+prop.getProperty("abms_intake_associate_Qualname")+"']")).click();
			Thread.sleep(2000);
			dvr.findElement(By.xpath("//div[@id='s2id_intake_id']/a")).click();
			Thread.sleep(2000);
			dvr.findElement(By.xpath("//div[text()='"+prop.getProperty("abms_intake_name")+"']")).click();
			Thread.sleep(2000);
			dvr.findElement(By.xpath("//input[@id='assessment_start_date']")).sendKeys("");
			dvr.findElement(By.xpath("//input[@id='assessment_start_date']")).click();
			Thread.sleep(2000);

			String startdate=prop.getProperty("abms_center_cbt_startdate");
			String[] date = startdate.split("/");
			int startmonth=Integer.parseInt(date[1]);
			startmonth=startmonth-1;

			dvr.findElement(By.xpath("//td[@data-month='"+startmonth+"'][@data-year='"+date[2]+"']//a[text()='"+date[0]+"']")).click();
			Thread.sleep(2000);
			dvr.findElement(By.xpath("//div[@id='s2id_start_hour']/a")).click();
			Thread.sleep(2000);
			dvr.findElement(By.xpath("//div[text()='01']")).click();
			Thread.sleep(2000);
			dvr.findElement(By.xpath("//div[@id='s2id_start_minute']/a")).click();
			Thread.sleep(2000);
			dvr.findElement(By.xpath("//div[text()='00']")).click();
			dvr.findElement(By.xpath("//div[@id='s2id_end_hour']/a")).click();
			Thread.sleep(2000);
			dvr.findElement(By.xpath("//div[text()='04']")).click();
			Thread.sleep(2000);
			dvr.findElement(By.xpath("//div[@id='s2id_end_minute']/a")).click();
			Thread.sleep(2000);
			dvr.findElement(By.xpath("//div[text()='00']")).click();
			Thread.sleep(2000);



			dvr.findElement(By.xpath("//input[@id='assessment_end_date']")).sendKeys("");
			dvr.findElement(By.xpath("//input[@id='assessment_end_date']")).click();
			Thread.sleep(2000);

			String enddate=prop.getProperty("abms_center_cbt_enddate");
			String[] newenddate = enddate.split("/");
			int endmonth=Integer.parseInt(newenddate[1]);
			endmonth=endmonth-1;


			dvr.findElement(By.xpath("//td[@data-month='"+endmonth+"'][@data-year='"+newenddate[2]+"']//a[text()='"+newenddate[0]+"']")).click();
			Thread.sleep(2000);
			dvr.findElement(By.xpath("//input[@id='next_step']")).click();
			dvr.findElement(By.xpath("//a[text()='Select Trainees']")).click();
			dvr.switchTo().frame(dvr.findElement(By.xpath("//iframe[contains(@name,'fancybox-frame')]")));


			try
			{
				dvr.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
				while(dvr.findElement(By.xpath("//li[@class='enabled']/a[@class='next']")).isDisplayed())
				{
					dvr.findElement(By.xpath("//label[@for='all_check']")).click();
					dvr.findElement(By.xpath("//a[text()='Add To List']")).click();
					handleAlert();
					dvr.findElement(By.xpath("//li[@class='enabled']/a[@class='next']")).click();
					Thread.sleep(4000);


				}
			}
			catch(Exception e)
			{
				dvr.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				dvr.findElement(By.xpath("//label[@for='all_check']")).click();
				dvr.findElement(By.xpath("//a[text()='Add To List']")).click();
				handleAlert();
			}
			dvr.switchTo().defaultContent();
			explicit_wait(By.xpath("//div[@class='fancybox-skin']//a[@title='Close']"), 10);
			dvr.findElement(By.xpath("//div[@class='fancybox-skin']//a[@title='Close']")).click();
			Thread.sleep(3000);
			dvr.findElement(By.xpath("//input[@value='BOOK']")).click();
			Alert alr=dvr.switchTo().alert();
			alr.accept();

			dvr.switchTo().frame(dvr.findElement(By.xpath("//iframe[contains(@name,'fancybox-frame')]")));
			String suc_msg1=dvr.findElement(By.xpath("//ul[@class='status-popup']/li[1]")).getText().trim();
			captureScreenshot("cbt_booking_resit.png");
			Assert.assertEquals(suc_msg1, "Booking is created/updated successfully");
			captureScreenshot("cbt_booking_resit.png");
			dvr.switchTo().defaultContent();
			explicit_wait(By.xpath("//div[@class='fancybox-skin']//a[@title='Close']"), 10);
			dvr.findElement(By.xpath("//div[@class='fancybox-skin']//a[@title='Close']")).click();
			Thread.sleep(3000);

		}
		catch(Exception e)
		{
			System.out.println("Error while creating resit cbt booking");
			captureScreenshot("cbt_booking_resit_error.png");
			Assert.fail(e.getMessage());
		}

	}
}
