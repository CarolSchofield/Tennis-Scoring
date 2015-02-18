package com.springapp.mvc.model;

import org.junit.Before;
import org.junit.Test;

import static com.springapp.mvc.model.PlayerBuilder.aPlayer;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PlayerTest {
    private Player playerOne;

    @Before
    public void setUp() throws Exception {
        playerOne = new Player("Player one");
    }

    @Test
    public void shouldIncrementScoreByFifteenWhenPlayerScoresAPoint() throws Exception {
        playerOneScores(1);

        Integer newScore = playerOne.numberOfPointsScored();
        assertThat(newScore, is(1));
    }

    @Test
    public void shouldCapScoreAtFortyInsteadOfFortyFive() throws Exception {
        playerOneScores(3);

        assertThat(playerOne.numberOfPointsScored(), is(3));
    }

    @Test
    public void shouldResetScoreToZero() throws Exception {
        playerOneScores(1);
        assertThat(playerOne.numberOfPointsScored(), is(not(0)));

        playerOne.resetScore();
        assertThat(playerOne.numberOfPointsScored(), is(0));
    }

    @Test
    public void shouldResetPointsScoredToZero() {
        Player playerWithNoPoints = aPlayer().withPlayerId("Player one").withNumberOfPointsScored(0).build();

        playerOneScores(1);
        playerOne.resetScore();

        assertThat(playerOne, is(playerWithNoPoints));
    }

    @Test
    public void shouldNotDeclareWinnerBeforeAnyPointsAreScored() throws Exception {
        Player opponent = new Player("Player one");
        Boolean defeatedOpponent = playerOne.defeated(opponent);

        assertThat(defeatedOpponent, is(Boolean.FALSE));

    }

    @Test
    public void shouldDiscoverPlayerOneDefeatsOpponent() {
        Player opponent = new Player("Player one");
        playerOneScores(4);

        Boolean defeatedOpponent = playerOne.defeated(opponent);

        assertThat(defeatedOpponent, is(Boolean.TRUE));
    }

    @Test
    public void shouldNotDefeatAPlayerUntilBeatingByTwo() throws Exception {

        playerOneScores(3);

        Player playerTwo = aPlayer().withNumberOfPointsScored(3).build();
        
        playerOne.incrementScore();
        assertThat(playerOne.defeated(playerTwo), is(false));

        playerOne.incrementScore();
        assertThat(playerOne.defeated(playerTwo), is(true));
    }

    private void playerOneScores(int numPoints) {
        for (int i = 0; i < numPoints; i++) {
            playerOne.incrementScore();
        }
    }

    @Test
    public void shouldReturnTrueWhenPlayerOneHasAdvantage() throws Exception {
        Player playerWithAdvantage = aPlayer().withNumberOfPointsScored(4).build();
        Player playerWithNoAdvantage = aPlayer().withNumberOfPointsScored(3).build();
        assertThat(playerWithAdvantage.isAtAdvantage(playerWithNoAdvantage), is(true));
    }

    @Test
    public void shouldReturnFalseWhenPlayerDoesNotHaveOneGreaterPointThanOpponentFromDeuceState() throws Exception {
        Player playerWithSixPoints = aPlayer().withNumberOfPointsScored(6).build();
        Player anotherPlayerWithSixPoints = aPlayer().withNumberOfPointsScored(6).build();
        assertThat(playerWithSixPoints.isAtAdvantage(anotherPlayerWithSixPoints), is(false));
    }

    @Test
    public void shouldNotShowAdvantageWhenBothPlayersHaveFourPoints() throws Exception {
        Player playerWithFourPoints = aPlayer().withNumberOfPointsScored(4).build();
        Player anotherPlayerWithFourPoints = aPlayer().withNumberOfPointsScored(4).build();
        assertThat(playerWithFourPoints.isAtAdvantage(anotherPlayerWithFourPoints), is(false));
    }
}