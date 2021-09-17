package avic;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;
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

   /* @Test(priority = 1)
    public void checkWebUrlContainsSearchWord(){
        driver.findElement(By.xpath("//input[@id='input_search']")).sendKeys("iPhone 11");
        driver.findElement(By.xpath("//button[@class='button-reset search-btn']")).click();
        assertTrue(driver.getCurrentUrl().contains("query=iPhone+11"));
    }

    @Test(priority = 2)
    public void checkAmountOnPage(){
        driver.findElement(By.xpath("//input[@id='input_search']")).sendKeys("iPhone 11");
        driver.findElement(By.xpath("//button[@class='button-reset search-btn']")).click();
        List<WebElement> elementsList = driver.findElements(By.xpath("//div[@class='item-prod col-lg-3']"));
        assertEquals(elementsList.size(),12);
    }

    @Test(priority = 3)
    public void checkEveryElementContainsWord(){
        driver.findElement(By.xpath("//input[@id='input_search']")).sendKeys("iPhone 11");
        driver.findElement(By.xpath("//button[@class='button-reset search-btn']")).click();
        List<WebElement> elementsList = driver.findElements(By.xpath("//div[@class='item-prod col-lg-3']"));
        for (WebElement element: elementsList){
            assertTrue(element.getText().contains("iPhone 11"));
        }
    }

    @Test(priority = 4)
    public void checkCart(){
        driver.findElement(By.xpath("//span[@class='sidebar-item']")).click();
        driver.findElement(By.xpath("//span[contains(text(),'Apple Store')]")).click();
        driver.findElement(By.xpath("//div[@class='brand-box__title']//a[contains(text(),'iPhone')]")).click();
        driver.findElement(By.xpath("//a[contains(@data-ecomm-cart,'Смартфон Apple iPhone 11 64GB Slim Box White (MHDC3)')]")).click();
        WebDriverWait wait = new WebDriverWait(driver,3000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("js_cart")));
        driver.findElement(By.xpath("//a[contains(text(),'Продолжить покупки')]")).click();
        String checkCart = driver.findElement(By.xpath("//div[contains(@class,'header-bottom__cart')]//div[contains(@class,'cart_count')]")).getText();
        assertEquals(checkCart,"1");
    }*/

    @Test(priority = 1)
    public void iphoneInMoneyRange(){
        driver.findElement(By.xpath("//span[@class='sidebar-item']")).click();
        driver.findElement(By.xpath("//span[contains(text(),'Apple Store')]")).click();
        driver.findElement(By.xpath("//div[@class='brand-box__title']//a[contains(text(),'iPhone')]")).click();
        driver.findElement(By.xpath("//input[contains(@class,'form-control-min')]")).sendKeys("24200", Keys.ENTER);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//div[contains(@class,'open-filter-tooltip')]//a[@class='filter-tooltip js_filters_accept']")).click();
    }

    @Test(priority = 2)
    public void changeStorage() {
        driver.findElement(By.xpath("//input[@id='input_search']")).sendKeys("Pixel");
        driver.findElement(By.xpath("//button[@class='button-reset search-btn']")).click();
        driver.findElement(By.xpath("//a[@title='Наушники Google Pixel Buds 2 White']")).click();
        //driver.findElement(By.xpath("")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//a[contains(@data-ecomm-cart,'Наушники Google Pixel Buds 2 Black')]")).click();
        WebDriverWait wait = new WebDriverWait(driver,3000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("js_cart")));
        driver.findElement(By.xpath("//a[contains(text(),'Продолжить покупки')]")).click();
    }

    @Test(priority = 3)
    public void testShopsInKharkov(){
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
