package com.springapp.mvc;

import com.springapp.mvc.controller.ScoreController;
import com.springapp.mvc.service.ScoreUpdateService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.ui.ModelMap;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class ScoreControllerTest {

    @Mock
    ScoreUpdateService scoreUpdateService;
    ScoreController scoreController;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        scoreController = new ScoreController(scoreUpdateService);

    }

    @Test
    public void shouldCallScoreUpdaterService() {
        scoreController.respondToButtonClick(new ModelMap());
        verify(scoreUpdateService).scorePoint();

    }

    @Test
    public void shouldResetScoreWhenResetButtonIsClicked() {
        scoreController.reset();
        verify(scoreUpdateService).resetScore();
    }
}