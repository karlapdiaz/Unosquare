package com.unosquare.amazon.pageFactory.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Abstract class to create other based on Actions of this class
 */
public abstract class BasePage {

    @FindBy(id = "twotabsearchtextbox")
    WebElement searchInput;

    @FindBy(id = "nav-search-submit-button")
    WebElement searchButton;

    @FindBy(id = "nav-cart")
    WebElement cartButton;

    protected WebDriver webDriver;

    /**
     * Constructor to set webDriver
     * @param webDriver
     */
    public BasePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver,this);
    }
}