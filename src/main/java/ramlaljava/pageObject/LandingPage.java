package ramlaljava.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ramlaljava.Abstract.AbstractPages;

public class LandingPage extends AbstractPages {

	WebDriver d;

	public LandingPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.d = driver;
		PageFactory.initElements(d, this);  //trigger to initialize elements
	
	}
	
	
	//WebElement userEmail = d.findElement(By.id("userEmail"));  - replaced using pageFactory
	//PageFactory - using pageFactory to initialize elements
	
	//this annotation does not have driver information
	//driver information given to annotation through initElements methods
	@FindBy(id = "userEmail") WebElement email;
	@FindBy(id = "userPassword") WebElement password;
	@FindBy(id = "login") WebElement login;
	
	@FindBy(css = "[class*='flyInOut']") WebElement errorMessage;

	
	public ProductCatalog loginApplication(String user,String pass)
	{
		email.sendKeys(user);
		password.sendKeys(pass);
		login.click();
		ProductCatalog pc = new ProductCatalog(d);
		return pc;
	}
	
	public void goTo()
	{
		d.get("https://rahulshettyacademy.com/client");
		
	}
	

	public String getErrorMessage()
	{
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	
	}
	
	
	
	

}
