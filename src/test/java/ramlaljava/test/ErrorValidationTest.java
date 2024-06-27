package ramlaljava.test;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import ramlaljava.pageObject.CartPage;
import ramlaljava.pageObject.ProductCatalog;
import ramlaljava.testComponents.BaseTest;
import ramlaljava.testComponents.Retry;

public class ErrorValidationTest extends BaseTest {

@Test (groups = {"ErrorHandling"}, retryAnalyzer= Retry.class)
public void placeOrder() throws IOException, InterruptedException
{
 
	String productName = "ZARA COAT 3";
	
	//pc object initialized inside LandingPage class
	ProductCatalog pc = lp.loginApplication("r@example.com", "ramlalGR7");
	lp.getErrorMessage();
	Assert.assertEquals("Incorrect email or password.", lp.getErrorMessage());
	}

@Test
public void productErrorValidation() throws InterruptedException
{
	String productName = "ZARA COAT 3";
	
	//pc object initialized inside LandingPage class
	ProductCatalog pc = lp.loginApplication("ramlal1@example.com", "ramlalGR7");
	
	//receive all products
	List<WebElement> products = pc.getProdctList();

	//filter required product and add to cart
	pc.addProductToCart(productName);
			
	//click on cart - return object and create new object for cartPage
	CartPage cartp = pc.goToCartPage();
	
	//verifying item in cart and go to checkout
	Boolean match = cartp.verifyProductDisplay("ZARA COAT 33");
	Assert.assertFalse(match);
	
}
}
