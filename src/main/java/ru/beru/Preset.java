package ru.beru;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Preset {

    public static WebDriver driver;

    public static ScreenTaker screen;

    public static String SCREEN_PATH = new File("screenshots").getAbsolutePath();

    public String saveShot() {
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh.mm.ss");
        screen.screenPage(dateFormat.format(date));
        return dateFormat.format(date);
    }

    @BeforeMethod
    public void runUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\ChromeDriver\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        //options.addArguments("start-fullscreen");
        driver = new ChromeDriver(options);
        screen = new ScreenTaker(driver, SCREEN_PATH);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.get("https://beru.ru");
    }

    @AfterMethod
    public void signOut() {
        if (driver.findElement(By.cssSelector(".header2-nav__user"))
                .getAttribute("textContent").contains("Мой профиль")) {
            driver.findElement(By.cssSelector(".header2-nav__user")).click();
            driver.findElement(By.cssSelector("[class*='type_logout']")).click();
        }
        driver.quit();
    }
}

