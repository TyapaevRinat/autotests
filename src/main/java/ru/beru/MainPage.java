package ru.beru;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class MainPage extends Preset {

    private String cityName;

    private WebElement giveAccountInfoButton() {
        return driver.findElement(By.cssSelector(".header2-nav__user"));
    }

    @Step("Account entry button press")
    public LoginPage clickButtonAccount() {
        giveAccountInfoButton().click();
        return new LoginPage();
    }

    @Step("User email check")
    public void checkProfileChanged() {
        String name = driver.findElement(By.cssSelector(".header2-nav__user .header2-nav-item__text"))
                .getAttribute("innerHTML");
        Assert.assertEquals("Мой профиль", name);
    }

    private WebElement findCurrentCity() {
        return driver.findElement(By.cssSelector("[class*='__region'] [class*='__inner']"));
    }

    @Step("Current city click")
    public void clickCityInner() {
        findCurrentCity().click();
    }

    @Step("New city pick")
    public void changeCityName(String cityName) {
        this.cityName = cityName;
        WebElement CityForm = (new WebDriverWait(driver, 20)
                .until(ExpectedConditions.presenceOfElementLocated(By
                        .cssSelector("[class*='region-select-form']"))));
        WebElement city = CityForm.findElement(By.cssSelector("[class*='region-suggest'] [class*='input__control']"));
        city.click();
        city.sendKeys(cityName);
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOf(city.
                        findElement(By.xpath("//strong[text()[contains(.,\'" + cityName + "\')]]"))));
        city.sendKeys(Keys.ENTER);
        (new WebDriverWait(driver, 20))
                .until(ExpectedConditions.invisibilityOf(
                        driver.findElement(By.cssSelector("[class*='suggestick-list']"))));
        city.sendKeys(Keys.ENTER);
        driver.navigate().refresh();
    }

    @Step("City name changed check")
    public void checkCityName() {
        Assert.assertEquals(findCurrentCity().getAttribute("textContent"), cityName);
    }

    @Step("Personal account entry")
    public Profile goToMyProfile() {
        (new Actions(driver)).moveToElement(giveAccountInfoButton()).build().perform();
        WebElement addresses = driver.findElement(By.cssSelector("[class*='item_type_addresses']"));
        addresses.click();
        return new Profile();
    }

    @Step("Request input")
    public void findItem(String item) {
        WebElement searchField = driver.findElement(By.id("header-search"));
        searchField.click();
        searchField.sendKeys(item);
        searchField.sendKeys(Keys.ENTER);
    }

    @Step("Subscribe button press")
    public void subscribeInit() {
        WebElement btn = driver.findElement(By.cssSelector("[class*='THqSbzx07u'] [class*='_2w0qPDYwej']"));
        btn.click();
    }

    @Step("Subscription email input")
    public void inputEmail(String email){
        WebElement mailBox = driver.findElement(By.cssSelector("._2CaNYhWZT5"));
        mailBox.sendKeys(email);
    }

    @Step("Submit subscription button press")
    public void submit(){
        WebElement submitBtn = driver.findElement(By.cssSelector("[class*='_3OWdR9kZRH'] [class*='_2w0qPDYwej']"));
        submitBtn.click();
    }

    @Step("No email message check")
    public void checkNoMail(){
        WebElement text = driver.findElement(By.cssSelector(".W-B6JRTjJH"));
        Assert.assertEquals(text.isDisplayed(), true);
    }
}
