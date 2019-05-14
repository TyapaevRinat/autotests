package ru.beru;

import io.qameta.allure.Attachment;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.Augmenter;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.cropper.indent.BlurFilter;
import ru.yandex.qatools.ashot.cropper.indent.IndentCropper;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class ScreenTaker {
    private WebDriver driver;
    private String path;

    ScreenTaker(WebDriver driver, String path) {
        this.driver = driver;
        this.path = path;
    }

    @Attachment(value = "{0}", type = "image/png")
    public byte[] saveAllureScreenshotError(String name) {
        return ((TakesScreenshot) (new Augmenter().augment(driver)))
                .getScreenshotAs(OutputType.BYTES);
    }

    void screenPage(String name) {
        File screenshot = ((TakesScreenshot)(new Augmenter().augment(driver)))
                .getScreenshotAs(OutputType.FILE);
        try {
            BufferedImage img = ImageIO.read(screenshot);
            File to = new File(path + "\\" +  name + ".png");
            ImageIO.write(img, "png", to);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
