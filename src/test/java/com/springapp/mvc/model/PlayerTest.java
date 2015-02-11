package com.springapp.mvc.model;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PlayerTest {
    private Player player;

    @Before
    public void setUp() throws Exception {
        player = new Player();
    }

    @Test
    public void shouldIncrementScoreByFifteen() throws Exception {
        Integer startingScore = player.getCurrentScore();

        player.incrementScore();

        Integer newScore = player.getCurrentScore();
        
        assertThat(newScore, is(startingScore + 15));
    }

    @Test
    public void shouldCapScoreAt40() throws Exception {
        player.incrementScore();
        player.incrementScore();
        player.incrementScore();
        assertThat(player.getCurrentScore(),is(40));

    }

    @Test
    public void shouldResetScoreToZero() throws Exception {
        player.incrementScore();
        assertThat(player.getCurrentScore(), is(not(0)));

        player.resetScore();
        assertThat(player.getCurrentScore(), is(0));
    }

    @Test
    public void shouldNotDeclareWinnerBeforeAnyPointsAreScored() throws Exception {
        Player opponent = new Player();
        Boolean defeatedOpponent = player.defeated(opponent);

        assertThat(defeatedOpponent, is(Boolean.FALSE));

    }

    @Test
    public void shouldDiscoverPlayerOneDefeatsOpponent() {
        Player opponent = new Player();
        player.incrementScore();
        player.incrementScore();
        player.incrementScore();
        player.incrementScore();

        Boolean defeatedOpponent = player.defeated(opponent);

        assertThat(defeatedOpponent, is(Boolean.TRUE));
    }

}