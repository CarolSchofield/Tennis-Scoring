package com.springapp.mvc.functional;

import com.springapp.mvc.service.ScoreUpdateService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ScoreKeeperSubcutaneousTest {

    @Test
    public void shouldIncreasePlayersScoreBy15WhenTheyScore() {
        ApplicationContext applicationContext = new FileSystemXmlApplicationContext("/src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml");

        ScoreUpdateService scoreUpdateService = applicationContext.getBean(ScoreUpdateService.class);
        assertThat(scoreUpdateService.getCurrentScore(), is("0/0"));

        scoreUpdateService.scorePoint("Player One");
        scoreUpdateService.scorePoint("Player One");
        scoreUpdateService.scorePoint("Player Two");

        assertThat(scoreUpdateService.getCurrentScore(), is("30/15"));

        scoreUpdateService.scorePoint("Player One");
        assertThat(scoreUpdateService.getCurrentScore(), is("40/15"));

        scoreUpdateService.resetScore();
        assertThat(scoreUpdateService.getCurrentScore(), is("0/0"));
    }
}
