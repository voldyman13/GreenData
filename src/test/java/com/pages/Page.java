package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.NoSuchElementException;

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

    public String getTitle() { return driver.getTitle(); }

    public void waitUntilPageIsLoaded(int time, By locator){
        WebDriverWait wait = new WebDriverWait(driver, time);
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
    public void printTitle(){ System.out.println("Title: " + getTitle()); }
    public void getText(){ }
    public void click(By locator) { driver.findElement(locator).click(); }
    public void type(By locator, String text) {
        click(locator);
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(text);
    }
    public boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
    public void consoleLogBefore(String methodName){ System.out.println(methodName +" started... "); }
    public void consoleLogAfter(String methodName){ System.out.println(methodName +" finished. "); }

}
