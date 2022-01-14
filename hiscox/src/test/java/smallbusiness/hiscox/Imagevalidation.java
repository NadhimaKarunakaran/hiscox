package smallbusiness.hiscox;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.time.Duration;
import java.util.List;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Imagevalidation {

	public static void main(String[] args) throws MalformedURLException, IOException, InterruptedException {
		// TODO Auto-generated method stub
	//	ChromeOptions options = new ChromeOptions();
//		options.addArguments("headless");
		System.setProperty("webdriver.chrome.driver", "/Users/nkarunakaran/Desktop/Selenium/chromedriver");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://www.hiscox.com/");
		Thread.sleep(2000);
		driver.findElement(By.cssSelector(".onetrust-close-btn-handler.onetrust-close-btn-ui.banner-close-button.onetrust-lg.ot-close-icon")).click();
		
		List<WebElement> imagelist = driver.findElements(By.xpath("//img"));
		System.out.println(imagelist.size());
		
		for(WebElement img:imagelist)
		{
			String url = img.getAttribute("src");
			HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
			 conn.setRequestMethod("GET");
			 conn.connect();
			int code = conn.getResponseCode();
			System.out.println("Response code for "+ url + " is " +code);
	}
		driver.close();
}
}
