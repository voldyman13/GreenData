package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * Sample page
 */
public class LoginPage extends Page {

  @FindBy(how = How.TAG_NAME, using = "h1")
//  @findBy()
  @CacheLookup
  public WebElement header;

  public LoginPage(WebDriver webDriver) {
    super(webDriver);
  }
  public void openSite(String url){
    driver.get(url);
  }
  public void inputLogin(String username){
    type(By.id("username"), username);
  }
  public void inputPassword(String password){
    type(By.id("password"), password);
  }
  public void clickOnEnterButton(){
    click(By.id("login_button"));
  }
  public boolean errorAlertConfirmation(){ return isElementPresent(By.id("error")); }
  public void waitUntilLoginPageIsLoaded(){ waitUntilPageIsLoaded(30, By.id("login_button")); }

}
