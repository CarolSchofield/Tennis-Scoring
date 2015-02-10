package com.springapp.mvc.controller;

import com.springapp.mvc.service.ScoreUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ScoreController {
    private final ScoreUpdateService scoreUpdateService;

    @Autowired
    public ScoreController(ScoreUpdateService scoreUpdateService) {
        this.scoreUpdateService = scoreUpdateService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/updateAction")
    public String respondToButtonClick(HttpServletRequest request) {
        scoreUpdateService.scorePoint(request.getParameter("player"));
        return "redirect:/";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/resetScore")
    public String reset() {
        scoreUpdateService.resetScore();
        return "redirect:/";
    }
}
