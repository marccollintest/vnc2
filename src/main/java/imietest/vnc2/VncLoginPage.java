package imietest.vnc2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class VncLoginPage extends VncRootPage {

	public static final String TITLE = "Connexion - VINICOM";
	
	By ByUserName = By.id("email");
	By ByPassword = By.id("passwd");
	By ByConnect = By.id("SubmitLogin");
	By ByMsgError = By.xpath("//li");

	
	// Les �lements d'interface disponible sur la page
	private WebElement tbUserName;
	private WebElement tbPassword;
	private WebElement btnConnect;
	
	public VncLoginPage(WebDriver pDriver) {
		super( pDriver);
		construct();
	}
	
	/**
	 * Methode de construction de la page : instanciation des attributs
	 */
	protected void construct()
	{
		assert(driver != null);
		// V�rification que l'on est bien sur la bonne page
	    String sTitle = driver.getTitle();
		if(!sTitle.equals(TITLE)) {
            throw new IllegalStateException("This is not Login Page, current page is: " +driver.getCurrentUrl());
		}		
		tbUserName = driver.findElement(ByUserName);
		tbPassword = driver.findElement(ByPassword);
		btnConnect = driver.findElement(ByConnect);
	}
	
	/**
	 * Accessor en ecriture du username
	 * @param pUser
	 */
	public void setUserName(String pUser)
	{
 	    tbUserName.clear();
	    tbUserName.sendKeys(pUser);
		
	}
	
	public void setPassword(String ppwd)
	{
		
	    tbPassword.clear();
	    tbPassword.sendKeys(ppwd);
	}
	
	public VncRootPage clickOnConnect()
	{
		assert(driver != null);
	    btnConnect.click();
	    return createDoliPage();
	}
	
	public String getMessageError()
	{
		assert(driver != null);

		return driver.findElement(ByMsgError).getText();
	}

}
