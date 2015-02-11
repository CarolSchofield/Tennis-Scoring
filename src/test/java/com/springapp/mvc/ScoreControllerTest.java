package com.springapp.mvc;

import com.springapp.mvc.controller.ScoreController;
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
    ScoreUpdateService scoreUpdateService;
    @Mock
    HttpServletRequest request;        
    ScoreController scoreController;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        scoreController = new ScoreController(scoreUpdateService);

    }

    @Test
    public void shouldCallScoreUpdaterService() {
        when(request.getParameter("player")).thenReturn("somePlayer");
        scoreController.respondToButtonClick(request);
        verify(scoreUpdateService).scorePoint("somePlayer");

    }

    @Test
    public void shouldResetScoreWhenResetButtonIsClicked() {
        scoreController.reset();
        verify(scoreUpdateService).resetScore();
    }
}