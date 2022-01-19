package Selenium.Selpra;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import resources.baseclass;


public class homepage extends baseclass{

	public WebDriver driver;
	
	@BeforeTest
	public void startup() throws IOException
	{
		driver = browserdetails();
	}
	
	@Test
	public void homepageclick()
	{
		
		driver.get(prop.getProperty("url"));
		
		
	}
	
	
	
}
