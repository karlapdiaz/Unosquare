package com.unosquare.amazon.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

/**
 * Class with actions that can be performed Conditions of use page
 */
public class ConditionsUsePage {

    private WebDriver webDriver;
    private WebDriverWait webDriverWait;
    private Actions actions;

    private final By searchBar = By.id("helpsearch");
    private By listResults;

    /**
     * Constructor of the class
     * @param webDriver webDriver web explorer driver
     * @param webDriverWait wait element for driver
     */
    public ConditionsUsePage(WebDriver webDriver, WebDriverWait webDriverWait) {
        this.webDriver = webDriver;
        this.webDriverWait = webDriverWait;
        actions = new Actions(webDriver);
    }

    /**
     * Method to write the topic to know support
     * @param topic phase to search support about
     */
    public void writeOnSearchBar(String topic){
        webDriverWait.until(visibilityOfElementLocated(searchBar));
        webDriver.findElement(searchBar).clear();
        webDriver.findElement(searchBar).sendKeys(topic);
        webDriver.findElement(searchBar).sendKeys(Keys.ENTER);
    }

    /**
     * Method to select a topic result to see more details
     * @param title title desired to find
     */
    public void clickOnResult(String title){
        listResults = By.linkText("Echo Support");
        webDriverWait.until(visibilityOfElementLocated(listResults));
        webDriver.findElement(listResults).click();
    }
}