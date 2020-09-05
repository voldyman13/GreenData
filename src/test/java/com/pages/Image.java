package com.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Image extends PageBase {

    @FindBy(how = How.TAG_NAME, using = "h1")
    @CacheLookup
    public WebElement header;

    @FindBy(id = "login_button")
    private WebElement enterButton;
    @FindBy(id = "login_button_domain")
    private WebElement anotherAccount;
    @FindBy(id = "login_button_current")
    private WebElement currentAccount;
    @FindBy(id = "username")
    private WebElement loginField;
    @FindBy(id = "password")
    private WebElement passwordField;

    public Image(WebDriver webDriver) {
        super(webDriver);
    }

    @Step
    public void clickOnLoginField() { loginField.click(); }

    @Step
    public void clearLoginField() { loginField.clear(); }

    @Step
    public void clickOnPasswordField() { passwordField.click(); }

    @Step
    public void clearPasswordField() { passwordField.clear(); }

    @Step
    public void mouseOnEnterButton(){
        Actions action = new Actions(driver);
        action.moveToElement(enterButton).perform();
    }

    @Step
    public void mouseOnAnotherAccount(){
        Actions action = new Actions(driver);
        action.moveToElement(anotherAccount).perform();
    }

    @Step
    public void mouseOnCurrentAccount(){
        Actions action = new Actions(driver);
        action.moveToElement(currentAccount).perform();
    }

    @Step
    public void readImgAndSave(String filename, String format) throws IOException {
        String fileName = "C:/Users/User/Documents/GitHub/GreenData/images/" + filename;
        Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies
                .viewportPasting(3000)).takeScreenshot(driver);
        ImageIO.write(screenshot.getImage(), format, new File(fileName));
    }

    @Step
    public void readImgAndCompare(String filename) throws IOException {
        String fileName = "C:/Users/User/Documents/GitHub/GreenData/images/" + filename;
        Screenshot elementScreenshot = new AShot().takeScreenshot(driver);
        BufferedImage actualImage = elementScreenshot.getImage();
        BufferedImage expectedImage = ImageIO.read(new File(fileName));
        ImageDiffer imgDiff = new ImageDiffer();
        ImageDiff diff = imgDiff.makeDiff(actualImage, expectedImage);
        Assert.assertTrue(diff.hasDiff(), "The image differs from the standard");
    }

}
