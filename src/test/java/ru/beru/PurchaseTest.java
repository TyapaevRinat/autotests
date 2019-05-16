package ru.beru;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestListener.class)

public class PurchaseTest extends Preset{
    @Test
    public void purchaseTest() {
        MainPage page = new MainPage();
        page.findItem("Электрические зубные щетки");
        SearchPage searchPage = new SearchPage();
        searchPage.inputMinPrice(999);
        searchPage.inputMaxPrice(1999);
        searchPage.showAllProducts();
        searchPage.getAllProductsList();
        searchPage.checkListNotEmpty();
        searchPage.checkPriceInRange(999, 1999);
        searchPage.addToCart();
        CartPage cartPage = searchPage.gotoCart();
        cartPage.checkDeliveryMsg("бесплатной доставки осталось");
        cartPage.checkPriceCorrect();
        cartPage.addProd(2999);
        cartPage.checkFreeDeliveryLabel("Поздравляем!");
        cartPage.checkFreeDelivery();
        cartPage.checkPriceCorrect();
    }
}