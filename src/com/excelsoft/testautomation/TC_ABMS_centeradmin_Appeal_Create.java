package com.excelsoft.testautomation;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_ABMS_centeradmin_Appeal_Create extends TC_ABMS_BaseClass 
{

	@Test
	public void createAppeal() throws InterruptedException, InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, FileNotFoundException, IOException
	{
		try
		{
			String appealid = null;
			String dburl ="jdbc:sqlserver://STAGING7\\MSSQLSERVER1; DatabaseName=sss-abms-current-new";
			// Load Microsoft SQL Server JDBC driver
			String dbClass = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
			Class.forName(dbClass).newInstance();
			//Get connection to DB
			Connection con = DriverManager.getConnection(dburl, "sa", "imfinity!123");
			//Create Statement
			Statement stmt = (Statement) con.createStatement();
			// method which returns the requested information as rows of data
			ResultSet rs_appeal=stmt.executeQuery("select max(id) from appeal");
			if(rs_appeal.next())
			{
				System.out.println(rs_appeal.getString(1));
				appealid=rs_appeal.getString(1);
			}


			Thread.sleep(5000);
			explicit_wait(By.xpath("//a[@class='dropdown-toggle']//span[@class='grading-icon']"), 10);
			WebElement mainMenu = dvr.findElement(By.xpath("//a[@class='dropdown-toggle']//span[@class='grading-icon']"));
			act.moveToElement(mainMenu).moveToElement(dvr.findElement(By.xpath("//a[text()='Manage Appeal']"))).click().build().perform();
			WebElement createbtn = dvr.findElement(By.xpath("//a[@class='dropdown-toggle big-btn green-btn']//span[@class='caret']"));
			act.moveToElement(createbtn).moveToElement(dvr.findElement(By.xpath("//a[contains(text(),'Create Appeal for Standard Qualification')]"))).click().build().perform();
			dvr.findElement(By.xpath("//textarea[@id='appeal_description']")).sendKeys("appeal description");
			dvr.findElement(By.xpath("//div[@id='s2id_appeal_qualification']/a")).click();
			Thread.sleep(2000);
			dvr.findElement(By.xpath("//div[text()='"+prop.getProperty("abms_intake_associate_Qualname")+"']")).click();
			Thread.sleep(3000);
			dvr.findElement(By.xpath("//div[@id='s2id_appeal_intake_id']")).click();
			Thread.sleep(2000);
			try
			{
			dvr.findElement(By.xpath("//div[@id='s2id_appeal_intake_id']")).click();
			Thread.sleep(3000);
			}
			catch(Exception e)
			{
				
			}
			dvr.findElement(By.xpath("//div[text()='"+prop.getProperty("abms_intake_name")+"']")).click();
			Thread.sleep(2000);
			dvr.findElement(By.xpath("//input[@id='appeal_next']")).click();
			Thread.sleep(5000);
			dvr.findElement(By.xpath("//div[@id='s2id_appeal_national_id']/a")).click();
			Thread.sleep(2000);
			String nid=String.valueOf((long)sh.getRow(1).getCell(0).getNumericCellValue());
			dvr.findElement(By.xpath("//div[text()='"+nid+"']")).click();
			Thread.sleep(2000);
			dvr.findElement(By.xpath("//input[@id='recommended_marks']")).sendKeys("B");
			dvr.findElement(By.xpath("//textarea[@id='comments']")).sendKeys("Testcomments");
			dvr.findElement(By.xpath("//input[@value='Add To List']")).click();
			Thread.sleep(3000);
			((JavascriptExecutor)dvr).executeScript("window.scroll(0,0);");
			Thread.sleep(2000);
			dvr.findElement(By.xpath("//span[@class='custom-radio capstone']")).click();
			Thread.sleep(4000);
			String nid1=String.valueOf((long)sh.getRow(2).getCell(0).getNumericCellValue());
			dvr.findElement(By.xpath("//div[@id='s2id_appeal_national_id']/a")).click();
			Thread.sleep(2000);
			dvr.findElement(By.xpath("//div[text()='"+nid1+"']")).click();
			Thread.sleep(2000);
			dvr.findElement(By.xpath("//input[@id='recommended_marks']")).sendKeys("B");
			dvr.findElement(By.xpath("//textarea[@id='comments']")).sendKeys("Testcomments");
			dvr.findElement(By.xpath("//input[@value='Add To List']")).click();
			Thread.sleep(2000);
			((JavascriptExecutor)dvr).executeScript("window.scroll(0,0);");
			Thread.sleep(2000);
			dvr.findElement(By.xpath("//input[@id='publishappeal']")).click();
			Alert alr=dvr.switchTo().alert();
			alr.accept();
			Assert.assertEquals(dvr.findElement(By.xpath("//div[@class='success']")).getText(), "Appeal is published/updated successfully");
			Thread.sleep(2000);
			int nintappeal = 0;
			ResultSet rs_appealnew=stmt.executeQuery("select max(id) from appeal");
			if(rs_appealnew.next())
			{
				System.out.println(rs_appealnew.getString(1));
				String nappealid=rs_appealnew.getString(1);
				nintappeal=Integer.parseInt(nappealid);			
			}

			if(nintappeal>Integer.parseInt(appealid))
			{
				act.moveToElement(dvr.findElement(By.xpath("//li[@id='header-user-dropdown']"))).moveToElement(dvr.findElement(By.xpath("//a[text()='Logout']"))).click().build().perform();
				Thread.sleep(3000);
				login("abmsadmin");
				Thread.sleep(6000);
				explicit_wait(By.xpath("//a[@class='dropdown-toggle']//span[@class='grading-icon']"), 10);
				WebElement mainMenun = dvr.findElement(By.xpath("//a[@class='dropdown-toggle']//span[@class='grading-icon']"));
				act.moveToElement(mainMenun).moveToElement(dvr.findElement(By.xpath("//a[text()='Manage Appeals']"))).click().build().perform();

				dvr.findElement(By.xpath("//a[contains(text(),'"+nintappeal+"')]")).click();
				((JavascriptExecutor)dvr).executeScript("window.scroll(0,700);");
				Thread.sleep(2000);

				dvr.findElement(By.xpath("//table[@id='table_trainee']//tr[2]/td[6]/div[contains(@id,'s2id_select')]/a")).click();
				Thread.sleep(2000);
				dvr.findElement(By.xpath("//div[text()='Reject']")).click();
				Thread.sleep(2000);
				dvr.switchTo().frame(dvr.findElement(By.xpath("//div[@class='fancybox-skin']//iframe[@class='fancybox-iframe']")));
				Thread.sleep(1000);
				explicit_wait(By.xpath("//input[@value='Save']"), 10);
				dvr.findElement(By.xpath("//input[@value='Save']")).click();
				Thread.sleep(2000);
				dvr.findElement(By.xpath("//input[@value='submit']")).click();

				Assert.assertEquals(dvr.findElement(By.xpath("//div[@class='success']")).getText(), "Appeal is published/updated successfully");

				int update_appealtrstatus = stmt.executeUpdate("update appeal_trainee set new_marks=3, grade_marks='B' where appeal_id='"+nintappeal+"' and status='conditionally accepted'");
				if(update_appealtrstatus>0)
				{
					System.out.println("Appeal marks entered successfully");

				}

				int update_appealstatus = stmt.executeUpdate("update appeal set appeal_status='marks updated' where id='"+nintappeal+"'");
				if(update_appealstatus>0)
				{
					System.out.println("Appeal status entered successfully");

				}

				//			act.moveToElement(dvr.findElement(By.xpath("//li[@id='header-user-dropdown']"))).moveToElement(dvr.findElement(By.xpath("//a[text()='Logout']"))).click().build().perform();
				//			Thread.sleep(3000);
				//			login("centeradmin");
				//			Thread.sleep(7000);
				//			explicit_wait(By.xpath("//a[@class='dropdown-toggle']//span[@class='grading-icon']"), 10);
				//			WebElement nmainMenu = dvr.findElement(By.xpath("//a[@class='dropdown-toggle']//span[@class='grading-icon']"));
				//			act.moveToElement(nmainMenu).moveToElement(dvr.findElement(By.xpath("//a[text()='Manage Appeal']"))).click().build().perform();
				//			
				//			dvr.findElement(By.xpath("//a[contains(text(),'"+nintappeal+"')]")).click();
				//			dvr.findElement(By.xpath("//input[@value='submit for final review']")).click();
				//			Thread.sleep(10000);
				//			act.moveToElement(dvr.findElement(By.xpath("//li[@id='header-user-dropdown']"))).moveToElement(dvr.findElement(By.xpath("//a[text()='Logout']"))).click().build().perform();
				//			Thread.sleep(3000);
				//			
				//			login("abmsadmin");
				//			Thread.sleep(6000);
				//			explicit_wait(By.xpath("//a[@class='dropdown-toggle']//span[@class='grading-icon']"), 10);
				//			WebElement mainMenum = dvr.findElement(By.xpath("//a[@class='dropdown-toggle']//span[@class='grading-icon']"));
				//			act.moveToElement(mainMenum).moveToElement(dvr.findElement(By.xpath("//a[text()='Manage Appeals']"))).click().build().perform();
				//			
				dvr.findElement(By.xpath("//a[contains(text(),'"+nintappeal+"')]")).click();
				Thread.sleep(3000);
				dvr.findElement(By.xpath("//input[@value='submit']")).click();
				Assert.assertEquals(dvr.findElement(By.xpath("//div[@class='success']")).getText(), "Appeal is published/updated successfully");
				Thread.sleep(4000);
				captureScreenshot("appealcompleted.png");




			}



		}
		catch(Exception e)
		{
			System.out.println("Error creating appeal..");
			captureScreenshot("create_appeal_err.png");
			Assert.fail(e.getMessage());

		}

	}
}
