package com.springapp.mvc.service;

import com.springapp.mvc.model.Player;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class ScoreUpdateServiceTest {
    @Mock
    Player player;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void shouldUpdatePlayerScore() {
        ScoreUpdateService scoreUpdateService = new ScoreUpdateService();
        scoreUpdateService.player = player;
        
        scoreUpdateService.scorePoint();
        
        verify(player).scorePoint();
    }
}