package com.unosquare.amazon.pageFactory.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Class with actions that can be performed on the cart page
 */
public class CartPage extends BasePage{

    @FindBy(className = "sc-product-price")
    WebElement priceProductOnCart;


    @FindBy(className = "a-color-link")
    WebElement deleteButton;

    /**
     * Constructor to set webDriver
     * @param webDriver
     */
    public CartPage(WebDriver webDriver) {
        super(webDriver);
    }

    /**
     * Method to Performe click on the cart button
     */
    public void clickOnCart(){
        cartButton.click();
    }

    /**
     * Method to get the price of a product selected
     * @return the price of the product
     */
    public String getPriceProductOnCart(){
        return priceProductOnCart.getText();
    }

    /**
     * Method to delete a product from cart
     */
    public void deleteProductOnCart(){
        deleteButton.click();
    }
}