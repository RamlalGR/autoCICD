package ramlaljava.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConfirmationPage {

	
	WebDriver d;

	public ConfirmationPage(WebDriver d) {
		// TODO Auto-generated constructor stub
		this.d = d;
		PageFactory.initElements(d, this);
	}

	@FindBy(css = ".hero-primary") WebElement confirmationMessage; //receive all products webElements

	public String getConfirmationMessage()
	{
		
		return confirmationMessage.getText();		
	}

}
