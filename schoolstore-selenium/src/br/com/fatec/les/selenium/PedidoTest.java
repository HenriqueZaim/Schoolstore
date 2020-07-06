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
public class PedidoTest {
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
  public void pedidoTeste() throws InterruptedException {
    driver.get("http://localhost:8085/schoolstore/");
    driver.manage().window().setSize(new Dimension(1366, 699));
    {
      WebElement element = driver.findElement(By.linkText("LOGIN"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).perform();
    }
    driver.findElement(By.linkText("LOGIN")).click();
    driver.findElement(By.id("txtEmail")).click();
    driver.findElement(By.id("txtEmail")).sendKeys("joao@gmail.com");

    driver.findElement(By.id("txtSenha")).sendKeys("qwer1234");
    driver.findElement(By.cssSelector(".btn")).click();
    {
      WebElement element = driver.findElement(By.linkText("Iní­cio"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).perform();
    }
    driver.findElement(By.linkText("Iní­cio")).click();
    Thread.sleep(2000);
    driver.findElement(By.cssSelector(".col-lg-4:nth-child(1) form:nth-child(2) > .btn")).click();
    driver.findElement(By.cssSelector(".btn")).click();
    {
      WebElement element = driver.findElement(By.linkText("Iní­cio"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).perform();
    }
    driver.findElement(By.linkText("Iní­cio")).click();
    Thread.sleep(2000);
    driver.findElement(By.cssSelector(".col-lg-4:nth-child(7) form:nth-child(2) > .btn")).click();
    driver.findElement(By.cssSelector(".btn")).click();
    {
      WebElement element = driver.findElement(By.cssSelector("form:nth-child(2) > .list-group-item"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).perform();
    }
    driver.findElement(By.cssSelector("form:nth-child(2) > .list-group-item")).click();
    driver.findElement(By.name("txtQuantidade")).sendKeys("1");

    driver.findElement(By.id("botaoSubmit")).click();
    Thread.sleep(5000);
    driver.findElement(By.cssSelector(".enderecoFavorito > .form-check-label")).click();
    driver.findElement(By.cssSelector("#cupom1 .form-check-label")).click();
    driver.findElement(By.id("botaoSubmit")).click();
    driver.findElement(By.linkText("Meu Pedidos")).click();
    driver.findElement(By.cssSelector(".rounded-circle")).click();
    driver.findElement(By.cssSelector(".dropdown-item:nth-child(1)")).click();
    driver.findElement(By.linkText("LOGIN")).click();
    driver.findElement(By.id("txtEmail")).click();
    driver.findElement(By.id("txtEmail")).sendKeys("admin@admin.com");
    driver.findElement(By.id("txtSenha")).sendKeys("qwer1234");
    driver.findElement(By.id("txtSenha")).sendKeys(Keys.ENTER);
    Thread.sleep(2000);
    {
      WebElement element = driver.findElement(By.linkText("Lista de Pedidos"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).perform();
    }
    Thread.sleep(2000);
    driver.findElement(By.linkText("Lista de Pedidos")).click();
    Thread.sleep(6000);
    driver.findElement(By.id("alterarBotaoStatus-94")).click();
    {
      WebElement element = driver.findElement(By.id("alterarBotaoStatus-94"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).perform();
    }
    {
      WebElement element = driver.findElement(By.tagName("body"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element, 0, 0).perform();
    }
    driver.findElement(By.id("modalSelect-94")).click();
    {
      WebElement dropdown = driver.findElement(By.id("modalSelect-94"));
      dropdown.findElement(By.xpath("//option[. = 'Aprovado']")).click();
    }
    driver.findElement(By.id("modalSelect-94")).click();
    driver.findElement(By.id("alterarStatusBotao-94")).click();
    Thread.sleep(10000);
    driver.findElement(By.id("alterarBotaoStatus-94")).click();
    driver.findElement(By.id("modalSelect-94")).click();
    {
      WebElement dropdown = driver.findElement(By.id("modalSelect-94"));
      dropdown.findElement(By.xpath("//option[. = 'Em trânsito']")).click();
    }
    driver.findElement(By.id("modalSelect-94")).click();
    driver.findElement(By.id("alterarStatusBotao-94")).click();
    Thread.sleep(10000);
    driver.findElement(By.id("alterarBotaoStatus-94")).click();
    driver.findElement(By.id("modalSelect-94")).click();
    {
      WebElement dropdown = driver.findElement(By.id("modalSelect-94"));
      dropdown.findElement(By.xpath("//option[. = 'Entregue']")).click();
    }
    driver.findElement(By.id("modalSelect-94")).click();
    driver.findElement(By.id("alterarStatusBotao-94")).click();
    Thread.sleep(5000);
    driver.findElement(By.cssSelector(".text-center:nth-child(58) > td:nth-child(9)")).click();
    driver.findElement(By.cssSelector(".rounded-circle")).click();
    driver.findElement(By.cssSelector("button.dropdown-item")).click();

    driver.findElement(By.linkText("LOGIN")).click();
    driver.findElement(By.id("txtEmail")).click();
    driver.findElement(By.id("txtEmail")).sendKeys("joao@gmail.com");
    driver.findElement(By.id("txtSenha")).sendKeys("qwer1234");
    driver.findElement(By.id("txtSenha")).sendKeys(Keys.ENTER);
    Thread.sleep(5000);
    {
      WebElement element = driver.findElement(By.linkText("Meu Pedidos"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).perform();
    }
    Thread.sleep(5000);
    driver.findElement(By.linkText("Meu Pedidos")).click();
    Thread.sleep(5000);
    driver.findElement(By.cssSelector(".text-info")).click();
    Thread.sleep(5000);

    driver.findElement(By.cssSelector("tr:nth-child(2) > td:nth-child(5)")).click();
    driver.findElement(By.cssSelector(".btn-primary")).click();
    driver.findElement(By.cssSelector(".rounded-circle")).click();
    driver.findElement(By.cssSelector(".dropdown-item:nth-child(1)")).click();
  
    
    driver.findElement(By.linkText("LOGIN")).click();
    driver.findElement(By.id("txtEmail")).click();
    driver.findElement(By.id("txtEmail")).sendKeys("admin@admin.com");
    driver.findElement(By.id("txtSenha")).sendKeys("qwer1234");
    driver.findElement(By.id("txtSenha")).sendKeys(Keys.ENTER);
    Thread.sleep(4000);
    driver.findElement(By.linkText("Lista de Trocas")).click();
    Thread.sleep(4000);
    driver.findElement(By.id("alterarStatusBotao-1")).click();
    {
      WebElement dropdown = driver.findElement(By.id("modalSelect-1"));
      dropdown.findElement(By.xpath("//option[. = 'Autorizada']")).click();
    }
    {
      WebElement element = driver.findElement(By.id("modalSelect-1"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).clickAndHold().perform();
    }
    {
      WebElement element = driver.findElement(By.id("modalSelect-1"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).perform();
    }
    {
      WebElement element = driver.findElement(By.id("modalSelect-1"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).release().perform();
    }
    driver.findElement(By.id("modalSelect-1")).click();
    driver.findElement(By.cssSelector(".btn-success")).click();
    Thread.sleep(4000);
    driver.findElement(By.id("alterarStatusBotao-1")).click();
    {
      WebElement dropdown = driver.findElement(By.id("modalSelect-1"));
      dropdown.findElement(By.xpath("//option[. = 'Em troca']")).click();
    }
    {
      WebElement element = driver.findElement(By.id("modalSelect-1"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).clickAndHold().perform();
    }
    {
      WebElement element = driver.findElement(By.id("modalSelect-1"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).perform();
    }
    {
      WebElement element = driver.findElement(By.id("modalSelect-1"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).release().perform();
    }
    driver.findElement(By.id("modalSelect-1")).click();
    driver.findElement(By.cssSelector(".btn-success")).click();
    Thread.sleep(4000);
    driver.findElement(By.id("alterarStatusBotao-1")).click();
    {
      WebElement dropdown = driver.findElement(By.id("modalSelect-1"));
      dropdown.findElement(By.xpath("//option[. = 'Trocado']")).click();
    }
    {
      WebElement element = driver.findElement(By.id("modalSelect-1"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).clickAndHold().perform();
    }
    {
      WebElement element = driver.findElement(By.id("modalSelect-1"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).perform();
    }
    {
      WebElement element = driver.findElement(By.id("modalSelect-1"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).release().perform();
    }
    driver.findElement(By.id("modalSelect-1")).click();
    driver.findElement(By.cssSelector(".btn-success")).click();
  }
}