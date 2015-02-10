package com.springapp.mvc.controller;

import com.springapp.mvc.service.ScoreUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    private ScoreUpdateService scoreUpdateService;

    @RequestMapping(method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
		model.addAttribute("message", "Welcome to Tennis Scorekeeper!");
        model.addAttribute("score", scoreUpdateService.getCurrentScore());
		return "tennis";
	}
}