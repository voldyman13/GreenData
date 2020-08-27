package com;

import org.openqa.selenium.support.PageFactory;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.pages.HomePage;

public class SampleTestNgTest extends TestNgTestBase {

  private HomePage homepage;

  @BeforeMethod
  public void initPageObjects() {
    homepage = PageFactory.initElements(driver, HomePage.class);
  }

  @Test
  public void login(){
    homepage.openSite(baseUrl);
    homepage.inputLogin("tester");
    homepage.inputPassword("K35G3U");
    homepage.submit();
    Assert.assertTrue("WorkFlow".equals(homepage.getTitle()));
  }

//  @Test
//  public void testHomePageHasAHeader() {
//    driver.get(baseUrl);
//    Assert.assertFalse("".equals(homepage.header.getText()));
//  }
}
