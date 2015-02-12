package com.springapp.mvc.service;

import com.springapp.mvc.model.Player;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PlayerServiceTest {
    private PlayerService playerService;

    @Before
    public void setUp() throws Exception {
        playerService = new PlayerService();
    }

    @Test
    public void shouldReturnNobodyIfPlayerDoesNotExist() {
        Player player = playerService.findPlayer("Non-existing player");

        assertThat(player, is(Player.NOBODY));
    }

    @Test
    public void shouldFindPlayerFromStringIfPlayerExists() {
        Player player = playerService.findPlayer("Player One");
        Player expectedPlayer = new Player("Player One");
        assertThat(player, is(expectedPlayer));
    }
}