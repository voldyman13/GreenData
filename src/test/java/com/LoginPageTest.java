package com;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.pages.LoginPage;
import com.pages.MainPage;

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

  @BeforeMethod(alwaysRun = true)
  public void initPageObjects() {
    loginpage = PageFactory.initElements(driver, LoginPage.class);
    mainpage = PageFactory.initElements(driver, MainPage.class);
    loginpage.openSite(baseUrl);
  }

  @Test(groups = {"LoginPageTests", "PositiveTests", "InterfaceTests"}, priority = 1)
  public void loginPageTitleTest(){
    loginpage.rightPageConfirmation(loginPage);
  }

  @Test(groups = {"LoginTests", "PositiveTests"}, priority = 0)
  public void loginPositiveTest(){
    loginpage.inputLogin(loginPositive);
    loginpage.inputPassword(passwordPositive);
    loginpage.clickOnEnterButton();
    loginpage.waitUntilPageIsLoaded(60, mainPage);
    loginpage.rightPageConfirmation(mainPage);
  }

  @Test(groups = {"LoginTests", "PositiveTests"}, priority = 0)
  public void loginPositiveRememberCheckBoxTest() {
    loginpage.inputLogin(loginPositive);
    loginpage.inputPassword(passwordPositive);
    loginpage.markCheckboxRemember();
    loginpage.clickOnEnterButton();
    loginpage.waitUntilPageIsLoaded(60, mainPage);
    loginpage.rightPageConfirmation(mainPage);
    loginpage.openSite(baseUrl);
    loginpage.waitUntilPageIsLoaded(60, mainPage);
    loginpage.rightPageConfirmation(mainPage);
  }

  @Test(groups = {"LoginTests", "NegativeTests"}, priority = 0)
  public void loginNegativeWrongPasswordTest(){
    loginpage.inputLogin(loginPositive);
    loginpage.inputPassword(passwordNegative);
    loginpage.clickOnEnterButton();
    loginpage.errorAlertConfirmation();
    loginpage.rightPageConfirmation(loginPage);
  }

  @Test(groups = {"LoginTests", "NegativeTests"}, priority = 2)
  public void loginNegativeWrongLoginTest(){
    loginpage.inputLogin(loginNegative);
    loginpage.inputPassword(passwordPositive);
    loginpage.clickOnEnterButton();
    loginpage.errorAlertConfirmation();
    loginpage.rightPageConfirmation(loginPage);
  }

  @AfterMethod
  public void TearDown(){
    loginpage.stop();
  }
}
