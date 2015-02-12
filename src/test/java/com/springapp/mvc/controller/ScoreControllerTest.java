package com.springapp.mvc.controller;

import com.springapp.mvc.model.Player;
import com.springapp.mvc.service.PlayerService;
import com.springapp.mvc.service.ScoreUpdateService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ScoreControllerTest {

    @Mock
    private ScoreUpdateService mockScoreUpdateService;
    @Mock
    private HttpServletRequest mockRequest;
    @Mock
    private PlayerService mockPlayerService;

    private ScoreController scoreController;

    @Before
    public void setUp() throws Exception {
        scoreController = new ScoreController(mockScoreUpdateService, mockPlayerService);
    }

    @Test
    public void shouldCallScoreUpdaterService() {
        when(mockRequest.getParameter("player")).thenReturn("some player");
        scoreController.respondToButtonClick(mockRequest);
        verify(mockScoreUpdateService).scorePoint(null);
    }

    @Test
    public void shouldResetScoreWhenResetButtonIsClicked() {
        scoreController.reset();
        verify(mockScoreUpdateService).resetScore();
    }

    @Test
    public void shouldCallPlayerServiceWhenChoosingAPlayer() {
        when(mockRequest.getParameter("player")).thenReturn("somePlayer");

        scoreController.respondToButtonClick(mockRequest);

        verify(mockPlayerService).findPlayer("somePlayer");
    }

    @Test
    public void shouldPassPlayerReceivedByPlayerServiceToScoreUpdateService() {
        when(mockRequest.getParameter("player")).thenReturn("somePlayer");
        Player player = new Player("Player one");
        when(mockPlayerService.findPlayer("somePlayer")).thenReturn(player);

        scoreController.respondToButtonClick(mockRequest);

        verify(mockScoreUpdateService).scorePoint(player);

    }
}