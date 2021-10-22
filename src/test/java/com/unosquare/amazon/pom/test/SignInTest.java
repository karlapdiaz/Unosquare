package com.unosquare.amazon.pom.test;

import com.unosquare.amazon.Util.JSONReadFromFile;
import com.unosquare.amazon.pom.pages.ConditionsUsePage;
import com.unosquare.amazon.pom.pages.SingInPage;
import com.unosquare.amazon.pom.pages.SupportForPage;
import com.unosquare.amazon.restAssured.pages.EmployeePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;
import org.testng.asserts.SoftAssert;


import java.util.logging.Logger;

/**
 *
 */
public class SignInTest {
    private static Logger log = Logger.getLogger(SignInTest.class.getName());

    private WebDriver driver;
    private WebDriverWait webDriverWait;

    private EmployeePage employeePage = new EmployeePage();
    private SingInPage singInPage;
    private ConditionsUsePage conditionsUsePage;
    private  SupportForPage supportForPage;

    private Assertion hardAssert = new Assertion();
    private SoftAssert softAssert = new SoftAssert();

    private JSONArray employees;

    @BeforeClass
    public void setupWebDriver_Chrome() {
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        String urlDemoPage = "https://www.amazon.com/";
        webDriverWait = new WebDriverWait(driver, 3);
        driver.get(urlDemoPage);
    }

    @Test
    public void testValidateGetAllEmployees() {
        String payloadBody =   employeePage.getAllEmployees().body().asString();

        try {
            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(payloadBody);
            employees = (JSONArray) json.get("data");
        } catch ( ParseException e) {
            log.info("Json could not be parsed");
        }
        Assert.assertFalse( payloadBody.contains("Too Many Attempts"),"API Response: Too Many Attempts");
        Assert.assertFalse( payloadBody.length()<2,"The user list is empty");
    }

    @DataProvider(name = "dataprovider-names-employees")
    public Object[][] readJsonToProvideNames()  {
        Object[][] objects = new Object[employees.size()][2];

        for(int i=0; i<employees.size(); i++){
            JSONObject employee = (JSONObject) employees.get(i);
            String name = (String) employee.get("employee_name");
            String[] parts = name.split(" ");
            objects[i][0] = parts[0];
            objects[i][1] = parts[1];
        }

        return objects;
    }

    @Test (dependsOnMethods= {"testValidateGetAllEmployees"})
    public void signInCustomer () {
        singInPage =  new SingInPage(driver,webDriverWait);

        singInPage.clickMenuSignIn();

        hardAssert.assertTrue(singInPage.clickOptionNewCustomer().contains("Start"));
    }

    @Test (dependsOnMethods= {"signInCustomer"}, dataProvider = "dataprovider-names-employees")
    public void fillFormNewCustomer (String name, String lastName) {
        singInPage.enterNameOfCustomer(name, lastName);
        softAssert.assertEquals(!singInPage.enterEmailOfCustomer(name, lastName).contains("@"),"Email invalid");
    }

    @Test (dependsOnMethods= {"fillFormNewCustomer"})
    public void openConditionsPage () throws ParseException {
        conditionsUsePage = new ConditionsUsePage(driver,webDriverWait);
        supportForPage = new SupportForPage(driver,webDriverWait);

        singInPage.openConditiosOfUseLink();
        conditionsUsePage.writeOnSearchBar(JSONReadFromFile.readFileJson("signParams","search_term"));
        conditionsUsePage.clickOnResult(JSONReadFromFile.readFileJson("signParams","select_title"));

        String secciones= JSONReadFromFile.readFileJson("signParams","sections").replace("sections:","");
        JSONParser parser = new JSONParser();
        JSONArray jsonArray = (JSONArray) parser.parse(secciones);
        for(int i=0; i<jsonArray.size(); i++){
            JSONObject titleObj = (JSONObject) jsonArray.get(i);
            String title = (String) titleObj.get("title");
            softAssert.assertEquals(supportForPage.existOptionOnTitlesResults(title),true);
        }
    }

}
