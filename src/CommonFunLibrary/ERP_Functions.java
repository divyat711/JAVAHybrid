package CommonFunLibrary;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ERP_Functions {
public static WebDriver driver;
//Launch URL in browser
public static String launchApp(String url)
{
	String res;
	System.setProperty("webdriver.chrome.driver", "E:\\OJT_divya\\Selenium_FrameWorks\\ERP_Accounting\\CommonDrivers\\chromedriver.exe");
	driver=new ChromeDriver();
	driver.get(url);
	driver.manage().window().maximize();
	if(driver.findElement(By.name("btnsubmit")).isDisplayed())
	{
		res="Application Launch Sucess";
	}
	else
	{
		res="Application Launch Fail";
	}
return res;
}
public static String verifyLogin(String uname, String password)
{
	String res;
	WebElement user=driver.findElement(By.name("username"));
	user.clear();
	user.sendKeys(uname);
	WebElement pass=driver.findElement(By.name("password"));
	pass.clear();
	pass.sendKeys(password);
	driver.findElement(By.id("btnsubmit")).click();
	if(driver.findElement(By.xpath("//li[@id='mi_logout']//a[contains(text(),'Logout')]")).isDisplayed())
	{
		res="Login Success";
	}
	else
	{
		res="Login Fail";
	}
	return res;
}
public static String verifySupplierCretion(String sname, String Addr, String city, String Country, 
		String cperson, String Pnum, String Email, String Mnum, String Notes) throws Throwable
{
    String res=""; 
	driver.findElement(By.linkText("Suppliers")).click();
	Thread.sleep(3000);
	driver.findElement(By.xpath("//div[@class='panel-heading ewGridUpperPanel']//span[@class='glyphicon glyphicon-plus ewIcon']")).click();
    Thread.sleep(3000);
    String Expval=driver.findElement(By.xpath("//input[@id='x_Supplier_Number']")).getAttribute("value");
    driver.findElement(By.xpath("//input[@id='x_Supplier_Name']")).sendKeys(sname);
    driver.findElement(By.xpath("//textarea[@id='x_Address']")).sendKeys(Addr);
    driver.findElement(By.xpath("//input[@id='x_City']")).sendKeys(city);
    driver.findElement(By.xpath("//input[@id='x_Country']")).sendKeys(Country);
    driver.findElement(By.xpath("//input[@id='x_Contact_Person']")).sendKeys(cperson);
    driver.findElement(By.xpath("//input[@id='x_Phone_Number']")).sendKeys(Pnum);
    driver.findElement(By.xpath("//input[@id='x__Email']")).sendKeys(Email);
    driver.findElement(By.xpath("//input[@id='x_Mobile_Number']")).sendKeys(Mnum);
    driver.findElement(By.xpath("//textarea[@id='x_Notes']")).sendKeys(Notes);
    driver.findElement(By.name("btnAction")).sendKeys(Keys.ENTER);
    Thread.sleep(3000);
    driver.findElement(By.xpath("//button[contains(text(),'OK!')]")).click();
    Thread.sleep(3000);
    driver.findElement(By.xpath("/html[1]/body[1]/div[17]/div[2]/div[1]/div[4]/div[2]/button[1]")).click();
    Thread.sleep(3000);
    if(!driver.findElement(By.xpath("//input[@id='psearch']")).isDisplayed())
    	driver.findElement(By.xpath("//span[@class='glyphicon glyphicon-search ewIcon']")).click();
    	driver.findElement(By.xpath("//input[@id='psearch']")).clear();
    	driver.findElement(By.xpath("//input[@id='psearch']")).sendKeys(Expval);
    	driver.findElement(By.xpath("//button[@id='btnsubmit']")).click();
    	Thread.sleep(3000);
    	String Actval=driver.findElement(By.xpath("//table[@id='tbl_a_supplierslist']/tbody/tr[1]/td[6]/div/span/span")).getText();
    	System.out.println(Expval+"   "+Actval);
    	if(Expval.equals(Actval))
    	{
    		res="pass";
    	}
    	else
    	{
    		res="fail";
    	}
 return res;   
}
public static void closeApp()
{
	driver.close();
}
/*public static void main(String[] args) throws Throwable
{
	String launch=ERP_Functions.launchApp("http://webapp.qedge.com");
	System.out.println(launch);
	String login=ERP_Functions.verifyLogin("admin", "master");
	System.out.println(login);
	String sc=ERP_Functions.verifySupplierCretion("dd", "eee", "bajf", "adfhkhf", "dfbhjhdf", "24563", "g@gmal.com", "658469", "fhaeuihuefh");
    System.out.println(sc);
	ERP_Functions.closeApp();
}*/
}