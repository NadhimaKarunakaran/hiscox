package smallbusiness.hiscox;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Properties;
import static org.openqa.selenium.support.locators.RelativeLocator.*;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import org.testng.reporters.Files;

import com.google.common.base.Verify;


public class Smallbusiness {
	
	
	public static void main(String[] args) throws InterruptedException, IOException, ParseException {
		// TODO Auto-generated method stub
		
		//------*--------- giving the Chrome Driver path ------------------
		System.setProperty("webdriver.chrome.driver", "/Users/nkarunakaran/Desktop/Selenium/chromedriver");
		
		//------*--------- invoked Chrome browser ------------------
		WebDriver driver = new ChromeDriver();
	
		//------*--------- Used implicit wait for synchronization ------------------
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//------*--------- Used data.properties to manage the datas to avoid hard coding ------------------
		Properties prop = new Properties();
		FileInputStream is = new FileInputStream(System.getProperty("user.dir") + "//src//main//java//resoures//data.properties");
		prop.load(is);
		
		//------*--------- Got url from properties file and initialized it ------------------
		driver.get(prop.getProperty("url"));
		
		//------*--------- Cookie popup is handled in try/catch block ------------------
		try {
			driver.findElement(By.cssSelector(".onetrust-close-btn-handler.onetrust-close-btn-ui.banner-close-button.onetrust-lg.ot-close-icon")).click();
		}
		catch(Exception e)
		{
			System.out.println("No such window opened");
		}
		
		//------*--------- Collected all the states as a list  ------------------

		driver.findElement(By.id("select2--container")).click();
		List<WebElement> state = driver.findElements(By.cssSelector(".select2-results__option"));
		
		//------*--------- Used Enhanced for loop to validate each and every state and once the desired state is found it will be selected ------------------
		for(WebElement st:state)
		{
			if(st.getText().equalsIgnoreCase(prop.getProperty("state")))
			{
				st.click();
		//------*--------- loop will break if the state is found ------------------

				break;
			}
		}
		
		//------*--------- Entered the zip code ------------------
		driver.findElement(By.id("zip_code")).sendKeys("33647");
		
		//------*--------- Clicked get quote button ------------------
		driver.findElement(By.cssSelector("button.button.button--primary.button--large.quote-GTM")).click();
		
		//------*--------- Verified control landed in the correct page by grabbing some text  ------------------
		String pagevalidation = driver.findElement(By.xpath("//div[@osviewid='PAI_1286465_OT_205_OI_1_BI_1244865_CI_16851765_Label']")).getText();
		Assert.assertEquals("Business type and start date", pagevalidation);
		
		//------*--------- Verified the correct state is selected by displaying it in the output ------------------
		System.out.println(driver.findElement(By.xpath("//a[@osviewid='PAI_1286465_OT_205_OI_1_BI_1244865_CI_16852165']")).getText());
		
		//------*--------- Clicked profession dropdown ------------------
		driver.findElement(By.xpath("//input[@osviewid='PAI_1286465_OT_205_OI_1_BI_1244865_CI_16852265']")).click();
		
		//------*--------- Used Explicit wait to wait till the pop up is displayed fully ------------------
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(2));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("ul[aria-hidden='false']")));
		
		//------*--------- Collected all the professions as a list ------------------
		List<WebElement> options = driver.findElements(By.cssSelector(".x-boundlist-item"));
	
		//------*--------- Again used Enhanced for loop to validate all the profession ------------------
		//------*--------- profession is sent from properties file ------------------
		for(WebElement option:options)
		{
			if(option.getText().equalsIgnoreCase(prop.getProperty("profession")))
				{
				option.click();
				//------*--------- loop breaks when the selection is found ------------------
				break;
				}
		
		}
		//------*--------- Used the same logic as above for choose ownership ------------------
	driver.findElement(By.xpath("//input[@osviewid='PAI_1286465_OT_205_OI_1_BI_1244865_CI_16860665']")).click();
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("ul[aria-hidden='false']")));
	List<WebElement> owndership = driver.findElements(By.cssSelector(".x-boundlist-item"));
	for(WebElement own:owndership)
	{
		if(own.getText().equalsIgnoreCase(prop.getProperty("ownership")))
		{
			own.click();
			break;
		}
		
	}
	
	//------*--------- Clicked data dropdown ------------------
	driver.findElement(By.xpath("//input[@osviewid='PAI_1286465_OT_205_OI_1_BI_1244865_CI_16852665']")).click();
	
	//------*--------- Clicked month again to select the date based on the input in properties file ------------------
	driver.findElement(By.id("splitbutton-1114-btnWrap")).click();
	
	//------*--------- Collected the months as a list and used enhanced for loop to choose the correct month and year ------------------
	List<WebElement> month = driver.findElements(By.cssSelector("div[class='x-monthpicker-item x-monthpicker-month']"));

	for(WebElement mon:month)
	{
		if(mon.getText().equalsIgnoreCase(prop.getProperty("month")))
	{
			
			mon.click();
			break;
		}
		
	}
	List<WebElement> year = driver.findElements(By.xpath("//div/div/div/div[2]/div[@class='x-monthpicker-item x-monthpicker-year']"));
	for(WebElement yr:year)
	{
		if(yr.getText().equalsIgnoreCase(prop.getProperty("year")))
		{	
	
			yr.click();
			break;
		}
	}
	//------*--------- Once month and year is choosen ok button is clicked ------------------
	driver.findElement(By.id("button-1117-btnInnerEl")).click();
	
	//------*--------- Collected active dates based on that particular month and storied it as a list ------------------
	List<WebElement> day = driver.findElements(By.xpath("//tr/td[@class='x-datepicker-active x-datepicker-cell']"));
	
	//------*--------- Used Enhanced for loop and selected the specific date ------------------
	for (WebElement d:day)
	{
		if(d.getText().equalsIgnoreCase(prop.getProperty("day")))
		{
			
			d.click();
			break;
		}
	}	
	
	driver.findElement(By.cssSelector("span[data-ref='btnInnerEl']")).click();
	
