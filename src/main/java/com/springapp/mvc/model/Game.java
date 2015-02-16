package com.springapp.mvc.model;

import org.springframework.stereotype.Component;

@Component
public class Game {

    private static final String DELIMITER = "/";
    
    private Player playerOne;
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
        if(playerOne.defeated(playerTwo)){
            return "Game - Player One";
        }

        if(playerTwo.defeated(playerOne)){
            return "Game - Player Two";
        }

        String p1Score = String.valueOf(playerOne.getCurrentScore());
        String p2Score = String.valueOf(playerTwo.getCurrentScore());
        
        if (p1Score.equals("40") && p1Score.equals(p2Score) ) {
            return "Deuce";
        }
        
        return p1Score + DELIMITER + p2Score;
    }
}
