package com.springapp.mvc.service;

import com.springapp.mvc.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ScoreUpdateService {
    private static final String DELIMITER = "/";

    @Autowired
    @Qualifier("playerOne")
    Player playerOne;
    @Autowired
    @Qualifier("playerTwo")
    Player playerTwo;

    public void scorePoint(String playerNumber) {
        if(playerNumber.equals("Player One")) {
            playerOne.scorePoint();
        }
        else if(playerNumber.equals("Player Two")) {
            playerTwo.scorePoint();
        }
        
    }

    public String getCurrentScore() {
        String p1Score = String.valueOf(playerOne.getCurrentScore());
        String p2Score = String.valueOf(playerTwo.getCurrentScore());
        return p1Score + DELIMITER + p2Score;
    }

    public void resetScore() {
        playerOne.resetScore(); 
        playerTwo.resetScore();
    }
}
