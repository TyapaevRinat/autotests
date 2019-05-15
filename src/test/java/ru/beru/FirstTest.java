package ru.beru;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestListener.class)
public class FirstTest extends Preset {

    @Test
    public void first_test() {
        MainPage mainPage = new MainPage();
        LoginPage loginPage = mainPage.clickButtonAccount();
        loginPage.enterLogin("sampletext11");
        loginPage.enterPassword("sampletext1101");
        mainPage.checkProfileChanged();
    }
}
