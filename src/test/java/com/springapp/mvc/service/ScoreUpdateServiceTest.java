package com.springapp.mvc.service;

import com.springapp.mvc.model.Game;
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
    Player mockPlayer;

    @Mock
    PlayerService mockPlayerService;

    @Mock
    Game mockGame;

    private ScoreUpdateService scoreUpdateService;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        scoreUpdateService = new ScoreUpdateService(mockPlayerService);
        scoreUpdateService.game = mockGame;
    }

    @Test
    public void shouldNotifyGameWhenPlayerScores() {
        scoreUpdateService.scorePoint(mockPlayer);

        verify(mockGame).pointBy(mockPlayer);
    }

    @Test
    public void shouldAskGameForScores() throws Exception {
        scoreUpdateService.getCurrentScore();
        verify(mockGame).score();
    }

    @Test
    public void shouldTellGameToReset() {
        scoreUpdateService.resetScore();
        verify(mockGame).resetScore();
    }

    @Test
    public void shouldGetWinnerFromGame() {
        when(mockGame.winner()).thenReturn(mockPlayer);

        scoreUpdateService.winner();

        verify(mockGame).winner();
    }

    @Test
    public void shouldNotDeclareGameOverWhenNoWinner() {
        when(mockGame.winner()).thenReturn(Player.NOBODY);
        Boolean isGameOver = scoreUpdateService.isGameOver();
        assertThat(isGameOver, is(false));
    }

    @Test
    public void shouldDeclareGameOverWhenWinner() {
        when(mockGame.winner()).thenReturn(mockPlayer);
        Boolean isGameOver = scoreUpdateService.isGameOver();
        assertThat(isGameOver, is(true));

    }
}