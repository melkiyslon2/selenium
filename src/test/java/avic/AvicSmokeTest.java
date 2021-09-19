package avic;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.testng.AssertJUnit.assertTrue;

public class AvicSmokeTest {

    private WebDriver driver;

    @BeforeTest
    public void profileSetUp(){
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
    }

    @BeforeMethod
    public void testsSetUp(){
    driver = new ChromeDriver();
    driver.manage().window().maximize();
    driver.get("https://avic.ua/");
    }

    @Test(priority = 1)
    public void iphoneInMoneyRange(){
        driver.findElement(By.xpath("//span[@class='sidebar-item']")).click();
        driver.findElement(By.xpath("//span[contains(text(),'Apple Store')]")).click();
        driver.findElement(By.xpath("//div[@class='brand-box__title']//a[contains(text(),'iPhone')]")).click();
        driver.findElement(By.xpath("//input[contains(@class,'form-control-min')]")).sendKeys("24200", Keys.ENTER);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//div[contains(@class,'open-filter-tooltip')]//a[@class='filter-tooltip js_filters_accept']")).click();
        List<WebElement> elements = driver.findElements(By.xpath("//div[@class='prod-cart__descr']"));
        for (WebElement element: elements) {
            assertTrue(element.getText().contains("Apple iPhone"));
        }
    }

    @Test(priority = 2)
    public void changeColorOfEarPhones() {
        driver.findElement(By.xpath("//input[@id='input_search']")).sendKeys("Pixel");
        driver.findElement(By.xpath("//button[@class='button-reset search-btn']")).click();
        driver.findElement(By.xpath("//a[@title='Наушники Google Pixel Buds 2 White']")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//a[contains(@data-ecomm-cart,'Наушники Google Pixel Buds 2 White')]")).click();
        WebDriverWait wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("js_cart")));
        driver.findElement(By.xpath("//a[contains(text(),'Продолжить покупки')]")).click();
        String color = driver.findElement(By.xpath("//span[@class='name']")).getText();
        assertTrue(color.contains("Google Pixel Buds"));
    }

    @Test(priority = 3)
    public void searchShopsInKharkov(){
        driver.findElement(By.xpath("//span[contains(text(),'Электротранспорт')]")).click();
        driver.findElement(By.xpath("//div[contains(text(),'Где купить Электротранспорт?')]")).click();
        List<WebElement> elementsList = driver.findElements(By.xpath("//div[@class='faq__question accord-box open']//ul/li[contains(text(),'Харьков')]"));
        for (WebElement element: elementsList) {
            assertTrue(element.getText().contains("Харьков"));
        }
    }

    @AfterMethod
    public void tearDown(){
        driver.close();
    }
}
