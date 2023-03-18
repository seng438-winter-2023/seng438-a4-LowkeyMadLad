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
public class AddItemtoCartTest {
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
  public void addItemtoCart() {
    driver.get("https://www.amazon.ca/");
    driver.manage().window().setSize(new Dimension(1649, 1000));
    driver.findElement(By.linkText("Home")).click();
    driver.findElement(By.cssSelector(".bxc-grid__row:nth-child(1) > .bxc-grid__column:nth-child(1) img")).click();
    driver.findElement(By.cssSelector(".a-declarative:nth-child(2) .a-section .a-section > .a-section")).click();
    driver.findElement(By.cssSelector("div:nth-child(3) .octopus-pc-item:nth-child(3) .a-link-normal:nth-child(1) > .a-section:nth-child(1) > .a-section:nth-child(1)")).click();
    driver.findElement(By.id("add-to-cart-button")).click();
    {
      WebElement element = driver.findElement(By.name("proceedToRetailCheckout"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).perform();
    }
    {
      List<WebElement> elements = driver.findElements(By.id("sw-atc-details-single-container"));
      assert(elements.size() > 0);
    }
    {
      List<WebElement> elements = driver.findElements(By.xpath("//div[2]/div/div/div/div[2]/div/div/div/div/span"));
      assert(elements.size() > 0);
    }
    driver.findElement(By.linkText("Go to cart")).click();
    {
      List<WebElement> elements = driver.findElements(By.cssSelector("#sc-active-cart > .a-cardui-body"));
      assert(elements.size() > 0);
    }
  }
}