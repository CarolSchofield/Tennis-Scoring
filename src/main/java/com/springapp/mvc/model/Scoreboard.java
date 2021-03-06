package com.springapp.mvc.model;

import org.springframework.stereotype.Component;

@Component
public class Scoreboard {

    private static final String DELIMITER = "/";
    
    private Player playerOne;
    private Player playerTwo;

    private Scoreboard() {
        // needed for Spring wiring
    }

    public Scoreboard(Player playerOne, Player playerTwo) {
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

    public void resetScore() {
        playerOne.resetScore();
        playerTwo.resetScore();
    }

    public String score() {
        if(playerOne.defeated(playerTwo)){
            return "Game - Player One";
        }

        if(playerTwo.defeated(playerOne)){
            return "Game - Player Two";
        }
        
        if(playerOne.isAtAdvantage(playerTwo)) {
            return "Advantage/-";
        }

        if(playerTwo.isAtAdvantage(playerOne)) {
            return "-/Advantage";
        }

        String p1Score = String.valueOf(playerOne.currentScore());
        String p2Score = String.valueOf(playerTwo.currentScore());

        if (p1Score.equals("40") && p1Score.equals(p2Score) ) {
            return "Deuce";
        }

        return p1Score + DELIMITER + p2Score;
    }
}
