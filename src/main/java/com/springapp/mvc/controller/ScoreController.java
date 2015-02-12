package com.springapp.mvc.controller;

import com.springapp.mvc.model.Player;
import com.springapp.mvc.service.PlayerService;
import com.springapp.mvc.service.ScoreUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ScoreController {
    private final ScoreUpdateService scoreUpdateService;
    private final PlayerService playerService;

    @Autowired
    public ScoreController(ScoreUpdateService scoreUpdateService, PlayerService playerService) {
        this.scoreUpdateService = scoreUpdateService;
        this.playerService = playerService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/updateAction")
    public String respondToButtonClick(HttpServletRequest request) {
        Player player = playerService.findPlayer(request.getParameter("player"));
        scoreUpdateService.scorePoint(player);
        return "redirect:/";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/resetScore")
    public String reset() {
        scoreUpdateService.resetScore();
        return "redirect:/";
    }
}
