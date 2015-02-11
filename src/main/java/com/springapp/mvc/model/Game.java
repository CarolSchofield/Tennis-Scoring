package com.springapp.mvc.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Game {

    private static final String DELIMITER = "/";
    
    @Autowired
    @Qualifier("playerOne")
    private Player playerOne;

    @Autowired
    @Qualifier("playerTwo")
    private Player playerTwo;

    private Game() {
        // needed for Spring wiring
    }

    public Game(Player playerOne, Player playerTwo) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
    }

    public Player winner() {
        if(playerOne.defeated(playerTwo)) {
            return playerOne;
        }
        else if(playerTwo.defeated(playerOne)) {
            return playerTwo;
        }
        return Player.NOBODY;
    }

    public void pointBy(Player player) {
        player.incrementScore();
    }

    public void resetScore() {
        playerOne.resetScore();
        playerTwo.resetScore();
    }

    public String score() {
        String p1Score = String.valueOf(playerOne.getCurrentScore());
        String p2Score = String.valueOf(playerTwo.getCurrentScore());
        return p1Score + DELIMITER + p2Score;

    }
}
