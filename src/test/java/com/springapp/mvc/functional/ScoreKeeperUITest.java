package com.springapp.mvc.functional;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ScoreKeeperUITest {
    protected static final String BASE_URL = "http://localhost:8080/";
    protected static WebDriver driver;

    @BeforeClass
    public static void openDriver() {
        driver = new FirefoxDriver();
    }

    @After
    public void tearDown() throws Exception {
        driver.findElement(By.cssSelector("[data-qa=reset-button]")).click();
    }

    @AfterClass
    public static void closeDriver() {
        driver.close();
    }

    @Test
    public void shouldUpdateScoreWhenButtonIsClicked() {
        driver.get(BASE_URL);

        WebElement playerOneButton = driver.findElement(By.cssSelector("[data-qa=player-one-button]"));

        WebElement playerOneScore = driver.findElement(By.cssSelector("[data-qa=player-one-score]"));
        assertThat(playerOneScore.getText(), is("0"));

        playerOneButton.click();
        playerOneScore = driver.findElement(By.cssSelector("[data-qa=player-one-score]"));

        assertThat(playerOneScore.getText(), is("15"));
    }
}