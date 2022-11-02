package basicSelenium;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.security.Key;
import java.util.Date;

public class BasicSeleniumTest {
    WebDriver driver;
    @BeforeEach
    public void setup(){
        System.out.println("setup");
        System.setProperty("webdriver.chrome.driver","src/test/resources/driver/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @AfterEach
    public void cleanup(){
        System.out.println("cleanup");
        //driver.quit();
    }
// bootcamp@mojix44.com
//123345
    @Test
    public void verifySomeThing() throws InterruptedException {
        System.out.println("test");
        driver.get("HTTP://todo.ly/");
        Thread.sleep(5000);
        driver.findElement(By.xpath("//img[contains(@src,'pagelogin')]")).click();
        driver.findElement(By.id("ctl00_MainContent_LoginControl1_TextBoxEmail")).sendKeys("bootcamp@mojix44.com");
        driver.findElement(By.id("ctl00_MainContent_LoginControl1_TextBoxPassword")).sendKeys("123345");
        driver.findElement(By.id("ctl00_MainContent_LoginControl1_ButtonLogin")).click();
        Thread.sleep(1000);
        Assertions.assertTrue(driver.findElement(By.id("ctl00_HeaderTopControl1_LinkButtonLogout")).isDisplayed()
                ,"ERROR login was incorrect");

        // create
        String nameProject="Pruebas-Joaquin-Alcon"+new Date().getTime();
        driver.findElement(By.id("Div2")).click();
        driver.findElement(By.id("NewProjNameInput")).sendKeys(nameProject);
        driver.findElement(By.id("NewProjNameButton")).click();
        Thread.sleep(1000);
        int actualResult=driver.findElements(By.xpath(" //td[text()='"+nameProject+"'] ")).size();
        Assertions.assertTrue(actualResult >= 1
                ,"ERROR The project was not created");
        //tarea
        String tareaNueva = "tarea"+new Date().getTime();
        for(int i=0 ; i <= 4 ; i+=1 ){
            System.out.println(i);
            driver.findElement(By.id("NewItemContentInput")).sendKeys(tareaNueva);
            driver.findElement(By.id("NewItemAddButton")).click();
            Thread.sleep(200);
        }
        Thread.sleep(1000);
        int actualTareas = driver.findElements(By.xpath("//ul[@id='mainItemList']/li")).size();
        System.out.println(actualTareas);
        Thread.sleep(1000);
        Assertions.assertTrue(actualTareas >= 1,"no hay elementos");

        //UPDATE
        String nameLi = driver.findElement(By.xpath("//ul[@id='mainItemList']/li[1]")).getText();
        System.out.println(nameLi);
        driver.findElement(By.xpath("//ul[@id='mainItemList']/li[1]")).click();
        String updateJobs = tareaNueva+"ACTUALIZACION";
        Thread.sleep(1000);
        driver.findElement(By.id("ItemEditTextbox")).clear();
        driver.findElement(By.id("ItemEditTextbox")).sendKeys(updateJobs);
        driver.findElement(By.id("ItemEditTextbox")).sendKeys(Keys.ENTER);
        String updateText = driver.findElement(By.xpath("//ul[@id='mainItemList']/li[1]")).getText();
        Thread.sleep(1000);
        Assertions.assertTrue(nameLi!=updateText,"Text Input isn't updated");
    }
}
