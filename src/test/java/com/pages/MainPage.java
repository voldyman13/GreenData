package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class MainPage extends Page {

    @FindBy(how = How.TAG_NAME, using = "h1")
    @CacheLookup
    public WebElement header;
    public MainPage(WebDriver webDriver) {
            super(webDriver);
        }

    public boolean mainPageConfirmation(){
        return isElementPresent(By.cssSelector("div.support-button i.material-icons.gd-icon"));
    }
    public void waitUntilMainPageIsLoaded(){
        waitUntilPageIsLoaded(30, By.cssSelector("div.support-button i.material-icons.gd-icon"));
    }


}
