package com.springapp.mvc.controller;

import com.springapp.mvc.service.ScoreBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class HomeController {

    private ScoreBoardService scoreboardService;

    @Autowired
    public HomeController(ScoreBoardService scoreBoardService) {
        this.scoreboardService = scoreBoardService;
    }

    @RequestMapping(method = RequestMethod.GET)
	public String reportTennisGame(ModelMap model) {
		model.addAttribute("message", "Welcome to Tennis Scorekeeper!");
        model.addAttribute("score", scoreboardService.getCurrentScore());

        if(scoreboardService.isGameOver()) {
            model.addAttribute("disableIfGameOver", "disabled");
        }

		return "tennis";
	}
}