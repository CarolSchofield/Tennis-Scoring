package com.springapp.mvc.controller;

import com.springapp.mvc.service.PlayerService;
import com.springapp.mvc.service.RefereeService;
import com.springapp.mvc.service.ScoreBoardService;
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
    private ScoreBoardService mockScoreBoardService;
    @Mock
    private HttpServletRequest mockRequest;
    @Mock
    private PlayerService mockPlayerService;
    @Mock
    private RefereeService mockRefereeService;

    private ScoreController scoreController;

    @Before
    public void setUp() throws Exception {
        scoreController = new ScoreController(mockScoreBoardService, mockPlayerService, mockRefereeService);
    }

//    @Test
//    public void shouldCallRefereeServiceWhenPlayerScores() {
//        when(mockRequest.getParameter("player")).thenReturn("some player");
//        scoreController.scorePointForPlayer(mockRequest);
//        verify(mockRefereeService).pointBy(null);
//    }

    @Test
    public void shouldResetScoreboardWhenGameIsReset() {
        scoreController.reset();
        verify(mockScoreBoardService).resetScore();
    }

    @Test
    public void shouldCallPlayerServiceWhenChoosingAPlayer() {
        when(mockRequest.getParameter("player")).thenReturn("somePlayer");
        scoreController.scorePointForPlayer(mockRequest);
        verify(mockPlayerService).findPlayer("somePlayer");
    }

//    @Test
//    public void shouldPassPlayerReceivedByPlayerServiceToRefereeService() {
//        when(mockRequest.getParameter("player")).thenReturn("somePlayer");
//        Player player = new Player("Player one");
//        when(mockPlayerService.findPlayer("somePlayer")).thenReturn(player);
//
//        scoreController.scorePointForPlayer(mockRequest);
//
//        verify(mockRefereeService).pointBy(player);
//    }
}