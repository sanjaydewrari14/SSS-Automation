package com.excelsoft.testautomation;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_ABMS_TraineeGenerateCertificateBulk extends TC_ABMS_BaseClass 
{


	@Test
	public void generateCertificate() throws InterruptedException, IOException
	{
		try
		{
			explicit_wait(By.xpath("//a[@class='dropdown-toggle']//span[@class='grading-icon']"), 10);
			WebElement mainMenu = dvr.findElement(By.xpath("//a[@class='dropdown-toggle']//span[@class='grading-icon']"));
			act.moveToElement(mainMenu).moveToElement(dvr.findElement(By.xpath("//a[@href='/SSS_ABMS_3.1/public/certificate']"))).click().build().perform();
			dvr.findElement(By.xpath("//div[@id='s2id_cert_center_id']/a")).click();
			Thread.sleep(3000);
			dvr.findElement(By.xpath("//div[text()='"+prop.getProperty("abms_center_name")+"']")).click();
			Thread.sleep(2000);
			dvr.findElement(By.xpath("//div[@id='s2id_cert_qualification_id']/a")).click();
			explicit_wait(By.xpath("//div[text()='"+prop.getProperty("abms_intake_associate_Qualname")+"']"), 10);
			dvr.findElement(By.xpath("//div[text()='"+prop.getProperty("abms_intake_associate_Qualname")+"']")).click();
			Thread.sleep(3000);
			dvr.findElement(By.xpath("//div[@id='s2id_cert_intake_id']/a")).click();
			Thread.sleep(3000);
			dvr.findElement(By.xpath("//div[text()='"+prop.getProperty("abms_intake_name")+"']")).click();
			Thread.sleep(3000);
			dvr.findElement(By.xpath("//input[@value='SHOW ALL TRAINEES']")).click();
			Thread.sleep(6000);
			dvr.findElement(By.xpath("//input[@value='GENERATE CERTIFICATE']")).click();	
			Thread.sleep(6000);
			dvr.findElement(By.xpath("//input[@id='sendemail']")).sendKeys("testcert@yopmail.com");
			Thread.sleep(3000);
			dvr.findElement(By.xpath("//input[@value='SEND PASSWORD'][@id='submitemail']")).click();
			Thread.sleep(10000);
			Assert.assertEquals(dvr.findElement(By.xpath("//div[@id='sendSP']/span")).getText(), "Password to open the certificate document has been successfully sent to the provided email address.");
			dvr.findElement(By.xpath("//div[@class='fancybox-skin']//a[@title='Close']")).click();
			Thread.sleep(4000);
			captureScreenshot("generate_cert.png");
			act.moveToElement(dvr.findElement(By.xpath("//li[@id='header-user-dropdown']"))).moveToElement(dvr.findElement(By.xpath("//a[text()='Logout']"))).click().build().perform();
			Thread.sleep(3000);
			
		}
		catch(Exception e)
		{
			System.out.println("Error while generating certificate..");
			captureScreenshot("generatecertificate_err.png");
			Assert.fail(e.getMessage());

		}

		//	dvr.findElement(By.xpath("//input[@value='DOWNLOAD CERTIFICATE']")).click();





	}

}
