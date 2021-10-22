package com.unosquare.amazon.pageFactory.test;

import com.unosquare.amazon.Util.JSONReadFromFile;
import com.unosquare.amazon.pageFactory.pages.CartPage;
import com.unosquare.amazon.pageFactory.pages.ProductPage;
import com.unosquare.amazon.pageFactory.pages.ResultsSearchPage;
import com.unosquare.amazon.pageFactory.pages.SearchPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;
import org.testng.asserts.SoftAssert;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class SearchProductTest {

    private static Logger log = Logger.getLogger(SearchProductTest.class.getName());

    private Assertion hardAssert = new Assertion();
    private SoftAssert softAssert = new SoftAssert();
    private WebDriver driver;
    private String price = "";

    private SearchPage searchPage;
    private ResultsSearchPage resultsSearchPage;
    private ProductPage productPage;
    private CartPage cartPage;

    @BeforeClass
    public void setupWebDriver_Chrome() {
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        String urlDemoPage = "https://www.amazon.com/";
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(urlDemoPage);

    }

    @Test(priority = 0)
    public void validate_searchResults() {
        searchPage = new SearchPage(driver);
        resultsSearchPage = new ResultsSearchPage(driver);
        String resultsCount = "";

        searchPage.writeProductoSearchbar(JSONReadFromFile.readFileJson("searchProductParams","product_name"));
        searchPage.clickButtonToSearch();
        resultsCount = resultsSearchPage.getCountFromLabel();

        hardAssert.assertFalse(resultsCount.contains("0 result"));
    }

    @Test(dependsOnMethods = {"validate_searchResults"})
    public void validate_priceOfItemSelected(){
        price = resultsSearchPage.getPriceOfResultList(Integer.parseInt(JSONReadFromFile.readFileJson("searchProductParams","result_to_select")));
        productPage = new ProductPage(driver);

        resultsSearchPage.clickOnProductFromList(Integer.parseInt(JSONReadFromFile.readFileJson("searchProductParams","result_to_select")));

        softAssert.assertEquals(price, productPage.getPriceProduct());
    }

    @Test (dependsOnMethods = {"validate_priceOfItemSelected"})
    public void validate_priceOfItemOnCart(){
        cartPage = new CartPage(driver);

        productPage.clickToBuyProduct();
        cartPage.clickOnCart();

        softAssert.assertEquals(price, cartPage.getPriceProductOnCart());
    }


    @AfterClass
    public void exitWebDriver_Chrome() throws Exception{
        cartPage.deleteProductOnCart();
        driver.close();
    }
}