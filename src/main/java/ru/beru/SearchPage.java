package ru.beru;

import io.qameta.allure.Step;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

public class SearchPage extends Preset {
    private List<WebElement> productList;

    @Step("Min price input")
    public void inputMinPrice(int price) {
        WebElement priceField = driver.findElement(By.id("glpricefrom"));
        priceField.click();
        String priceStr = Integer.toString(price);
        priceField.sendKeys(priceStr);
        WebElement wind = driver.findElement(By.cssSelector("[class*='_1PQIIOelRL']"));
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOf(wind));
    }

    @Step("Max price input")
    public void inputMaxPrice(int price) {
        WebElement priceField = driver.findElement(By.id("glpriceto"));
        priceField.click();
        String priceStr = Integer.toString(price);
        priceField.sendKeys(priceStr);
        WebElement wind = driver.findElement(By.cssSelector("[class*='_1PQIIOelRL']"));
        (new WebDriverWait(driver, 40)).until(ExpectedConditions.visibilityOf(wind));
    }

    @Step("Show all products in price range")
    public void showAllProducts() {
        while(true) {
            try {
                WebElement showMore = driver.findElement(By.cssSelector(".n-pager-more__button"));
                showMore.click();
            } catch (Exception e) {
                break;
            }
        }
    }

    @Step("Get all products list")
    public void getAllProductsList() {
        final int cnt = Integer.parseInt(
                driver.findElement(By.cssSelector(".n-search-preciser__results-count"))
                        .getAttribute("textContent").split(" ")[1]);
        (new WebDriverWait(driver,10)).until(new ExpectedCondition<Boolean>(){
            public Boolean apply(WebDriver driver) {
                return driver.findElements(By
                        .cssSelector("[class*='grid-snippet_react']")).size() == cnt;
            }
        });
        productList = driver.findElements(By.cssSelector("[class*='grid-snippet_react']"));
    }

    @Step("Products list not empty check")
    public void checkListNotEmpty() {
        Assert.assertNotNull(productList);
        Assert.assertTrue(productList.size() != 0);
    }

    @Step("Price range check")
    public void checkPriceInRange(int minPrice, int maxPrice) {
        boolean corr = true;
        JSONParser parser = new JSONParser();
        for (WebElement element : productList) {
            try {
                JSONObject jsonObject = (JSONObject) parser.parse(element.getAttribute("data-bem"));
                int price = Integer.parseInt((((JSONObject)
                        ((JSONObject) jsonObject.get("b-zone")).get("data")).get("price")).toString());
                if (price  < minPrice || price > maxPrice) {
                    corr = false;
                    break;
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        Assert.assertTrue(corr);
    }

    @Step("Product to cart addition")
    public void addToCart() {
        productList.get(productList.size() - 2).findElement(By.cssSelector("[class*='_2w0qPDYwej']")).click();
        (new WebDriverWait(driver, 20))
                .until(ExpectedConditions.presenceOfElementLocated(By
                        .cssSelector("[class*='_1sjxYfIabK _26mXJDBxtH']")));
    }

    @Step("Show cart")
    public CartPage gotoCart() {
        productList.get(productList.size() - 2).findElement(By.cssSelector("[class*='_2w0qPDYwej']")).click();
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[class*='_3AlSA6AOKL']")));
        return new CartPage();
    }
}
