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
  public void testVncConnexion() throws Exception {
    // type | id=email | testclient@imie.fr
    driver.findElement(By.id("email")).clear();
    driver.findElement(By.id("email")).sendKeys("testclient@imie.fr");
    // type | id=passwd | rien
    driver.findElement(By.id("passwd")).clear();
    driver.findElement(By.id("passwd")).sendKeys("rien");
    // click | id=SubmitLogin | 
    driver.findElement(By.id("SubmitLogin")).click();
    // verifyText | //li | mot de passe non valable
    try {
      assertEquals("mot de passe non valable", driver.findElement(By.xpath("//li")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    // type | id=passwd | motdepasseinconnu
    driver.findElement(By.id("passwd")).clear();
    driver.findElement(By.id("passwd")).sendKeys("motdepasseinconnu");
    // click | id=SubmitLogin | 
    driver.findElement(By.id("SubmitLogin")).click();
    // verifyText | //li | Authentication failed.[testclient@imie.fr/motdepasseinconnu=>]
    try {
      assertEquals("Authentication failed.[testclient@imie.fr/motdepasseinconnu=>]", driver.findElement(By.xpath("//li")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    // type | id=email | rien@imie.fr
    driver.findElement(By.id("email")).clear();
    driver.findElement(By.id("email")).sendKeys("rien@imie.fr");
    // type | id=passwd | rien@imie.fr
    driver.findElement(By.id("passwd")).clear();
    driver.findElement(By.id("passwd")).sendKeys("rien@imie.fr");
    // click | id=SubmitLogin | 
    driver.findElement(By.id("SubmitLogin")).click();
    // verifyText | //li | Authentication failed.[rien@imie.fr/rien@imie.fr=>]
    try {
      assertEquals("Authentication failed.[rien@imie.fr/rien@imie.fr=>]", driver.findElement(By.xpath("//li")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    // type | id=email | testclient@imie.fr
    driver.findElement(By.id("email")).clear();
    driver.findElement(By.id("email")).sendKeys("testclient@imie.fr");
    // type | id=passwd | testclient@imie.fr
    driver.findElement(By.id("passwd")).clear();
    driver.findElement(By.id("passwd")).sendKeys("testclient@imie.fr");
    // click | id=SubmitLogin | 
    driver.findElement(By.id("SubmitLogin")).click();
    // assertTitle | Mon compte - VINICOM | 
    assertEquals("Mon compte - VINICOM", driver.getTitle());
    // click | //body[@id='my-account']/table/tbody/tr[3]/td/div/div/div/ul/li[5]/a/span | 
    driver.findElement(By.xpath("//body[@id='my-account']/table/tbody/tr[3]/td/div/div/div/ul/li[5]/a/span")).click();
  }

  @Test
  public void testVncConnexionPOD() throws Exception {
	  
	 VncLoginPage oLoginPage = new VncLoginPage(driver);
	 oLoginPage.setUserName("testclient@imie.fr");
	 oLoginPage.setPassword("rien");
	 oLoginPage= (VncLoginPage)oLoginPage.clickOnConnect();
    try {
      assertEquals("mot de passe non valable", oLoginPage.getMessageError());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
	 oLoginPage.setUserName("testclient@imie.fr");
	 oLoginPage.setPassword("motdepasseinconnu");
	 oLoginPage= (VncLoginPage)oLoginPage.clickOnConnect();
   try {
     assertEquals("Authentication failed.[testclient@imie.fr/motdepasseinconnu=>]", oLoginPage.getMessageError());
   } catch (Error e) {
     verificationErrors.append(e.toString());
   }

	 oLoginPage.setUserName("rien@imie.fr");
	 oLoginPage.setPassword("rien@imie.fr");
	 oLoginPage = (VncLoginPage)oLoginPage.clickOnConnect();
 try {
   assertEquals("Authentication failed.[rien@imie.fr/rien@imie.fr=>]", oLoginPage.getMessageError());
 } catch (Error e) {
   verificationErrors.append(e.toString());
 }
	 oLoginPage.setUserName("testclient@imie.fr");
	 oLoginPage.setPassword("testclient@imie.fr");
	 VncRootPage oPage = oLoginPage.clickOnConnect();

	 // assertTitle | Mon compte - VINICOM | 
    assertEquals(VncMenuPage.TITLE, oPage.getTitle());
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
