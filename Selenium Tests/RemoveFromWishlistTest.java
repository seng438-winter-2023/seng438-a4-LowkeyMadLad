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
public class RemoveFromWishlistTest {
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
  public void removeFromWishlist() {
    driver.get("https://www.amazon.ca/");
    {
      WebElement element = driver.findElement(By.cssSelector("#nav-link-accountList > .nav-line-2"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).perform();
    }
    driver.findElement(By.cssSelector(".nav-link:nth-child(1) > .nav-text")).click();
    {
      List<WebElement> elements = driver.findElements(By.cssSelector("#itemInfo_I1HKG1U3RO6ZZF > .a-row"));
      assert(elements.size() > 0);
    }
    driver.findElement(By.name("submit.deleteItem")).click();
    {
      List<WebElement> elements = driver.findElements(By.cssSelector("#itemInfo_I1HKG1U3RO6ZZF > .a-row"));
      assert(elements.size() == 0);
    }
  }
}