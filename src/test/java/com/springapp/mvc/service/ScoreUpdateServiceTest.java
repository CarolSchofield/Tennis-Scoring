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
    Player mockPlayerOne;
    @Mock 
    Player mockPlayerTwo;
    private ScoreUpdateService scoreUpdateService;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        scoreUpdateService = new ScoreUpdateService();
    }

    @Test
    public void shouldUpdatePlayerScore() {
        scoreUpdateService.playerOne = mockPlayerOne;
        
        scoreUpdateService.scorePoint("Player One");
        
        verify(mockPlayerOne).incrementScore();
    }

    @Test
    public void shouldUpdatePlayerTwoScoreWhenPlayerTwoScores()  {
        scoreUpdateService.playerTwo = mockPlayerTwo;

        scoreUpdateService.scorePoint("Player Two");

        verify(mockPlayerTwo).incrementScore();
    }

    @Test
    public void shouldConcatenateScoresForPlayers() throws Exception {
        scoreUpdateService.playerOne = mockPlayerOne;
        scoreUpdateService.playerTwo = mockPlayerTwo;
        
        when(mockPlayerOne.getCurrentScore()).thenReturn(1);
        when(mockPlayerTwo.getCurrentScore()).thenReturn(2);

        assertThat(scoreUpdateService.getCurrentScore(), is("1/2"));
        
    }

    @Test
    public void shouldResetPlayersWhenGameIsReset() {
        scoreUpdateService.playerOne = mockPlayerOne;
        scoreUpdateService.playerTwo = mockPlayerTwo;
        scoreUpdateService.resetScore();
        verify(mockPlayerOne).resetScore();
        verify(mockPlayerTwo).resetScore();
    }
}