package ru.beru;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CartPage extends Preset {
    private static List<WebElement> priceList = null;

    private int parseInt(String str) {
        Pattern pattern = Pattern.compile("[^\\d]");
        Matcher matcher = pattern.matcher(str);
        return Integer.parseInt(matcher.replaceAll(""));
    }

    @Step("Regular price")
    private int regularPrice() {
        String tempText = priceList.get(0).findElement(By.cssSelector("[data-auto*='value']"))
                .getAttribute("textContent");
        return parseInt(tempText);
    }

    @Step("Delivery price")
    private int deliveryPrice() {
        String tempText = priceList.get(1).findElement(By.cssSelector("[data-auto*='value']"))
                .getAttribute("textContent");
        return tempText.contains("бесплатно") ? 0 : parseInt(tempText);
    }

    @Step("Discount")
    private int discount() {
        int salary = 0;
        if (priceList.size() == 4) {
            String tempText = priceList.get(2).findElement(
                    By.xpath("//span[text()[contains(., 'Скидка')]]/following-sibling::span"))
                    .getAttribute("textContent");
            salary = parseInt(tempText);
        }
        return salary;
    }

    @Step("Total price")
    private int totalPrice(int index) {
        String tempText = priceList.get(index).findElement(
                By.cssSelector("[class*='_1oBlNqVHPq']"))
                .getAttribute("textContent");
        return parseInt(tempText);
    }

    @Step("Correct price check")
    public void checkPriceCorrect() {
        priceList = driver.findElements(By.cssSelector("[class *= '_1Q9ASvPbPN']"));
        int sale = discount();
        int index = sale == 0 ? 2 : 3;
        Assert.assertEquals(regularPrice() + deliveryPrice() - sale, totalPrice(index));
    }

    @Step("Free delivery check")
    public void checkFreeDelivery() {
        String priceStr = priceList.get(1).findElement(By.cssSelector("[data-auto*='value']"))
                .getAttribute("textContent").replace(" ", "");
        Assert.assertTrue(priceStr.contains("бесплатно"));
    }

    @Step("One more item addition")
    public void addProd(int priceLimit) {
        String priceStr = driver.findElement(By.xpath("//div[@data-auto='CartOfferPrice']/span/span/span"))
                .getAttribute("textContent");
        int price = parseInt(priceStr);
        while (price < priceLimit) {
            driver.findElement(By.xpath("//button//span[text()='+']")).click();
            priceStr = driver.findElement(By.xpath("//div[@data-auto='CartOfferPrice']/span/span/span"))
                    .getAttribute("textContent");
            price = parseInt(priceStr);
        }
    }

    @Step("Free delivery message check")
    public void checkDeliveryMsg(String textDelivery) {
        WebElement freeDelivery = driver.findElement(By.cssSelector("[class *= '_3EX9adn_xp']"));
        Assert.assertTrue(freeDelivery.getAttribute("textContent").contains(textDelivery));
    }

    public void checkFreeDeliveryLabel(String title) {
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.attributeContains(
                        By.cssSelector("[class*='_3EX9adn_xp']"), "textContent", title));
        checkDeliveryMsg(title);
    }
}
