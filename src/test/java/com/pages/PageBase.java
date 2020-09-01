package com.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

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

    @Step("Title: {value}")
    public void currentPageCheckByTitle(String value, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout, 500);
        wait.until(ExpectedConditions.titleContains(value));
        Assert.assertEquals(getTitle(), value, "It is not right page");
    }

}
