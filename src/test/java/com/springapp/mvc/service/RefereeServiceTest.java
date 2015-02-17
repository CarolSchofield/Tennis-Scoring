package com.springapp.mvc.service;

import com.springapp.mvc.model.Player;
import com.springapp.mvc.model.RefereeService;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class RefereeServiceTest {

    @Test
    public void shouldIncrementScoreForPlayer() throws Exception {
        RefereeService referee = new RefereeService();
        Player mockPlayer = mock(Player.class);
        referee.pointBy(mockPlayer);

        verify(mockPlayer).incrementScore();
    }

}