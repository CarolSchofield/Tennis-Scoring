package com.springapp.mvc.service;

import com.springapp.mvc.model.Scoreboard;
import com.springapp.mvc.model.Player;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class ScoreBoardServiceTest {
    @Mock
    Player mockPlayer;

    @Mock
    PlayerService mockPlayerService;

    @Mock
    Scoreboard mockGame;

    private ScoreBoardService scoreBoardService;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        scoreBoardService = new ScoreBoardService(mockPlayerService);
        scoreBoardService.scoreboard = mockGame;
    }

    @Test
    public void shouldTellGameToReset() {
        scoreBoardService.resetScore();
        verify(mockGame).resetScore();
    }

    @Test
    public void shouldGetWinnerFromGame() {
        when(mockGame.winner()).thenReturn(mockPlayer);
        scoreBoardService.winner();
        verify(mockGame).winner();
    }

    @Test
    public void shouldNotDeclareGameOverWhenNoWinner() {
        when(mockGame.winner()).thenReturn(Player.NOBODY);
        Boolean isGameOver = scoreBoardService.isGameOver();
        assertThat(isGameOver, is(false));
    }

    @Test
    public void shouldDeclareGameOverWhenWinner() {
        when(mockGame.winner()).thenReturn(mockPlayer);
        Boolean isGameOver = scoreBoardService.isGameOver();
        assertThat(isGameOver, is(true));

    }
}