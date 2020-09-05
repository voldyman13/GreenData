package com;

import com.pages.LoginPage;
import com.pages.MainPage;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MainPageTest extends TestBase {
    private LoginPage loginpage;
    private MainPage mainpage;

    String baseUrl = "https://gdcloud.ru/release-17/";
    String loginPositive = "tester";
    String passwordPositive = "K35G3U";
    String loginPage = "WorkFlow";
    String mainPage = "Лента - WorkFlow";

    @BeforeMethod(alwaysRun = true)
    public void initPageObjects() {
        loginpage = PageFactory.initElements(driver, LoginPage.class);
        mainpage = PageFactory.initElements(driver, MainPage.class);
        loginpage.openSite(baseUrl);
    }

// This section is for practice only, not for a specific task.  -----------------------
    @Test(groups = {"MainPageTests", "Positive"}, priority = 0)
    public void logoutPositiveTest(){
        loginpage.openSite(baseUrl);
        loginpage.inputLogin(loginPositive);
        loginpage.inputPassword(passwordPositive);
        loginpage.clickOnEnterButton();
        loginpage.currentPageCheckByTitle(mainPage, 30);
        mainpage.openUserMenu();
        mainpage.Logout();
        mainpage.currentPageCheckByLogo(loginPage, 300);
    }
    @AfterMethod(alwaysRun= true)
    public void TearDown(){
        mainpage.stop();
    }
}
