package com.unosquare.amazon.pageFactory.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Class with actions that can be performed on the product description page
 */
public class ProductPage extends BasePage{
    @FindBy(id  = "price_inside_buybox")
    WebElement priceProduct;

    @FindBy(id  = "add-to-cart-button")
    WebElement addToCartButton;

    /**
     * Constructor to set webDriver
     * @param webDriver
     */
    public ProductPage(WebDriver webDriver) {
        super(webDriver);
    }

    /**
     * Method to get the price of the product
     * @return price of product
     */
    public String getPriceProduct(){
        return priceProduct.getText();
    }

    /**
     * Method to add the product to sopping cart
     */
    public void clickToBuyProduct(){
        addToCartButton.click();
    }

}