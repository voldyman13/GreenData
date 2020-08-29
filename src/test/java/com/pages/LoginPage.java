package com.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * Sample page
 */
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

  @Step
  public void openSite(String url) {
    driver.get(url);
  }

  @Step("Enter login: {value}")
  public void inputLogin(String username) {
    type(loginField, username);
  }

  @Step("Enter password: {value}")
  public void inputPassword(String password) {
    type(passwordField, password);
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

  @Step
  public void userNameCheck(String username) {
    String user = userName.getText();
    System.out.println("User: " + userName.getText());
    Assert.assertEquals(user, username, "Wrong user name");
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
  public void waitUntilElementIsClickable(WebElement element, int timeout) {
    WebDriverWait wait = new WebDriverWait(driver, timeout);
    wait.until(ExpectedConditions.elementToBeClickable(element));
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