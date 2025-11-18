package org.example.tests;

import org.example.pom.FormPom;
import org.example.utils.Driver1;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class FormTest {
    WebDriver driver;
    WebDriverWait wait;

    static public String URL = "https://demoqa.com/automation-practice-form";
    static public String FIRST_NAME = "Alisa";
    static public String LAST_NAME = "Midrigan";
    static public String GENDER = "Female";
    static public String EMAIL = "alisamdg123@gmail.com";
    static public String NUMBER = "0683084900";
    static public String DATE = "7 JUL 2006";
    static public String SUBJECTS = "MATHS";
    static public String HOBBY = "";
    static public String HOBBY2 = "";
    static public String HOBBY3 = "Music";
    static public String STATE = "Haryana";
    static public String CITY = "Karnal";

    @BeforeClass
    public void beforeTest() {
        this.driver = Driver1.getAutoLocalDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void waitForPageLoaded() {
        new WebDriverWait(driver, Duration.ofSeconds(30)).until(webDriver ->
                ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }

    @Test
    public void formTest() {
        driver.get(URL);

        waitForPageLoaded();

        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 200);");

        FormPom form = new FormPom(driver);

        form.closeAdvert();
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight/2);");

        form.setFirstName(FIRST_NAME);
        form.setLastName(LAST_NAME);
        form.setGender(GENDER);
        form.setEmail(EMAIL);
        form.setUserNumber(NUMBER);
        form.setDateOfBirth(DATE);
        form.setSubjects(SUBJECTS);
        form.setHobby(HOBBY, HOBBY2, HOBBY3);
        form.setState(STATE);
        form.setCity(CITY);
        form.setSubmit();

        WebElement nameCell = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//tbody/tr[1]/*[2]")));
        String actualName = nameCell.getText();

        Assert.assertEquals(actualName, FIRST_NAME + " " + LAST_NAME, "Numele complet nu se potrivește!");
    }

    //@AfterClass
    public void closeDriver() {
        if (driver != null) {
            try {
                driver.quit();
            } catch (Exception ignored) {}
        }
    }
}