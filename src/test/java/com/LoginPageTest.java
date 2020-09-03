package com;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.pages.LoginPage;
import com.pages.MainPage;

import java.awt.*;

public class LoginPageTest extends TestBase {
  private LoginPage loginpage;
  private MainPage mainpage;

  String baseUrl = "https://gdcloud.ru/release-17/";
  String loginPositive = "tester";
  String loginNegative = "stranger";
  String passwordPositive = "K35G3U";
  String passwordNegative = "12345678";
  String loginPage = "WorkFlow";
  String mainPage = "Лента - WorkFlow";
  String userName = "Скворцофф А. И. (Jinior QA. Dptt)";

  @BeforeMethod(alwaysRun = true)
  public void initPageObjects() {
    loginpage = PageFactory.initElements(driver, LoginPage.class);
    mainpage = PageFactory.initElements(driver, MainPage.class);
    loginpage.openSite(baseUrl);
  }

  @Test(groups = {"LoginPageTests", "PositiveTests", "InterfaceTests"}, priority = 1)
  public void loginPageTitleTest(){
    loginpage.currentPageCheckByTitle(loginPage, 10);
  }

  @Test(groups = {"LoginTests", "PositiveTests"}, priority = 0)
  public void loginPositiveTest(){
    loginpage.inputLogin(loginPositive);
    loginpage.inputPassword(passwordPositive);
    loginpage.clickOnEnterButton();
    loginpage.currentPageCheckByTitle(mainPage, 10);
    loginpage.userNameCheck(userName);

  }
  @Test(groups = {"LoginTests", "PositiveTests"}, priority = 1)
  public void loginPositiveRememberCheckBoxTest() throws AWTException {
    loginpage.inputLogin(loginPositive);
    loginpage.inputPassword(passwordPositive);
    loginpage.checkInRememberMe();
    loginpage.clickOnEnterButton();
    loginpage.currentPageCheckByTitle(mainPage, 30);
    loginpage.openSiteInNewTab(baseUrl);
    loginpage.currentPageCheckByTitle(mainPage, 60);
    loginpage.userNameCheck(userName);
  }

  @Test(groups = {"LoginTests", "NegativeTests"}, priority = 2)
  public void loginNegativeWrongPasswordTest(){
    loginpage.inputLogin(loginPositive);
    loginpage.inputPassword(passwordNegative);
    loginpage.clickOnEnterButton();
    loginpage.errorMessageCheck();
    loginpage.currentPageCheckByTitle(loginPage, 0);
  }

  @Test(groups = {"LoginTests", "NegativeTests"}, priority = 2)
  public void loginNegativeWrongLoginTest(){
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
    loginpage.inputLogin(loginPositive);
    loginpage.inputPassword(passwordPositive);
    loginpage.clickOnEnterButton();
    loginpage.currentPageCheckByTitle(mainPage, 30);
    loginpage.openSiteInNewTab(baseUrl);
    loginpage.currentPageCheckByTitle(mainPage, 60);
    loginpage.userNameCheck(userName);
  }

// enter positive login & password, click on Another Account Button, expect error message
  @Test(groups = {"LoginTests", "NegativeTests"}, priority = 2)
  public void loginNegativeAnotherAccountTest(){
    loginpage.inputLogin(loginPositive);
    loginpage.inputPassword(passwordPositive);
    loginpage.clickOnAnotherAccountButton();
    loginpage.errorMessageCheck();
    loginpage.currentPageCheckByTitle(loginPage, 0);
  }

// enter positive login & password, click on Current Account Button, expect error message
//  I don't know, but I think it can work on different browsers at the same time
  @Test(groups = {"LoginTests", "NegativeTests"}, priority = 1)
  public void loginNegativeCurrentAccountTest(){
    loginpage.inputLogin(loginPositive);
    loginpage.inputPassword(passwordPositive);
    loginpage.clickOnCurrentAccountButton();
    loginpage.errorMessageCheck();
    loginpage.currentPageCheckByTitle(loginPage, 0);
  }

  @AfterMethod
  public void TearDown(){
    loginpage.stop();
  }
}
