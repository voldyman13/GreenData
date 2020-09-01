package com.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

/**
 * Abstract class representation of a Page in the UI. Page object pattern
 */
public abstract class PageBase {

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

//    @Step
//    public void waitUntilElementVisibilityOf(WebElement element, int timeout) {
//        WebDriverWait wait = new WebDriverWait(driver, timeout);
//        wait.until(ExpectedConditions.visibilityOf(element));
//    }

//    @Step
//    public void waitUntilElementIsClickable(WebElement element, int timeout) {
//        WebDriverWait wait = new WebDriverWait(driver, timeout);
//        wait.until(ExpectedConditions.elementToBeClickable(element));
//    }

//    @Step
//    public void waitUntilPageIsLoaded(int timeout, String pageTitle) {
//        WebDriverWait wait = new WebDriverWait(driver, timeout, 500);
//        wait.until(ExpectedConditions.titleContains(pageTitle));
//    }


    @Step("Title: {value}")
    public void currentPageCheckByTitle(String value, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout, 500);
        wait.until(ExpectedConditions.titleContains(value));
        Assert.assertEquals(getTitle(), value, "It is not right page");
    }

//    @Step
//    public void getText() {
//    }
}
