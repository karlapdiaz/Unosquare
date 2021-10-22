package com.unosquare.amazon.pageFactory.pages;

import org.openqa.selenium.WebDriver;

import java.util.logging.Logger;

/**
 * Class with actions that can be performed search bar page
 */
public class SearchPage extends BasePage {

    private static Logger log = Logger.getLogger(SearchPage.class.getName());

    /**
     * Contructor to set webDriver
     * @param webDriver webDriver web explorer driver
     */
    public SearchPage(WebDriver webDriver) {
        super(webDriver);
    }

    /**
     * Method to write a search product
     * @param product name of the product
     */
    public void writeProductoSearchbar(String product){
        searchInput.clear();
        searchInput.sendKeys(product);
    }

    /**
     * Method to performe click on search button
     */
    public void clickButtonToSearch(){
        searchButton.click();
    }

}