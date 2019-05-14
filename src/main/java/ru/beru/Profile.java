package ru.beru;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class Profile extends Preset {

    @Step("Current city search")
    public WebElement findCityInner() {
        return driver.findElement(By.cssSelector("[class*='settings-list_type_region'] [class*='__inner']"));
    }

    @Step("Delivery address search")
    public WebElement findDeliveryAddress() {
        return driver.findElement(By.cssSelector("[class*='__region'] [class*='__inner']"));
    }

    @Step("Delivery address in city check")
    public void checkAddresses() {
        Assert.assertEquals(findCityInner().getAttribute("textContent"),
                findDeliveryAddress().getAttribute("textContent"));
    }
}