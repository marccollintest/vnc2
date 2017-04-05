package imietest.vnc2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.openqa.selenium.*;

public class VncRootPage extends RootSelenium{



	public VncRootPage(WebDriver pDriver) {
		super(pDriver);
	}
	
	/**
	 * rend le titre de la page courante
	 * @return
	 */
	public String getTitle()
	{
		return driver.getTitle();
	}

	public String getSubTitle()
	{
		if (isElementPresent(By.cssSelector("div.titre")))
		{
			return driver.findElement(By.cssSelector("div.titre")).getText();
		}
		else
		{
			return "";
		}
	}
	public Boolean isFichePresent()
	{
		return isElementPresent(By.id("card"));
	}

	/**
	 * Cr�ation d'une page de test en fonction du titre
	 * @return
	 */

	protected VncRootPage createDoliPage()
	{
		VncRootPage oReturn = this;
		Boolean bOk = false;
		if (!bOk && getTitle().equals(VncLoginPage.TITLE))
		{	bOk = true;
			oReturn = new VncLoginPage(driver);
		}
		if (!bOk && getTitle().equals(VncMenuPage.TITLE))
		{	bOk = true;
			oReturn = new VncMenuPage(driver);
		}
		return oReturn;
	}//createDoliPage
	
}// VncRootPage