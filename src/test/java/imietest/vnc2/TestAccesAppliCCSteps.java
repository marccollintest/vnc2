package imietest.vnc2;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class TestAccesAppliCCSteps extends RootTest{

	@Given("^un navigateur est démaré$")
	public void un_navigateur_est_démaré() throws Throwable {
	    driver = new FirefoxDriver();
	    baseUrl = "http://localhost";
	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Given("^Je suis sur la page de login$")
	public void je_suis_sur_la_page_de_login() throws Throwable {
	    driver.get(baseUrl + "/vinicom/index.php?controller=authentication");
	    _currentPage = new VncLoginPage(driver);
	}

	@When("^Je me connecte en \"([^\"]*)\" et \"([^\"]*)\"$")
	public void je_connecte_en_et(String arg1, String arg2) throws Throwable {
		VncLoginPage oPage = (VncLoginPage) _currentPage;
		oPage.setUserName(arg1);
		oPage.setPassword(arg2);
		oPage.clickOnConnect();
	}

	@Then("^La page affichée est la page delogin$")
	public void la_page_affichée_est_la_page_d_acccueil() throws Throwable {
		assertEquals(VncLoginPage.TITLE, _currentPage.getTitle());
	}

	@Then("^je ferme le navigateur$")
	public void je_ferme_le_navigateur() throws Throwable {
		driver.quit();
	}

	@Then("^le message d'erreur est \"([^\"]*)\"$")
	public void le_message_d_erreur_est(String arg1) throws Throwable {
		assertEquals(arg1, ((VncLoginPage)_currentPage).getMessageError());
	}

	

@Then("^La page affichée est la page de login$")
public void la_page_affichée_est_la_page_de_login() throws Throwable {
 assertEquals(VncLoginPage.TITLE,_currentPage.getTitle());
}


}
