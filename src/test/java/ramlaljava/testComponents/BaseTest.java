package ramlaljava.testComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import ramlaljava.pageObject.LandingPage;

public class BaseTest {

	public WebDriver d; //declare globally
	public LandingPage lp; //declare globally
	
	public WebDriver initializeDriver() throws IOException  //initialize driver
	{	
		
		//use properties class to choose between browser
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\ramlaljava\\resources\\GlobalData.properties");
		prop.load(fis);
		String browserName = System.getProperty("browser")!= null ? System.getProperty("browser"): prop.getProperty("browser");
		
		if(browserName.contains("chrome"))
		{		
			
		ChromeOptions coptions = new ChromeOptions(); //use chromeOption to run headless mode
		WebDriverManager.chromedriver().setup();
		
		if(browserName.contains("headless")) //setup if only requested to run in headless mode
		coptions.addArguments("headless");
		
		d = new ChromeDriver(coptions); //input
		d.manage().window().setSize(new Dimension(1440,900)); //since in headless mode by default it opens in minimize mode
		}
		else if(browserName.equalsIgnoreCase("firefox"))
		{
		WebDriverManager.firefoxdriver().setup();
		d= new FirefoxDriver();
		}
		else if(browserName.equalsIgnoreCase("edge"))
		{
		WebDriverManager.edgedriver().setup();
		d= new EdgeDriver();
		}
		
		d.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		d.manage().window().maximize();	
		
		return d;  //this method return driver
	}
	
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException
	{
		//read json to string
		String jsonContent = FileUtils.readFileToString(new File(filePath)
		, StandardCharsets.UTF_8);  //required to provide encoding format as second argument as per latest update
		
		//convert json string to hashMap - using Jackson Databind
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String,String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>(){});
		return data;		
	}
	
	//Taking screenshot only when testCase failed
	public String getScreenshot(String testCaseName, WebDriver d) throws IOException
	{
	TakesScreenshot pic = (TakesScreenshot) d;	//passing info to driver by casting
	File source = pic.getScreenshotAs(OutputType.FILE); //selenium takes screenshot and stores in file
	File filePath = new File(System.getProperty("user.dir")+"\\reports\\"+testCaseName+" .png");
	FileUtils.copyFile(source, filePath);
	return System.getProperty("user.dir")+"\\reports\\"+testCaseName+" .png";
	}
	
	@BeforeMethod (alwaysRun=true)
	public LandingPage launchApplication() throws IOException
	{
		//launching browser and login
		d =initializeDriver(); //catching the driver returned
		lp = new LandingPage(d);
		lp.goTo();
		return lp;
	}
	
	
	
	@AfterMethod (alwaysRun=true)
	public void closeBrowser()
	{
		d.close();		
	}
	
}
	
