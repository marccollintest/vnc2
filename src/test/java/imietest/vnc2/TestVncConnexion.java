package imietest.vnc2;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class TestVncConnexion {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://localhost/";
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    driver.get(baseUrl + "/vinicom/index.php?controller=authentication");

  }

 
  @Test
  public void testVncConnexionPOD() throws Exception {
	  
	 VncLoginPage oLoginPage = new VncLoginPage(driver);

      // Test avec un mot de passe inconnu
      oLoginPage.setUserName("testclient@imie.fr");
	 oLoginPage.setPassword("motdepasseinconnu");
	 oLoginPage= (VncLoginPage)oLoginPage.clickOnConnect();
     assertEquals("Authentication failed.[testclient@imie.fr/motdepasseinconnu=>]", oLoginPage.getMessageError());

/*     // Test avec un user inconnu
	 oLoginPage.setUserName("rien@imie.fr");
	 oLoginPage.setPassword("rien@imie.fr");
	 oLoginPage = (VncLoginPage)oLoginPage.clickOnConnect();
	 assertEquals("Authentication failed.[rien@imie.fr/rien@imie.fr=>]", oLoginPage.getMessageError());
*/	 
	 // test OK
	 oLoginPage.setUserName("testclient@imie.fr");
	 oLoginPage.setPassword("testclient@imie.fr");
	 VncRootPage oPage = oLoginPage.clickOnConnect();
    assertEquals(VncMenuPage.TITLE, oPage.getTitle());
    
    // Déconnexion
    oPage = ((VncMenuPage)oPage).clickOnDisconnect();
    assertEquals(VncLoginPage.TITLE, oPage.getTitle());
  }
  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
