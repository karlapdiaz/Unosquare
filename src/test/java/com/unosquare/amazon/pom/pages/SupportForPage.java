package com.unosquare.amazon.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Class with actions that can be performed on the detail support for a result page
 */
public class SupportForPage {
    private WebDriver webDriver;
    private WebDriverWait webDriverWait;
    private Actions actions;

    private final By resultsTitles = By.id("//a[@href='#']/h3");

    /**
     * Constructor of the class
     * @param webDriver wait element for driver
     * @param webDriverWait wait element for driver
     */
    public SupportForPage(WebDriver webDriver, WebDriverWait webDriverWait) {
        this.webDriver = webDriver;
        this.webDriverWait = webDriverWait;
        actions = new Actions(webDriver);
    }

    /**
     * Method to validate if a title is on the topics of a support result
     * @param title phase to check match on results
     * @return is exist or not
     */
    public boolean existOptionOnTitlesResults(String title){
        List<WebElement> titles = webDriver.findElements(resultsTitles);
        for (WebElement element: titles) {
           if( element.getText().equalsIgnoreCase(title)){
               return true;
            }
        }
        return false;
    }

}