package com.springapp.mvc.controller;

import com.springapp.mvc.service.ScoreBoardService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.ui.ModelMap;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(MockitoJUnitRunner.class)
public class HomeControllerTest {

    @Mock
    private ModelMap mockModelMap;
    @Mock
    private ScoreBoardService mockScoreBoardService;

    private HomeController homeController;


    @Before
    public void setUp() throws Exception {
        initMocks(this);
        homeController = new HomeController(mockScoreBoardService);
    }

    @Test
    public void shouldAddElementsToModel() {
        when(mockScoreBoardService.getCurrentScore()).thenReturn("SomeScore");

        homeController.reportTennisGame(mockModelMap);

        verify(mockModelMap).addAttribute("message", "Welcome to Tennis Scorekeeper!");
        verify(mockModelMap).addAttribute("score", "SomeScore");
    }

    @Test
    public void shouldDisableButtonsIfGameIsOver() {
        when(mockScoreBoardService.isGameOver()).thenReturn(true);

        homeController.reportTennisGame(mockModelMap);

        verify(mockModelMap).addAttribute("disableIfGameOver", "disabled");
    }
}