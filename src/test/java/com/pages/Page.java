package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Abstract class representation of a Page in the UI. Page object pattern
 */
public abstract class Page {

  protected WebDriver driver;

  /*
   * Constructor injecting the WebDriver interface
   * 
   * @param webDriver
   */
  public Page(WebDriver driver) {
    this.driver = driver;
  }

    public String getTitle() {

        return driver.getTitle();
    }
    public void getText(){
      String title = getTitle();
        System.out.println("Title: " + title);
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
    public void submit(){
        click(By.id("login_button"));
    }
    public void click(By locator) {
        driver.findElement(locator).click();
    }
    protected void type(By locator, String text) {
        click(locator);
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(text);
    }
}
