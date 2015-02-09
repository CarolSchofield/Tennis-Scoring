package com.springapp.mvc.service;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ScoreUpdateServiceTest {
    @Test
    public void shouldIncrementScoreByFifteen() {
        Integer expectedScore = 15;

        ScoreUpdateService scoreUpdateService = new ScoreUpdateService();
        scoreUpdateService.scorePoint();

        assertThat(scoreUpdateService.getCurrentScore(), is(expectedScore));
    }
}