package com.pages;

import io.qameta.allure.Step;
import net.bytebuddy.asm.Advice;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
public abstract class PageBase {
    private Actions action;

//    @FindBy(id = "login_button")
//    private WebElement enterButton;

    protected WebDriver driver;

    public PageBase(WebDriver driver) {
        this.driver = driver;
    }

    @Step
    public String getTitle() {
        return driver.getTitle();
    }

    @Step
    public void stop() {
        driver.quit();
    }

    @Step
    public void type(WebElement element, String text) {
        element.click();
        element.clear();
        element.sendKeys(text);
    }

    @Step
    public boolean isElementPresent(WebElement element) {
        if (element.isEnabled()) {
            return true;
        } else return false;
    }

    @Step("Title: {value}")
    public void currentPageCheckByTitle(String value, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout, 500);
        wait.until(ExpectedConditions.titleContains(value));
        Assert.assertEquals(getTitle(), value, "It is not right page");
    }


}
