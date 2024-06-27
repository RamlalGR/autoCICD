package ramlaljava.pageObject;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ramlaljava.Abstract.AbstractPages;

public class OrdersPage extends AbstractPages {

	WebDriver d;
	@FindBy(css = "tr td:nth-child(3)") List<WebElement> orderProducts;

	public OrdersPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.d = driver;
		PageFactory.initElements(d, this);  //trigger to initialize elements
	
	}
	
	public Boolean verifyOrderDisplay(String productName)
	{
		Boolean match = orderProducts.stream()
				.anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
		return match;
	}
	
}
