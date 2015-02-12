package com.springapp.mvc.model;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static com.springapp.mvc.model.Player.NOBODY;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class GameTest {
    @Mock
    private Player mockPlayerOne;
    @Mock
    private Player mockPlayerTwo;
    private Game game;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        game = new Game(mockPlayerOne, mockPlayerTwo);
    }

    @Test
    public void shouldNotDeclareWinnerWhenGameBegins() throws Exception {
        Player actualWinner = game.winner();
        
        assertThat(actualWinner, is(NOBODY));
    }

    @Test
    public void shouldDeclarePlayerOneWinnerWhenPlayerOneDefeatsPlayerTwo() throws Exception {
        when(mockPlayerOne.defeated(mockPlayerTwo)).thenReturn(true);

        Player actualWinner = game.winner();

        assertThat(actualWinner, is(mockPlayerOne));
    }

    @Test
    public void shouldDeclarePlayerTwoWinnerWhenPlayerTwoDefeatsPlayerOne() throws Exception {
        when(mockPlayerTwo.defeated(mockPlayerOne)).thenReturn(true);

        Player actualWinner = game.winner();

        assertThat(actualWinner, is(mockPlayerTwo));

    }

    @Test
    public void shouldNotifyPlayerWhenPlayerWinsPoint() throws Exception {
        
        game.pointBy(mockPlayerOne);
        
        verify(mockPlayerOne).incrementScore();

    }

    @Test
    public void shouldResetPlayerScoresWhenGameIsReset() throws Exception {
        game.resetScore();
        verify(mockPlayerOne).resetScore();
        verify(mockPlayerTwo).resetScore();
    }

    @Test
    public void shouldGetScoreFromPlayers() throws Exception {
        game.score();
        verify(mockPlayerOne).getCurrentScore();
        verify(mockPlayerTwo).getCurrentScore();
    }

    @Test
    public void shouldFormatScore() throws Exception {
        when(mockPlayerOne.getCurrentScore()).thenReturn(15);
        when(mockPlayerTwo.getCurrentScore()).thenReturn(30);
        String actualScore = game.score();
        assertThat(actualScore, is("15/30"));

    }

    @Test
    public void shouldDeclareWinnerWhenThereIsAWinner() {
        String actualScore;

        when(mockPlayerOne.defeated(mockPlayerTwo)).thenReturn(true).thenReturn(false);
        actualScore = game.score();
        assertThat(actualScore, is("Game - Player One"));

        when(mockPlayerTwo.defeated(mockPlayerOne)).thenReturn(true);
        actualScore = game.score();
        assertThat(actualScore, is("Game - Player Two"));
    }

}