//--------------------* Randomly verified few things in coverage page---------
	
	driver.findElement(By.cssSelector("#hxboxedcheckbox-1172-displayEl")).click();
	
	
	driver.findElement(By.id("hxboxedcheckbox-1183-displayEl")).click();
	
	
	System.out.println(driver.findElement(By.id("hxboxedcheckbox-1183-displayEl")).isSelected());
	
	
	System.out.println(driver.findElement(By.cssSelector("a[osviewid='PAI_1286865_OT_5_OI_1_BI_1413865_CI_18106265']")).getText());
	
	
	System.out.println(driver.findElement(By.cssSelector("#hxboxedcheckbox-1172-dynLabelEl")).getText());
	
	
	driver.findElement(By.cssSelector("span[data-ref='btnInnerEl']")).click();
	
	System.out.println(driver.findElement(By.id("hxboxedcheckbox-1183-displayEl")).isEnabled());
	
	System.out.println(driver.findElement(By.xpath("//div[@osviewid='PAI_1287165_OT_205_OI_1_BI_1245665_CI_17250065_Label']")).getText());
	
	//--------------------* In Business basics page captured the dynamically changing value using contains method in xpath---------
	driver.findElement(By.xpath("(//input[contains(@class,'x-form-field x-form-text x-form-text-HxTextBox-LL ')])[1]")).sendKeys("firstname");
	
	driver.findElement(By.xpath("(//input[contains(@class,'x-form-field x-form-text x-form-text-HxTextBox-LL ')])[2]")).sendKeys("secondname");
	
	driver.findElement(By.xpath("//span[contains(@class,'x-form-field x-form-checkbox x-form-checkbox-default x-form-cb x-form-cb-default ')]")).click();
	
	driver.findElement(By.xpath("(//input[contains(@class,'x-form-field x-form-text x-form-text-HxTextBox-LL ')])[3]")).sendKeys("3454345432");
	
	//--------------------* tried relative xpath to locate few other elements in the page ---------
	driver.findElement(By.xpath("//table/tbody/tr[15]/td/div/div/div/div/div/div/div/div/div/input")).sendKeys("4563456543");
	driver.findElement(By.xpath("//table/tbody/tr[16]/td/div/div/div/div/div/div/div/div/div/div/div/div/div/div/input")).sendKeys("nadhi@gmail.com");
	driver.findElement(By.xpath("//table/tbody/tr[17]/td/div/div/div/div/div/div/div/div/div/input")).click();
	
	//--------------------* Again use same enhanced for loop logic to choose some random dates---------
	//--------------------* This time just hardcoded the date---------
	driver.findElement(By.cssSelector(".x-btn-inner.x-btn-inner-default-small")).click();

	List<WebElement> months = driver.findElements(By.cssSelector(".x-monthpicker-item.x-monthpicker-month"));
	
	for(WebElement mm :months)
	{
		if(mm.getText().equalsIgnoreCase("Feb"))
				{
			mm.click();
			break;
				}
		
	}
	List<WebElement> years = driver.findElements(By.cssSelector(".x-monthpicker-item.x-monthpicker-year"));
	for(WebElement yyyy :years)
	{
		if(yyyy.getText().equalsIgnoreCase("2022"))
		{
			yyyy.click();
			break;
			
		}
		
	}
	driver.findElement(By.id("button-1320-btnInnerEl")).click();
	
	List<WebElement> dd = driver.findElements(By.cssSelector(".x-datepicker-active.x-datepicker-cell"));
	for(WebElement d:dd)
	{
		if(d.getText().equalsIgnoreCase("17"))
				{
			d.click();
			break;
				}
		
	}
	driver.findElement(By.xpath("//table/tbody/tr[18]/td/div/div/div/div/div/div/div/div/div/input")).click();
	List<WebElement> dba = driver.findElements(By.cssSelector("li[role='option']"));

	for(WebElement ba:dba)
	{
		if(ba.getText().equalsIgnoreCase("DBA (Doing Business As)"))
		{
			ba.click();
			break;
		}
	}
	driver.findElement(By.xpath("//table/tbody/tr[19]/td/div/div/div/div/div/div/div/div/div/input")).sendKeys("testing");
	
	driver.findElement(By.xpath("//table/tbody/tr[20]/td/div/div/div/div/div/div[2]/div/div/div/div/div/div[2]/div/table/tbody/tr/td[2]/div/div/div/label")).click();
	
	driver.findElement(By.cssSelector("#osCellButton-50365-btnInnerEl")).click();
	WebElement refcode = driver.findElement(By.cssSelector("div[osviewid='PAI_1174565_OT_383005_OI_1_BI_1184765_CI_16597065_Label']"));
	String confirmationcode = driver.findElement(By.cssSelector("div[osviewid='PAI_1174565_OT_383005_OI_1_BI_1184765_CI_16597065_Label']")).getText();
	
	//--------------------* Once the form is completely successfully printed the confirmation ref code in the output--------
	System.out.println(confirmationcode);
	
	//--------------------* Also grabbed that ref code as a screenshot and saved it --------
	File file = refcode.getScreenshotAs(OutputType.FILE);
	FileUtils.copyFile(file, new File("demo.png"));
	driver.quit();
	}
}
