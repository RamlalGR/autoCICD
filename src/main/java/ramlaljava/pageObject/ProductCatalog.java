package ramlaljava.pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import ramlaljava.Abstract.AbstractPages;

public class ProductCatalog extends AbstractPages {

	WebDriver d;

	public ProductCatalog(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.d = driver;
		PageFactory.initElements(d, this); // trigger to initialize elements

	}

	// PageFactory - using pageFactory to initialize elements

	@FindBy(css = ".mb-3") List<WebElement> products; //receive all products webElements
	@FindBy(css = ".ng-animating") WebElement spinner; //loading screen webElement

	By productsBy = By.cssSelector(".mb-3"); //locator for all products
	By addToCart = By.cssSelector(".card-body button:last-of-type"); //locator for required product
	By toastMessage = By.cssSelector("#toast-container"); //locator for toast message

	public List<WebElement> getProdctList() {
		// waiting for elements to appear and return products
		waitForElementToAppear(productsBy);
		return products;

	}

	public WebElement getProductByName(String productName) {
		/*
		 * WebElement prod = products.stream() .filter(product ->
		 * product.findElement(By.cssSelector("b")).getText().equals(productName)).
		 * findFirst() .orElse(null);
		 */

		// changing "products" directly to through method so that it will be executed
		// after the wait
		// filter required product from list and return
		return getProdctList().stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
				.orElse(null);

	}

	public void addProductToCart(String productName) {
		getProductByName(productName).findElement(addToCart).click();
		waitForElementToAppear(toastMessage);
		waitForElementToDisappear(spinner);

	}

}
