package ramlaljava.test;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import ramlaljava.pageObject.CartPage;
import ramlaljava.pageObject.CheckoutPage;
import ramlaljava.pageObject.ConfirmationPage;
import ramlaljava.pageObject.OrdersPage;
import ramlaljava.pageObject.ProductCatalog;
import ramlaljava.testComponents.BaseTest;

public class SubmitOrderTest extends BaseTest {
	
	String productName = "ZARA COAT 3";


@Test (dataProvider="getData", groups = {"Purchase"})
public void placeOrder(String email, String password, String productName) throws IOException, InterruptedException
{
		
		//pc object initialized inside LandingPage class
		ProductCatalog pc = lp.loginApplication(email, password);
		
		//receive all products
		List<WebElement> products = pc.getProdctList();

		//filter required product and add to cart
		pc.addProductToCart(productName);
				
		//click on cart - return object and create new object for cartPage
		CartPage cartp = pc.goToCartPage();
		
		//verifying item in cart and go to checkout
		Boolean match = cartp.verifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckoutPage checkp = cartp.goToCheckout();
		
		//selecting country in checkout page
		checkp.selectCountry("india");
		ConfirmationPage confirmp = checkp.submitOrder();
		
		//verify place order successful
		String confirmMessage = confirmp.getConfirmationMessage();
		AssertJUnit.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		System.out.println(confirmMessage);
	}

@Test (dependsOnMethods = {"placeOrder"})
public void orderHistoryTest()
{
	
	ProductCatalog pc = lp.loginApplication("ramlal@example.com", "ramlalGR7");
	OrdersPage orderp = pc.goToOrdersPage();
	Assert.assertTrue(orderp.verifyOrderDisplay(productName));
}


//Provide data using HashMap - Json file
@DataProvider
public Object[][] getData() throws IOException
{
return new Object[][] {{"ramlal@example.com", "ramlalGR7", "ZARA COAT 3"} , 
{"ramlal1@example.com", "ramlalGR7", "ADIDAS ORIGINAL"}};  //initializing and returning the same object creat
}


//Provide data using HashMap
//@DataProvider
//public Object[][] getData()
//{
//HashMap<String,String> map1 = new HashMap<String,String>();	
//map1.put("email", "ramlal@example.com");
//map1.put("password", "ramlalGR7");
//map1.put("product", "ZARA COAT 3");
//
//HashMap<String,String> map2 = new HashMap<String,String>();	
//map2.put("email", "ramlal1@example.com");
//map2.put("password", "ramlalGR7");
//map2.put("product", "ADIDAS ORIGINAL");
//return new Object[][] {{map1}, {map2}};
//}



}
