package imietest.vnc2;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
//import org.openqa.selenium.phantomjs.*;
import org.openqa.selenium.remote.DesiredCapabilities;


import com.thoughtworks.selenium.webdriven.commands.IsElementPresent;
/**
 * Test l'accès à l'application
 * @author IMIE
 *
 */
public class RootTest {
  protected static WebDriver driver;
  protected static String baseUrl;
  protected boolean acceptNextAlert = true;
  
  protected boolean _bconnected = false;
  
  protected VncRootPage _currentPage = null;

  @BeforeClass
  public static void setUpClass() throws Exception {
	  //Using PhantomJS
/*	  DesiredCapabilities caps = new DesiredCapabilities();
	  caps.setJavascriptEnabled(true);
	  caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, "C:/phantomjs-2.1.1-windows/bin/phantomjs.exe");
	  driver = new PhantomJSDriver(caps);
*/
	  // using firefox
	  driver = new FirefoxDriver();
	  
    baseUrl = "http://localhost/";
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
  }
  @AfterClass
  public static void tearDownClass() throws Exception {
	  
	  driver.quit();
  }
  @Before
  public void setUp() throws Exception {
	  //Procédure de connexion
	    driver.get(baseUrl + "/dolibarr/htdocs/user/logout.php");
	    driver.get(baseUrl + "/dolibarr/htdocs/index.php");
	    _currentPage = new VncLoginPage(driver);
	  }
  
  @After
  public void teardown() throws Exception {
	  // Procédure de déconnexion
	  if (isElementPresent(By.xpath("//img[@alt=\"Déconnexion\"]")))
			  {
		  		boolean bok = false;
		  		while (!bok)
		  		try
		  		{
		  			driver.findElement(By.xpath("//img[@alt=\"Déconnexion\"]")).click();
		  			bok = true;
		  		}
		  		catch (Exception ex)
		  		{
		  			Thread.sleep(1000);
		  			bok = false;
		  		}
			  }
	  }
  
  


protected boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  protected boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  protected String closeAlertAndGetItsText() {
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
