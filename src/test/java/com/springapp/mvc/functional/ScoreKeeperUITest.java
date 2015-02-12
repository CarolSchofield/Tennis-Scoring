package com.springapp.mvc.functional;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ScoreKeeperUITest {
    private static final String BASE_URL = "http://localhost:8080/";
    public static final String PLAYER_ONE_BUTTON_SELECTOR = "[data-qa=player-one-button]";
    public static final String PLAYER_TWO_BUTTON_SELECTOR = "[data-qa=player-two-button]";
    private static WebDriver driver;

    private WebElement playerOneButton;
    private WebElement playerTwoButton;
    private WebElement score;
    private WebElement resetButton;

    @BeforeClass
    public static void openDriver() {
        driver = new FirefoxDriver();
    }

    @Before
    public void setUp() throws Exception {
        driver.get(BASE_URL);
        driver.findElement(By.cssSelector("[data-qa=reset-button]")).click();
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
    public void shouldPlayMatchUntilWin() {
        playerOneButton = driver.findElement(By.cssSelector(PLAYER_ONE_BUTTON_SELECTOR));
        score = driver.findElement(By.cssSelector("[data-qa=score]"));
        assertThat(score.getText(), is("0/0"));

        playerOneButton.click();
        score = driver.findElement(By.cssSelector("[data-qa=score]"));
        assertThat(score.getText(), is("15/0"));

        playerTwoButton = driver.findElement(By.cssSelector(PLAYER_TWO_BUTTON_SELECTOR));
        playerTwoButton.click();
        score = driver.findElement(By.cssSelector("[data-qa=score]"));
        assertThat(score.getText(), is("15/15"));

        playerOneButton = driver.findElement(By.cssSelector(PLAYER_ONE_BUTTON_SELECTOR));
        playerOneButton.click();
        score = driver.findElement(By.cssSelector("[data-qa=score]"));
        assertThat(score.getText(), is("30/15"));

        playerOneButton = driver.findElement(By.cssSelector(PLAYER_ONE_BUTTON_SELECTOR));
        playerOneButton.click();
        score = driver.findElement(By.cssSelector("[data-qa=score]"));
        assertThat(score.getText(), is("40/15"));

        playerOneButton = driver.findElement(By.cssSelector(PLAYER_ONE_BUTTON_SELECTOR));
        playerOneButton.click();
        score = driver.findElement(By.cssSelector("[data-qa=score]"));
        assertThat(score.getText(), is("Game - Player One"));


        resetButton = driver.findElement(By.cssSelector("[data-qa=reset-button]"));
        resetButton.click();
        score = driver.findElement(By.cssSelector("[data-qa=score]"));
        assertThat(score.getText(), is("0/0"));
    }
}