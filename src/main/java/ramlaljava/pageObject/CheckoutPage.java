package ramlaljava.pageObject;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import ramlaljava.Abstract.AbstractPages;

public class CheckoutPage extends AbstractPages {

	WebDriver d;
	

	public CheckoutPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.d = driver;
		PageFactory.initElements(d, this);  //trigger to initialize elements
	}
	
	@FindBy (css = "[placeholder='Select Country']") private WebElement country;
	@FindBy (css = ".action__submit") private WebElement submit;
	@FindBy (xpath = "(//button[contains(@class,'ta-item')])[2]") private WebElement selectIndia;
	
	private By results = By.cssSelector(".ta-results");
	
	public void selectCountry(String countryName) throws InterruptedException
	{
		
		Actions a = new Actions(d);
		a.sendKeys(country, countryName).build().perform();
		waitForElementToAppear(results);
		Thread.sleep(1000);
		selectIndia.click();
	}
	
	public ConfirmationPage submitOrder()
	{
		submit.click();
		return new ConfirmationPage(d);
	}
	

}
