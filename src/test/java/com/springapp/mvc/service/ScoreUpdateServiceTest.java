package com.springapp.mvc.service;

import com.springapp.mvc.model.Player;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class ScoreUpdateServiceTest {
    @Mock
    Player playerOne;
    @Mock 
    Player playerTwo;
    private ScoreUpdateService scoreUpdateService;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        scoreUpdateService = new ScoreUpdateService();
    }

    @Test
    public void shouldUpdatePlayerScore() {
        scoreUpdateService.playerOne = playerOne;
        
        scoreUpdateService.scorePoint("Player One");
        
        verify(playerOne).incrementScore();
    }

    @Test
    public void shouldUpdatePlayerTwoScoreWhenPlayerTwoScores()  {
        scoreUpdateService.playerTwo = playerTwo;

        scoreUpdateService.scorePoint("Player Two");

        verify(playerTwo).incrementScore();
    }

    @Test
    public void shouldConcatenateScoresForPlayers() throws Exception {
        scoreUpdateService.playerOne = playerOne;
        scoreUpdateService.playerTwo = playerTwo;
        
        when(playerOne.getCurrentScore()).thenReturn(1);
        when(playerTwo.getCurrentScore()).thenReturn(2);

        assertThat(scoreUpdateService.getCurrentScore(), is("1/2"));
        
    }

    @Test
    public void shouldResetPlayersWhenGameIsReset() {
        scoreUpdateService.playerOne = playerOne;
        scoreUpdateService.playerTwo = playerTwo;
        scoreUpdateService.resetScore();
        verify(playerOne).resetScore();
        verify(playerTwo).resetScore();
    }
}