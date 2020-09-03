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

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class LoginPage extends PageBase {

  @FindBy(how = How.TAG_NAME, using = "h1")
  @CacheLookup
  public WebElement header;

  @FindBy(id = "username")
  private WebElement loginField;
  @FindBy(id = "password")
  private WebElement passwordField;
  @FindBy(id = "remember")
  private WebElement rememberCheckbox;
  @FindBy(id = "login_button")
  private WebElement enterButton;
  @FindBy(id = "login_button_domain")
  private WebElement anotherAccount;
  @FindBy(id = "login_button_current")
  private WebElement currentAccount;
  @FindBy(id = "error")
  private WebElement errorWarning;
  @FindBy(className = "svg-bg-path")
  private WebElement icon;
  @FindBy(id = "userName")
  private WebElement userName;

  public LoginPage(WebDriver webDriver) {
    super(webDriver);
  }

  @Step("url: {value}")
  public void openSite(String value) {
    driver.get(value);
  }

  @Step("Enter login: {value}")
  public void inputLogin(String value) {
    type(loginField, value);
  }

  @Step("Enter password: {value}")
  public void inputPassword(String value) {
    type(passwordField, value);
  }

  @Step
  public void checkInRememberMe() {
    rememberCheckbox.click();
  }

  @Step
  public void clickOnEnterButton() {
    enterButton.click();
  }

  @Step
  public void errorMessageCheck() {
    Boolean check = isElementPresent(errorWarning);
    Assert.assertTrue(check);
  }

  @Step("User: {value}")
  public void userNameCheck(String value) {
    String user = userName.getText();
    Assert.assertEquals(user, value, "Wrong user name");
  }

  @Step
  public void openSiteInNewTab(String url) throws AWTException {
    Robot robot = new Robot();
    robot.keyPress(KeyEvent.VK_CONTROL);
    robot.keyPress(KeyEvent.VK_T);
    robot.keyRelease(KeyEvent.VK_CONTROL);
    robot.keyRelease(KeyEvent.VK_T);
    ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
    driver.switchTo().window(tabs.get(1));
    driver.get(url);
  }

  @Step
  public void clickOnAnotherAccountButton() {
    anotherAccount.click();
  }

  @Step
  public void clickOnCurrentAccountButton() {
    currentAccount.click();
  }

}