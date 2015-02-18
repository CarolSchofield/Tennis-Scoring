package com.springapp.mvc.model;

import com.springapp.mvc.game.GameState;
import org.springframework.stereotype.Component;

import static com.springapp.mvc.game.GameState.*;
import static java.lang.Math.min;

@Component
public class Scoreboard {

    private static final String DELIMITER = "/";
    
    private Player playerOne;
    private Player playerTwo;
    private GameState gameState;
    private String playerOneScore;
    private String playerTwoScore;

    private Scoreboard() {
        // needed for Spring wiring
    }

    public Scoreboard(Player playerOne, Player playerTwo) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        gameState = NumericalScores;
        playerOneScore = "0";
        playerTwoScore = "0";
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
        playerOneScore = "0";
        playerTwoScore = "0";
        updateGameState();
    }

    public void pointBy(Player player) {
        player.incrementScore();
        updateGameState();
    }

    private void updateGameState() {
        updateScores();

        if(playerOne.defeated(playerTwo)) {
            gameState = PlayerOneWin;
        } else if(playerTwo.defeated(playerOne)) {
            gameState = PlayerTwoWin;
        } else if(playerOne.isAtAdvantage(playerTwo)) {
            gameState = PlayerOneAdvantage;
        } else if(playerTwo.isAtAdvantage(playerOne)) {
            gameState = PlayerTwoAdvantage;
        } else if(playerOneScore.equals("40") && playerOneScore.equals(playerTwoScore)) {
            gameState = Deuce;
        } else {
            gameState = NumericalScores;
        }
    }

    private void updateScores() {
        playerOneScore = String.valueOf(min(playerOne.numberOfPointsScored() * 15, 40) );
        playerTwoScore = String.valueOf(min(playerTwo.numberOfPointsScored() * 15, 40) );
    }


    public String score() {
        if(gameState == NumericalScores) {
            return playerOneScore + DELIMITER + playerTwoScore;
        } else {
            return gameState.message();
        }
    }
}
