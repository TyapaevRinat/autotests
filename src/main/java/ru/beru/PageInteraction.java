package ru.beru;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


import org.testng.annotations.*;


public class PageInteraction {
    public ChromeDriver driver;


    @BeforeTest
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "C:\\ChromeDriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://beru.ru");

    }

    public void sleep(int time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {

            e.printStackTrace();
        }
    }


    public WebElement findWithCapture(WebDriver drvr, By byArg) {
        WebElement elmt = drvr.findElement(byArg);
        return elmt;
    }

    @AfterTest
    public void clear() {
        driver.quit();
    }
}
