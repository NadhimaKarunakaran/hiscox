package resoures;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class baseclass {
	
	public void initializedriver() throws IOException
	{
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "//src//main//java//resources//data.properties");
		prop.load(fis);
		
		String browsername = System.getProperty("browser");
		if(browsername.contains("chrome"))
		{
			
			
			
			
			
		}
		
		
		
	}
	
	

}
