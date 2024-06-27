package ramlaljava.pageObject;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ramlaljava.Abstract.AbstractPages;

public class CartPage extends AbstractPages {

	WebDriver d;
	@FindBy(css = ".cartSection h3") List<WebElement> cartProducts;
	@FindBy(css = ".totalRow button") WebElement checkoutButton;
	

	public CartPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.d = driver;
		PageFactory.initElements(d, this);  //trigger to initialize elements
	
	}
	
	public Boolean verifyProductDisplay(String productName)
	{
		Boolean match = cartProducts.stream()
				.anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public CheckoutPage goToCheckout() throws InterruptedException
	{
		JavascriptExecutor js = (JavascriptExecutor)d;
		js.executeScript("window.scrollBy(0,300)");
		Thread.sleep(1000);
		checkoutButton.click();
		CheckoutPage checkoutp = new CheckoutPage(d);
		return checkoutp;
		
	}
	

	
	
	
	

}
