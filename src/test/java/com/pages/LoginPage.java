package com.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;

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
  @FindBy(className ="svg-bg-path")
  private WebElement icon;

  public LoginPage(WebDriver webDriver) { super(webDriver); }

  @Step
  public void openSite(String url){ driver.get(url); }

  @Step("Enter login: {value}")
  public void inputLogin(String username){ type(loginField, username); }

  @Step("Enter password: {value}")
  public void inputPassword(String password){ type(passwordField, password); }

  @Step
  public void markCheckboxRemember(){rememberCheckbox.click(); }

  @Step
  public void clickOnEnterButton(){ enterButton.click(); }

  @Step
  public void errorAlertConfirmation(){
    Boolean check = isElementPresent(errorWarning);
    Assert.assertTrue(check);
  }

}
