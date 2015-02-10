package com.springapp.mvc.service;

import com.springapp.mvc.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScoreUpdateService {

    @Autowired
    Player player;
    
    public void scorePoint() {
        player.scorePoint();
    }

    public Integer getCurrentScore() {
        return player.getCurrentScore();
    }

    public void resetScore() {
        player.resetScore(); 
    }
}
