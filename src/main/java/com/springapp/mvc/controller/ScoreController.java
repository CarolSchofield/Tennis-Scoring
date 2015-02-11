package com.springapp.mvc.controller;

import com.springapp.mvc.model.Player;
import com.springapp.mvc.service.ScoreUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ScoreController {
    private final ScoreUpdateService scoreUpdateService;

    @Autowired
    @Qualifier("playerOne")
    private Player playerOne;

    @Autowired
    @Qualifier("playerTwo")
    private Player playerTwo;


    @Autowired
    public ScoreController(ScoreUpdateService scoreUpdateService) {
        this.scoreUpdateService = scoreUpdateService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/updateAction")
    public String respondToButtonClick(HttpServletRequest request) {
        Player player = null;

        if(request.getParameter("player").equals("Player One")) {
            player = playerOne;
        }
        else if(request.getParameter("player").equals("Player Two")) {
            player = playerTwo;
        }
        
        scoreUpdateService.scorePoint(player);
        return "redirect:/";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/resetScore")
    public String reset() {
        scoreUpdateService.resetScore();
        return "redirect:/";
    }
}
