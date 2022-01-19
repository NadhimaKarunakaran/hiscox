package resources;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class baseclass {

	public WebDriver driver;
	public Properties prop;
	public WebDriver browserdetails() throws IOException
	{
		
		prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "//src//main//java//resources//ncaadata.properties");
		prop.load(fis);
		
		String browser = prop.getProperty("browser");
		if(browser.contains("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"//src//main//java//resources//chromedriver");
			driver = new ChromeDriver();
		}
		else if(browser.contains("firefox"))
		{
			 System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"//src//main//java//resources//geckodriver"); 
			 driver = new FirefoxDriver();
		}
	
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
		
	}
	
}
