// Generated by Selenium IDE
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import java.util.*;
import java.net.MalformedURLException;
import java.net.URL;
public class SearchItemTest {
  private WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;
  @Before
  public void setUp() {
    driver = new ChromeDriver();
    js = (JavascriptExecutor) driver;
    vars = new HashMap<String, Object>();
  }
  @After
  public void tearDown() {
    driver.quit();
  }
  @Test
  public void searchItem() {
    driver.get("https://www.amazon.ca/");
    driver.manage().window().setSize(new Dimension(1512, 874));
    driver.findElement(By.id("twotabsearchtextbox")).click();
    driver.findElement(By.id("twotabsearchtextbox")).sendKeys("Illumination Despicable Me 3 - Mel Wall Poster");
    driver.findElement(By.id("twotabsearchtextbox")).sendKeys(Keys.ENTER);
    {
      List<WebElement> elements = driver.findElements(By.cssSelector(".sg-col-4-of-24:nth-child(7) .a-size-base-plus:nth-child(1)"));
      assert(elements.size() > 0);
    }
    {
      List<WebElement> elements = driver.findElements(By.cssSelector(".sg-col-4-of-24:nth-child(7) .s-image:nth-child(1)"));
      assert(elements.size() > 0);
    }
    driver.findElement(By.id("twotabsearchtextbox")).click();
    driver.findElement(By.id("twotabsearchtextbox")).sendKeys("Disney Frozen 2 - Key Art Wall Poster");
    driver.findElement(By.id("twotabsearchtextbox")).sendKeys(Keys.ENTER);
    {
      List<WebElement> elements = driver.findElements(By.cssSelector(".sg-col-4-of-24:nth-child(3) .s-card-container > .a-section"));
      assert(elements.size() > 0);
    }
  }
}
