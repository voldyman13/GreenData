package com;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.pages.LoginPage;
import com.pages.MainPage;

public class LoginPageTest extends TestNgTestBase {
  private LoginPage loginpage;
  private MainPage mainpage;

  String baseUrl = "https://gdcloud.ru/release-17/";
  String loginPositive = "tester";
  String loginNegative = "stranger";
  String passwordPositive = "K35G3U";
  String passwordNegative = "12345678";
  String loginPageTitle = "WorkFlow";
  String mainPageTitle = "Лента - WorkFlow";



  @BeforeMethod(alwaysRun = true)
  public void initPageObjects() {
    loginpage = PageFactory.initElements(driver, LoginPage.class);
    mainpage = PageFactory.initElements(driver, MainPage.class);
  }
  @Test(groups = {"LoginPageTests", "PositiveTests", "InterfaceTests"}, priority = 1)
  public void loginPageTitleTest(){
    loginpage.consoleLogBefore("loginPageTitleTest");
    loginpage.openSite(baseUrl);
    loginpage.waitUntilLoginPageIsLoaded();
    loginpage.printTitle();
    Assert.assertEquals(loginpage.getTitle(), loginPageTitle, "It is main page Title\"");
    loginpage.consoleLogAfter("loginPageTitleTest");
  }
  @Test(groups = {"LoginTests", "PositiveTests"}, priority = 0)
  public void loginPositiveTest(){
    loginpage.consoleLogBefore("loginPositiveTest");
    loginpage.openSite(baseUrl);
    loginpage.inputLogin(loginPositive);
    loginpage.inputPassword(passwordPositive);
    loginpage.clickOnEnterButton();
    mainpage.waitUntilMainPageIsLoaded();
    Assert.assertTrue(mainpage.mainPageConfirmation());
    loginpage.printTitle();
    Assert.assertEquals(mainpage.getTitle(), mainPageTitle, "It is main page Title");
    loginpage.consoleLogAfter("loginPositiveTest");

  }

  @Test(groups = {"LoginTests", "NegativeTests"}, priority = 0)
  public void loginNegativeWrongPasswordTest(){
    loginpage.consoleLogBefore("loginNegativeWrongPasswordTest");
    loginpage.openSite(baseUrl);
    loginpage.inputLogin(loginPositive);
    loginpage.inputPassword(passwordNegative);
    loginpage.clickOnEnterButton();
    Assert.assertTrue(loginpage.errorAlertConfirmation());
    loginpage.printTitle();
    Assert.assertNotEquals(loginpage.getTitle(), mainPageTitle, "It is not main page Title");
    loginpage.consoleLogAfter("loginNegativeWrongPasswordTest");
  }

  @Test(groups = {"LoginTests", "NegativeTests"}, priority = 2)
  public void loginNegativeWrongLoginTest(){
    loginpage.consoleLogBefore("loginNegativeWrongLoginTest");
    loginpage.openSite(baseUrl);
    loginpage.inputLogin(loginNegative);
    loginpage.inputPassword(passwordPositive);
    loginpage.clickOnEnterButton();
    Assert.assertTrue(loginpage.errorAlertConfirmation());
    loginpage.printTitle();
    Assert.assertNotEquals(loginpage.getTitle(), mainPageTitle, "It is not main page Title");
    loginpage.consoleLogAfter("loginNegativeWrongLoginTest");
  }

  @AfterMethod
  public void TearDown(){
    driver.quit();
  }
}
