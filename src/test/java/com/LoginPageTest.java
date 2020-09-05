package com;

import com.pages.Image;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.pages.LoginPage;
import com.pages.MainPage;

import java.awt.*;
import java.io.IOException;

public class LoginPageTest extends TestBase {
  private LoginPage loginpage;
  private MainPage mainpage;
  private Image image;

  String baseUrl = "https://gdcloud.ru/release-17";
  String loginPositive = "tester";
  String loginNegative = "stranger";
  String passwordPositive = "K35G3U";
  String passwordNegative = "12345678";
  String loginPage = "WorkFlow";
  String mainPage = "Лента - WorkFlow";
  String userName = "Скворцофф А. И. (Jinior QA. Dptt)";
  String loginPageImg = "loginPageImg.jpg";
  String loginPositiveImg = "loginPositiveImg.jpg";
  String passwordNegativeImg = "passwordNegativeImg.jpg";
  String clickOnLoginFieldImg = "clickOnLoginFieldImg.jpg";
  String clickOnPasswordFieldImg = "clickOnPasswordFieldImg.jpg";
  String rememberMeImg = "rememberMeImg.jpg";
  String mouseOnEnterButtonImg = "mouseOnEnterButtonImg.jpg";
  String mouseOnCurrentAccountButtonImg = "mouseOnCurrentAccountButtonImg.jpg";
  String mouseOnAnotherAccountButtonImg = "mouseOnAnotherAccountButtonImg.jpg";
  String errorWarningImg = "errorWarningImg.jpg";
  String fillOutLoginImg = "fillOutLoginImg.jpg";
  String fillOutPasswordImg = "fillOutPasswordImg.jpg";

  @FindBy(className = "login-logo")
  private WebElement greenDataLogo;
  @FindBy(id = "K35G3U")
  private WebElement input_page;
  @FindBy(id = "login_button")
  private WebElement enterButton;
  @FindBy(id = "login_button_domain")
  private WebElement anotherAccount;
  @FindBy(id = "login_button_current")
  private WebElement currentAccount;
  @FindBy(id = "error")
  private WebElement errorWarning;

  @BeforeMethod(alwaysRun = true)
  public void initPageObjects() {
    loginpage = PageFactory.initElements(driver, LoginPage.class);
    mainpage = PageFactory.initElements(driver, MainPage.class);
    image = PageFactory.initElements(driver, Image.class);
  }

  //  This method is only for saving sample images
  @Test(alwaysRun = false)
  public void SaveImages() throws IOException {
    loginpage.openSite(baseUrl);
    image.readImgAndSave(loginPageImg, "jpg");
    image.clickOnLoginField();
    image.readImgAndSave(clickOnLoginFieldImg, "jpg");
    image.clickOnPasswordField();
    image.readImgAndSave(clickOnPasswordFieldImg, "jpg");
    loginpage.checkInRememberMe();
    image.readImgAndSave(rememberMeImg, "jpg");
    loginpage.checkInRememberMe();
    image.mouseOnEnterButton();
    image.readImgAndSave(mouseOnEnterButtonImg, "jpg");
    image.mouseOnAnotherAccount();
    image.readImgAndSave(mouseOnAnotherAccountButtonImg, "jpg");
    image.mouseOnCurrentAccount();
    image.readImgAndSave(mouseOnCurrentAccountButtonImg, "jpg");
    loginpage.inputLogin(loginPositive);
    image.readImgAndSave(loginPositiveImg, "jpg");
    image.clearLoginField();
    loginpage.inputPassword(passwordPositive);
    image.readImgAndSave(passwordNegativeImg, "jpg");
    image.clearPasswordField();
    loginpage.clickOnEnterButton();
    image.readImgAndSave(fillOutLoginImg, "jpg");
    loginpage.inputLogin(loginPositive);
    loginpage.clickOnEnterButton();
    image.readImgAndSave(fillOutPasswordImg, "jpg");
    loginpage.inputPassword(passwordNegative);
    loginpage.clickOnEnterButton();
    image.readImgAndSave(errorWarningImg, "jpg");
  }

  @Test(groups = {"LoginPage", "Positive", "Interface"}, priority = 1)
  public void loginPageTitleTest(){
    loginpage.openSite(baseUrl);
    loginpage.currentPageCheckByTitle(loginPage, 30);
  }

