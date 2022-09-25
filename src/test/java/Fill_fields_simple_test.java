import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.util.concurrent.TimeUnit;

public class Fill_fields_simple_test {

    private WebDriver driver;
    static WebDriverWait wait;
    private By sumFieldLocator = By.cssSelector("#inputSumFrom");
    private By phoneLocator = By.cssSelector("#phone");
    private By bitcoinFieldLocator = By.cssSelector("#Bitcoin");
    private By mailFieldLocator = By.cssSelector("#mainEmail");
    private By confirmCheckboxLocator = By.xpath("(//*[@class='form-check-input'])[1]");
    private By mainConfirmButtonLocator = By.cssSelector(".primaryButton.mb-5");
    private By confirmWindowLocator = By.xpath("(//*[@class='modal-content'])[1]");
    private By createRequestButtonLocator = By.cssSelector(".primaryButton.w-50.d-flex.justify-content-center" +
            ".align-items-center");

    @Before
    public void setUp()
    {
        System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 5);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @After
    public void tearDown()
    {
        driver.quit();
    }

    @Test
    public void automaticFillFields() throws InterruptedException {
        driver.navigate().to("https://bit-changer.cc/");
        driver.findElement(sumFieldLocator).sendKeys("100");
        driver.findElement(phoneLocator).sendKeys("1234567890");
        driver.findElement(bitcoinFieldLocator).sendKeys("123123");
        driver.findElement(mailFieldLocator).sendKeys("test@test.com");
        Thread.sleep(400);
        driver.findElement(confirmCheckboxLocator).click();
        WebElement element = driver.findElement(mainConfirmButtonLocator);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", element);
        Thread.sleep(400);
        driver.findElement(mainConfirmButtonLocator).click();
        Thread.sleep(400);
        Assert.assertTrue("Не отобразилось окно подтверждения заявки",
                driver.findElement(confirmWindowLocator).isDisplayed());
        driver.findElement(createRequestButtonLocator).click();
    }
}
