package ru.beru;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestListener.class)
public class LoginTest extends Preset {

    @Test
    public void loginTest() {
        MainPage mainPage = new MainPage();
        LoginPage loginPage = mainPage.clickButtonAccount();
        loginPage.enterLogin("sampletext11");
        loginPage.enterPassword("sampletext1101");
        mainPage.checkProfileChanged();
    }
}
