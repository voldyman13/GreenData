package com.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class MainPage extends PageBase {

    @FindBy(how = How.TAG_NAME, using = "h1")
    @CacheLookup
    public WebElement header;


    @FindBy(className = "login-logo")
    private WebElement greenDataLogo;
    @FindBy(className ="svg-bg-path")
    private WebElement userIcon;
    @FindBy(className ="username-section")
    private WebElement userMenu;
    @FindBy(xpath = "//ul[1]/li[8]")
    private WebElement logOut;

    public MainPage(WebDriver webDriver) { super(webDriver); }

    @Step
    public void openUserMenu(){ userMenu.click(); }

    @Step
    public void Logout(){ logOut.click(); }

    @Step
    public void currentPageCheckByLogo(String pageTitle, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout, 500);
        wait.until(ExpectedConditions.visibilityOf(greenDataLogo));
        Assert.assertEquals(getTitle(), pageTitle, "It is not right page");
    }
}
