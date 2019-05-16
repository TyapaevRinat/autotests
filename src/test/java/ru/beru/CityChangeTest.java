package ru.beru;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestListener.class)
public class CityChangeTest extends Preset {
    @DataProvider(name="SearchProvider")
    public Object[][] getDataFromDataprovider(){
        return new Object[][]
                {
                        { "Хвалынск" },
                        { "Энгельс" },
                        { "Балаково" }
                };
    }

    @Test(dataProvider="SearchProvider")
    public void cityChangeTest(String cityName) {
        MainPage page = new MainPage();
        page.clickCityInner();
        page.changeCityName(cityName);
        page.checkCityName();
        LoginPage loginPage = page.clickButtonAccount();
        String email = "sampletext11";
        loginPage.enterLogin(email);
        String password = "sampletext1101";
        loginPage.enterPassword(password);
        Profile profile = page.goToMyProfile();
        profile.findCityInner();
        profile.findDeliveryAddress();
        profile.checkAddresses();
    }
}
