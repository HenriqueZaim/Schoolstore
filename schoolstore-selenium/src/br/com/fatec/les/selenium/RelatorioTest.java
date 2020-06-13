package  br.com.fatec.les.selenium;

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
public class RelatorioTest {
  private WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;
  @Before
  public void setUp() {
    driver = new FirefoxDriver();
    js = (JavascriptExecutor) driver;
    vars = new HashMap<String, Object>();
  }
  @After
  public void tearDown() {
    driver.quit();
  }
  @Test
  public void teste() throws InterruptedException {
    driver.get("http://localhost:8085/schoolstore/index.jsp");
    driver.manage().window().setSize(new Dimension(1366, 699));
    driver.findElement(By.linkText("LOGIN")).click();
    driver.findElement(By.id("txtEmail")).click();
    driver.findElement(By.id("txtEmail")).sendKeys("admin@admin.com");
    driver.findElement(By.id("txtSenha")).sendKeys("qwer1234");
    driver.findElement(By.id("txtSenha")).sendKeys(Keys.ENTER);
    Thread.sleep(2000);
    driver.findElement(By.id("relatorios")).click();
    driver.findElement(By.id("txtDataInicio")).click();
    driver.findElement(By.id("txtDataInicio")).sendKeys("2020-01-01");
    js.executeScript("window.scrollTo(0,0)");
    {
      WebElement element = driver.findElement(By.id("txtDataFim"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).clickAndHold().perform();
    }
    {
      WebElement element = driver.findElement(By.cssSelector(".col-lg-6:nth-child(2)"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).release().perform();
    }
    driver.findElement(By.id("txtDataFim")).click();
    driver.findElement(By.id("txtDataFim")).sendKeys("2020-04-10");
    js.executeScript("window.scrollTo(0,0)");
    driver.findElement(By.id("txtDataFim")).click();
    driver.findElement(By.id("txtDataFim")).sendKeys("2020-03-13");
    js.executeScript("window.scrollTo(0,0)");
    driver.findElement(By.id("txtDataInicio")).sendKeys("\"\"");
    js.executeScript("window.scrollTo(0,0)");
    driver.findElement(By.cssSelector(".col-lg-12 > .card")).click();
    {
      WebElement element = driver.findElement(By.id("txtDataFim"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).clickAndHold().perform();
    }
    {
      WebElement element = driver.findElement(By.cssSelector(".col-lg-6:nth-child(2)"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).release().perform();
    }
    driver.findElement(By.id("txtDataFim")).sendKeys("\"\"");
    js.executeScript("window.scrollTo(0,0)");
    driver.findElement(By.cssSelector(".col-lg-12 > .card")).click();
    driver.findElement(By.id("txtDataInicio")).click();
    driver.findElement(By.id("txtDataInicio")).sendKeys("2020-01-01");
    js.executeScript("window.scrollTo(0,0)");
    driver.findElement(By.cssSelector(".col-lg-12 > .card")).click();
    driver.findElement(By.id("txtDataFim")).click();
    driver.findElement(By.id("txtDataFim")).sendKeys("2020-01-31");
    js.executeScript("window.scrollTo(0,0)");
    driver.findElement(By.id("txtDataInicio")).sendKeys("\"\"");
    js.executeScript("window.scrollTo(0,0)");
    driver.findElement(By.cssSelector(".col-lg-12 > .card")).click();
    {
      WebElement element = driver.findElement(By.id("txtDataFim"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).clickAndHold().perform();
    }
    {
      WebElement element = driver.findElement(By.cssSelector(".col-lg-6:nth-child(2)"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).release().perform();
    }
    driver.findElement(By.id("txtDataFim")).click();
    driver.findElement(By.id("txtDataFim")).sendKeys("2020-02-28");
    driver.findElement(By.cssSelector(".col-lg-12 > .card")).click();
  }
}