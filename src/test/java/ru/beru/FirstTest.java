package ru.beru;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;




public class FirstTest extends PageInteraction {


    @Test
    public void firstTest() {
        WebElement element = findWithCapture(driver, By.cssSelector(".header2-nav__user"));
        element.click();
        element = findWithCapture(driver, By.cssSelector("#passp-field-login"));
        element.sendKeys("sampletext11");
        element.sendKeys(Keys.ENTER);
        sleep(1000);
        element = findWithCapture(driver, By.cssSelector("#passp-field-passwd"));
        element.sendKeys("sampletext1101");
        element.sendKeys(Keys.ENTER);
        sleep(1000);
        String name = findWithCapture(driver, By.cssSelector(".header2-nav__user .header2-nav-item__text")).getAttribute("innerHTML");
        Assert.assertEquals("Мой профиль", name);

    }
}
