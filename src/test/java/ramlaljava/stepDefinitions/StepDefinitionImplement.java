package ramlaljava.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.AssertJUnit;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import ramlaljava.pageObject.CartPage;
import ramlaljava.pageObject.CheckoutPage;
import ramlaljava.pageObject.ConfirmationPage;
import ramlaljava.pageObject.LandingPage;
import ramlaljava.pageObject.ProductCatalog;
import ramlaljava.testComponents.BaseTest;

public class StepDefinitionImplement extends BaseTest {

	public LandingPage lp;	
	public ProductCatalog pc;
	public ConfirmationPage confirmp;
	
	@Given ("I landed on Ecommerce page") //static expression
	public void I_landed_on_Ecommerce_page() throws IOException
	{
		//write pre-requisite steps here - For Background keyword
		lp = launchApplication();
	}
	
	@Given ("^logged in with username (.+) and password (.+)$")  //(.+) - since data driven run time - regex
	public void logged_in_with_username_and_password(String username, String password)
	{
		pc = lp.loginApplication(username, password);
	}
	
	@When ("^I add product (.+) from cart$")
	public void i_add_product_from_cart(String productName)
	{
		List<WebElement> products = pc.getProdctList();
		pc.addProductToCart(productName);
	}
	
	@When ("^Checkout (.+) and submit the order$")
	public void checkout_submit_order(String productName) throws InterruptedException
	{
		CartPage cartp = pc.goToCartPage();
		
		//verifying item in cart and go to checkout
		Boolean match = cartp.verifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckoutPage checkp = cartp.goToCheckout();
		
		//selecting country in checkout page
		checkp.selectCountry("india");
		confirmp = checkp.submitOrder();
	}
	
    @Then("{String} message is displayed on confirmationPage") //String takes input from test step text data
    public void message_displayed_confirmationPage(String string)
    {
		String confirmMessage = confirmp.getConfirmationMessage();
		AssertJUnit.assertTrue(confirmMessage.equalsIgnoreCase(string));
		d.close();
    }
	
    @Then("{String} message is displayed on confirmationPage") //String takes input from test step text data
    public void error_message_displayed(String string)
    {
    	Assert.assertEquals(string, lp.getErrorMessage());
    	d.close();
    }
	
}
