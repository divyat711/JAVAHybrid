package DriverFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import CommonFunLibrary.ERP_Functions;
import Utilities.ExcelFileUtil;

public class DataDrivenFramework {
WebDriver driver;
@BeforeTest
public void setUp()
{
	String launch=ERP_Functions.launchApp("http://webapp.qedge.com");
	System.out.println(launch);
	String login=ERP_Functions.verifyLogin("admin", "master");
	System.out.println(login);
}
@Test
public void supplierCre() throws Throwable
{
	ExcelFileUtil xl=new ExcelFileUtil();
	int rc=xl.rowCount("Supplier");
	int cc=xl.colCount("Supplier");
	System.out.println(rc+"    "+cc);
	for(int i=1;i<=rc;i++)
	{
		String sname=xl.getCellData("Supplier",i,0);
		String Addr=xl.getCellData("Supplier",i,1);
		String city=xl.getCellData("Supplier",i,2);
		String Country=xl.getCellData("Supplier",i,3);
		String cperson=xl.getCellData("Supplier",i,4);
		String Pnum=xl.getCellData("Supplier",i,5);
		String Email=xl.getCellData("Supplier",i,6);
		String Mnum=xl.getCellData("Supplier",i,7);
		String Notes=xl.getCellData("Supplier",i,8);
		String result=ERP_Functions.verifySupplierCretion(sname, Addr, city, Country, cperson, 
				Pnum, Email, Mnum, Notes);
		xl.setCellData("Supplier",i,9,result);
		
	}
	
}
@AfterTest
public void tearDown()
{
	ERP_Functions.closeApp();
}
}