  @Test(groups = {"LoginPage", "Positive", "Interface"}, priority = 0)
  public void loginPageInterfaceTests() throws IOException{
    loginpage.openSite(baseUrl);
    image.readImgAndCompare(loginPageImg);
    image.clickOnLoginField();
    image.readImgAndCompare(clickOnLoginFieldImg);
    image.clickOnPasswordField();
    image.readImgAndCompare(clickOnPasswordFieldImg);
    loginpage.checkInRememberMe();
    image.readImgAndCompare(rememberMeImg);
    loginpage.checkInRememberMe();
    image.readImgAndCompare(loginPageImg);
    image.mouseOnEnterButton();
    image.readImgAndCompare(mouseOnEnterButtonImg);
    image.mouseOnAnotherAccount();
    image.readImgAndCompare(mouseOnAnotherAccountButtonImg);
    image.mouseOnCurrentAccount();
    image.readImgAndCompare(mouseOnCurrentAccountButtonImg);
    loginpage.inputLogin(loginPositive);
    image.readImgAndCompare(loginPositiveImg);
    image.clearLoginField();
    loginpage.inputPassword(passwordPositive);
    image.readImgAndCompare(passwordNegativeImg);
    image.clearPasswordField();
    loginpage.clickOnEnterButton();
    image.readImgAndCompare(fillOutLoginImg);
    loginpage.inputLogin(loginPositive);
    loginpage.clickOnEnterButton();
    image.readImgAndCompare(fillOutPasswordImg);
    loginpage.inputPassword(passwordNegative);
    loginpage.clickOnEnterButton();
    image.readImgAndCompare(errorWarningImg);
  }


  @Test(groups = {"LoginTests", "Positive"}, priority = 0)
  public void loginPositiveTest(){
    loginpage.openSite(baseUrl);
    loginpage.inputLogin(loginPositive);
    loginpage.inputPassword(passwordPositive);
    loginpage.clickOnEnterButton();
    loginpage.currentPageCheckByTitle(mainPage, 30);
    loginpage.userNameCheck(userName);
  }

  @Test(groups = {"LoginTests", "Positive"}, priority = 1)
  public void loginPositiveRememberCheckBoxTest() throws AWTException{
    loginpage.openSite(baseUrl);
    loginpage.inputLogin(loginPositive);
    loginpage.inputPassword(passwordPositive);
    loginpage.checkInRememberMe();
    loginpage.clickOnEnterButton();
    loginpage.currentPageCheckByTitle(mainPage, 50);
    loginpage.openSiteInNewTab(baseUrl);
    loginpage.currentPageCheckByTitle(mainPage, 60);
    loginpage.userNameCheck(userName);
  }

  @Test(groups = {"LoginTests", "Negative"}, priority = 2)
  public void loginNegativeWrongPasswordTest(){
    loginpage.openSite(baseUrl);
    loginpage.inputLogin(loginPositive);
    loginpage.inputPassword(passwordNegative);
    loginpage.clickOnEnterButton();
    loginpage.errorMessageCheck();
    loginpage.currentPageCheckByTitle(loginPage, 0);
  }

  @Test(groups = {"LoginTests", "Negative"}, priority = 2)
  public void loginNegativeWrongLoginTest(){
    loginpage.openSite(baseUrl);
    loginpage.inputLogin(loginNegative);
    loginpage.inputPassword(passwordPositive);
    loginpage.clickOnEnterButton();
    loginpage.errorMessageCheck();
    loginpage.currentPageCheckByTitle(loginPage, 10);
  }

// enter positive login & password, don't check in rememberMe checkBox, click on Enter  Button.
// Open new tab, open site there, expect something wrong. But everything is O'key
  @Test(groups = {"LoginTests", "Negative"}, priority = 1)
  public void loginNegativeRememberCheckBoxTest() throws AWTException {
    loginpage.openSite(baseUrl);
    loginpage.inputLogin(loginPositive);
    loginpage.inputPassword(passwordPositive);
    loginpage.clickOnEnterButton();
    loginpage.currentPageCheckByTitle(mainPage, 30);
    loginpage.openSiteInNewTab(baseUrl);
    loginpage.currentPageCheckByTitle(mainPage, 60);
    loginpage.userNameCheck(userName);
  }

// enter positive login & password, click on Another Account Button, expect error message
  @Test(groups = {"LoginTests", "Negative"}, priority = 2)
  public void loginNegativeAnotherAccountTest(){
    loginpage.openSite(baseUrl);
    loginpage.inputLogin(loginPositive);
    loginpage.inputPassword(passwordPositive);
    loginpage.clickOnAnotherAccountButton();
    loginpage.errorMessageCheck();
    loginpage.currentPageCheckByTitle(loginPage, 0);
  }

// enter positive login & password, click on Current Account Button, expect error message
//  I don't know, but I think it can work on different browsers at the same time
  @Test(groups = {"LoginTests", "Negative"}, priority = 1)
  public void loginNegativeCurrentAccountTest(){
    loginpage.openSite(baseUrl);
    loginpage.inputLogin(loginPositive);
    loginpage.inputPassword(passwordPositive);
    loginpage.clickOnCurrentAccountButton();
    loginpage.errorMessageCheck();
    loginpage.currentPageCheckByTitle(loginPage, 0);
  }

  @AfterMethod(alwaysRun= true)
  public void TearDown(){
    loginpage.stop();
  }
}
