package com.springapp.mvc.controller;

import com.springapp.mvc.service.ScoreUpdateService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import javax.servlet.http.HttpServletRequest;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class ScoreControllerTest {

    @Mock
    ScoreUpdateService mockScoreUpdateService;
    @Mock
    HttpServletRequest mockRequest;
    ScoreController scoreController;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        scoreController = new ScoreController(mockScoreUpdateService);

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
}