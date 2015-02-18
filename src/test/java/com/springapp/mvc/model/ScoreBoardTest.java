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

public class ScoreboardTest {
    @Mock
    private Player mockPlayerOne;
    @Mock
    private Player mockPlayerTwo;
    private Scoreboard scoreboard;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        scoreboard = new Scoreboard(mockPlayerOne, mockPlayerTwo);
    }

    @Test
    public void shouldNotDeclareWinnerWhenGameBegins() throws Exception {
        Player actualWinner = scoreboard.winner();
        
        assertThat(actualWinner, is(NOBODY));
    }

    @Test
    public void shouldDeclarePlayerOneWinnerWhenPlayerOneDefeatsPlayerTwo() throws Exception {
        when(mockPlayerOne.defeated(mockPlayerTwo)).thenReturn(true);

        Player actualWinner = scoreboard.winner();

        assertThat(actualWinner, is(mockPlayerOne));
    }

    @Test
    public void shouldDeclarePlayerTwoWinnerWhenPlayerTwoDefeatsPlayerOne() throws Exception {
        when(mockPlayerTwo.defeated(mockPlayerOne)).thenReturn(true);

        Player actualWinner = scoreboard.winner();

        assertThat(actualWinner, is(mockPlayerTwo));

    }

    @Test
    public void shouldResetPlayerScoresWhenGameIsReset() throws Exception {
        scoreboard.resetScore();
        verify(mockPlayerOne).resetScore();
        verify(mockPlayerTwo).resetScore();
    }

    @Test
    public void shouldGetScoreFromPlayers() throws Exception {
        scoreboard.score();
        verify(mockPlayerOne).numberOfPointsScored();
        verify(mockPlayerTwo).numberOfPointsScored();
    }

    @Test
    public void shouldFormatScore() throws Exception {
        when(mockPlayerOne.numberOfPointsScored()).thenReturn(1);
        when(mockPlayerTwo.numberOfPointsScored()).thenReturn(2);
        String actualScore = scoreboard.score();
        assertThat(actualScore, is("15/30"));

    }

    @Test
    public void shouldDeclareWinnerWhenThereIsAWinner() {
        String actualScore;

        when(mockPlayerOne.defeated(mockPlayerTwo)).thenReturn(true).thenReturn(false);
        actualScore = scoreboard.score();
        assertThat(actualScore, is("Game - Player One"));

        when(mockPlayerTwo.defeated(mockPlayerOne)).thenReturn(true);
        actualScore = scoreboard.score();
        assertThat(actualScore, is("Game - Player Two"));
    }

    @Test
    public void shouldReturnScoreAsDeuceWhenBothPlayersHaveFortyPoints() throws Exception {
        when(mockPlayerOne.numberOfPointsScored()).thenReturn(3);
        when(mockPlayerTwo.numberOfPointsScored()).thenReturn(3);
        
        String score = scoreboard.score();
        
        assertThat(score, is("Deuce"));
    }

    @Test
    public void shouldNotReturnDeuceWhenTiedAndNotForty() throws Exception {
        when(mockPlayerOne.numberOfPointsScored()).thenReturn(2);
        when(mockPlayerTwo.numberOfPointsScored()).thenReturn(2);
        
        String actualScore = scoreboard.score();
        
        assertThat(actualScore, is("30/30"));

    }

    @Test
    public void shouldReturnAdvantageScoreWhenOnePlayerScoresAfterDeuceScore() throws Exception {
        when(mockPlayerOne.isAtAdvantage(mockPlayerTwo)).thenReturn(true);

        String actualScore = scoreboard.score();

        assertThat(actualScore, is("Advantage/-"));
    }

    @Test
    public void shouldReturnAdvantageScoreWhenTheOtherPlayerScoresAfterDeuceScore() throws Exception {
        when(mockPlayerTwo.isAtAdvantage(mockPlayerOne)).thenReturn(true);

        String actualScore = scoreboard.score();

        assertThat(actualScore, is("-/Advantage"));
    }
}
