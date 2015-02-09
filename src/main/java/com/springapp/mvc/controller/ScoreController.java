package com.springapp.mvc.controller;

import com.springapp.mvc.service.ScoreUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ScoreController {
    private final ScoreUpdateService scoreUpdateService;

    @Autowired
    public ScoreController(ScoreUpdateService scoreUpdateService) {
        this.scoreUpdateService = scoreUpdateService;
    }



    @RequestMapping(method = RequestMethod.POST, value = "/updateAction")
    public String respondToButtonClick(ModelMap model) {
        scoreUpdateService.scorePoint();
        return "redirect:/";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/resetScore")
    public String reset() {
        scoreUpdateService.resetScore();
        return "redirect:/";
    }
}
