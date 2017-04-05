package imietest.vnc2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.openqa.selenium.*;

public class VncMenuPage extends VncRootPage {

	public static final String TITLE = "Mon compte - VINICOM";
	
	By ByDisconnect = By.xpath("//body[@id='my-account']/table/tbody/tr[3]/td/div/div/div/ul/li[5]/a/span");

	
	
	public VncMenuPage(WebDriver pDriver) {
		super( pDriver);
	}
	
	
	
	public VncRootPage clickOnDisconnect()
	{
		assert(driver != null);
		driver.findElement(ByDisconnect).click();
	    return createDoliPage();
	}
	

}
