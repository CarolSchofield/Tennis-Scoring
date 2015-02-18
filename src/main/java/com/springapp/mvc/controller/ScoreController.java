package com.springapp.mvc.controller;

import com.springapp.mvc.model.Player;
import com.springapp.mvc.service.RefereeService;
import com.springapp.mvc.service.PlayerService;
import com.springapp.mvc.service.ScoreBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ScoreController {
    private final ScoreBoardService scoreBoardService;
    private final PlayerService playerService;
    private final RefereeService refereeService;

    @Autowired
    public ScoreController(ScoreBoardService scoreBoardService, PlayerService playerService, RefereeService refereeService) {
        this.scoreBoardService = scoreBoardService;
        this.playerService = playerService;
        this.refereeService = refereeService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/updateAction")
    public String scorePointForPlayer(HttpServletRequest request) {
        Player player = playerService.findPlayer(request.getParameter("player"));
        scoreBoardService.pointBy(player);
        return "redirect:/";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/resetScore")
    public String reset() {
        scoreBoardService.resetScore();
        return "redirect:/";
    }
}
