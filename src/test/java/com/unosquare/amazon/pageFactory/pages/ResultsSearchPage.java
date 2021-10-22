package com.unosquare.amazon.pageFactory.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Class with actions that can be performed search result page
 */
public class ResultsSearchPage extends BasePage{

    @FindBy(xpath = "//*[@id='search']/span/div/span/h1/div/div[1]/div/div/span[1]")
    WebElement resultsCount;

    @FindBy(xpath = "//*[@id='search']/div[1]/div[1]/div/span[3]/div[2]/div[contains(@class, 's-result-item s-asin')]")
    List<WebElement> resultsList;

    /**
     * Contructor to set webDriver
     * @param webDriver webDriver web explorer driver
     */
    public ResultsSearchPage(WebDriver webDriver) {
        super(webDriver);
    }

    /**
     * Method to get the total of serarch results
     * @return the total of results
     */
    public String getCountFromLabel(){
        return resultsCount.getText();
    }

    /**
     * Method to get price of a item selected by position
     * @param position number on the list
     * @return price
     */
    public String getPriceOfResultList(int position){
        String price = resultsList.get(position).findElement(By.cssSelector("span.a-price")).getText();
        price = price.replace("\n",".");
        return price;
    }

    /**
     * Method to select a product to see the details of it
     * @param position number on the list
     */
    public void clickOnProductFromList(int position){
        resultsList.get(position).findElement(By.tagName("a")).click();
    }
}