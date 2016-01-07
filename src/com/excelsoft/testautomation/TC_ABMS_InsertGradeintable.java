package com.excelsoft.testautomation;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_ABMS_InsertGradeintable extends TC_ABMS_BaseClass
{
	@Test
	public void insertdata() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, FileNotFoundException, IOException
	{
		try
		{
			String cbtbookingid = null;
			String capstonebookingid=null;
			wb=new XSSFWorkbook(new FileInputStream("../SSS_ABMS_Automation/inputs/Cohort_Trainees.xlsx"));
			sh=wb.getSheet("Sheet1");

			String dburl ="jdbc:sqlserver://STAGING7\\MSSQLSERVER1; DatabaseName=sss-abms-current-new";
			// Load Microsoft SQL Server JDBC driver
			String dbClass = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
			Class.forName(dbClass).newInstance();
			//Get connection to DB
			Connection con = DriverManager.getConnection(dburl, "sa", "imfinity!123");
			//Create Statement
			Statement stmt = (Statement) con.createStatement();
			// method which returns the requested information as rows of data
			ResultSet rs_bookingcbt=stmt.executeQuery("select max(id) from assessment_bookings where booking_type_label='cbt'");
			if(rs_bookingcbt.next())
			{
				System.out.println(rs_bookingcbt.getString(1));
				cbtbookingid=rs_bookingcbt.getString(1);
			}

			ResultSet rs_bookingcapstone=stmt.executeQuery("select max(id) from assessment_bookings where booking_type_label='capstone'");

			if(rs_bookingcapstone.next())
			{
				System.out.println(rs_bookingcapstone.getString(1));
				capstonebookingid=rs_bookingcapstone.getString(1);
			}




			int result = stmt.executeUpdate("update assessment_bookings set status='completed', result_status='fetched' where id="+cbtbookingid+"");

			if(result>0)
			{
				System.out.println("CBT Booking record updated in database");
			}


			int result1 = stmt.executeUpdate("update assessment_bookings set status='completed', result_status='fetched' where id ="+capstonebookingid+"");

			if(result1>0)
			{
				System.out.println("capstone Booking record updated in database");
			}





			int lastrow=sh.getLastRowNum();

			String traineeid = null;
			for(int i=1; i<=lastrow; i++)
			{

				rw=sh.getRow(i);
				cell=rw.getCell(0);
				String nid=String.valueOf((long)(cell.getNumericCellValue()));
				System.out.println(nid);

				ResultSet rs_traineeid=stmt.executeQuery("select id from trainee where national_id='"+nid+"'");

				if(rs_traineeid.next())
				{
					System.out.println(rs_traineeid.getString(1));
					traineeid=rs_traineeid.getString(1);


				}


				int result_score_la = stmt.executeUpdate("insert into trainee_scores_la values ('"+traineeid+"','"+capstonebookingid+"','3','2','3.5','4',getdate(),'1')");
				if(result_score_la>0)
				{
					System.out.println("Trainee "+nid+" record inserted successfully for capstone");


				}

				int result_score_evolve = stmt.executeUpdate("insert into trainee_scores_evolve values ('"+traineeid+"','"+cbtbookingid+"','40','60','paper1', getdate(),'1')");
				if(result_score_evolve>0)
				{
					System.out.println("Trainee "+nid+" record inserted successfully for cbt");


				}
			}
		}
		catch(Exception e)
		{
			System.out.println("Error while inserting grade into table..");
			Assert.fail(e.getMessage());

		}
}
public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, FileNotFoundException, SQLException, IOException 
{
	TC_ABMS_InsertGradeintable tgi=new TC_ABMS_InsertGradeintable();
	tgi.insertdata();

}






}
