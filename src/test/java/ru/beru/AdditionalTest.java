package ru.beru;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestListener.class)
public class AdditionalTest extends Preset {

    @Test
    public void additionalTest() {
        //Test for checking news subscription on main page
        MainPage page = new MainPage();
        page.subscribeInit();
        page.submit();
        page.checkNoMail();
        page.inputEmail("sampletext11@yandex.ru");
        page.submit();
        page.checkDone();
    }
}
