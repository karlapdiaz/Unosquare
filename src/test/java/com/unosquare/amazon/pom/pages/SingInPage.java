package com.unosquare.amazon.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

/**
 * Class with actions that can be performed on the sign in page
 */
public class SingInPage {

    private WebDriver webDriver;
    private WebDriverWait webDriverWait;
    private Actions actions;

    private final By signInDropDown = By.id("nav-link-accountList");
    private final By newCustomerLink = By.linkText("Start here.");
    private final By inputName = By.id("ap_customer_name");
    private final By inputEmail = By.id("ap_email");
    private final By conditionsLink = By.linkText("Conditions of Use");

    /**
     * Constructor of the class
     * @param webDriver webDriver web explorer driver
     * @param webDriverWait wait element for driver
     */
    public SingInPage(WebDriver webDriver, WebDriverWait webDriverWait) {
        this.webDriver = webDriver;
        this.webDriverWait = webDriverWait;
        this.webDriver.get("https://www.amazon.com/");
        actions = new Actions(webDriver);
    }

    /**
     * Method show sign in menu
     */
    public void clickMenuSignIn(){
        webDriverWait.until(visibilityOfElementLocated(signInDropDown));
        actions.moveToElement(webDriver.findElement(signInDropDown));
    }

    /**
     * Method click on option to show register form
     * @return text link to validate
     */
    public String clickOptionNewCustomer(){
        String text= webDriver.findElement(newCustomerLink).getText();
        webDriverWait.until(visibilityOfElementLocated(newCustomerLink));
        webDriver.findElement(newCustomerLink).click();
        return text;
    }

    /**
     * Method to write the customer name
     * @param name of the customer
     * @param lastName of the customer
     */
    public void enterNameOfCustomer(String name, String lastName){
        webDriverWait.until(visibilityOfElementLocated(inputName));
        webDriver.findElement(inputName).clear();
        webDriver.findElement(inputName).sendKeys(name + " " + lastName);
    }

    /**
     * Method to write the customer lastname
     * @param name of the customer
     * @param lastName of the customer
     * @return the email written
     */
    public String enterEmailOfCustomer(String name, String lastName){
        webDriverWait.until(visibilityOfElementLocated(inputEmail));
        webDriver.findElement(inputEmail).clear();
        webDriver.findElement(inputEmail).sendKeys(name + "." + lastName + "@fake.com");
        return webDriver.findElement(inputEmail).getText();
    }

    /**
     * Method to open conditions of use page
     */
    public void openConditiosOfUseLink(){
        webDriverWait.until(visibilityOfElementLocated(conditionsLink));
        webDriver.findElement(conditionsLink).click();
    }

}