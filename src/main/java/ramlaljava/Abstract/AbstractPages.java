package ramlaljava.Abstract;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import ramlaljava.pageObject.CartPage;
import ramlaljava.pageObject.OrdersPage;

public class AbstractPages {

	//this class acts as parent to all page object model classes
	//it holds all of its reusable codes - eg., wait code
	//instances of this class cannot be created
	
	WebDriver d;
	public AbstractPages(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.d = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "[routerlink*='cart']") WebElement cartHeader; //cart button webElement
	@FindBy(css = "[routerlink*='myorders']") WebElement ordersHeader; //Orders button webElement


	
	public void waitForElementToAppear(By locator)   //"By" data type for locator
	{
		WebDriverWait wait = new WebDriverWait(d, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));	
	}
	
	public void waitForWebElementToAppear(WebElement locator)   //"By" data type for locator
	{
		WebDriverWait wait = new WebDriverWait(d, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(locator));	
	}

	
	public void waitForElementToDisappear(WebElement element)   //"By" data type for locator
	{
		WebDriverWait wait = new WebDriverWait(d, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(element));
	}
	
	public CartPage goToCartPage() throws InterruptedException
	{
		
		Thread.sleep(1000);
		cartHeader.click();
		CartPage cartp = new CartPage(d);
		return cartp;
	}

	public OrdersPage goToOrdersPage()
	{		
		ordersHeader.click();
		OrdersPage orderp = new OrdersPage(d);
		return orderp;
	}
	
	
	
}
