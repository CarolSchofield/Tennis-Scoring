package com.springapp.mvc.model;

import com.springapp.mvc.game.GameState;
import org.springframework.stereotype.Component;

import static com.springapp.mvc.game.GameState.*;

@Component
public class Scoreboard {

    private static final String DELIMITER = "/";
    
    private Player playerOne;
    private Player playerTwo;
    private GameState gameState;

    private Scoreboard() {
        // needed for Spring wiring
    }

    public Scoreboard(Player playerOne, Player playerTwo) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        gameState = NumericalScores;
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

    public GameState getGameState() {
        return gameState;
    }

    public void pointBy(Player player) {
        player.incrementScore();
        String p1Score = String.valueOf(playerOne.currentScore());
        String p2Score = String.valueOf(playerTwo.currentScore());

        if(playerOne.defeated(playerTwo)) {
            gameState = PlayerOneWin;
        } else if(playerTwo.defeated(playerOne)) {
            gameState = PlayerTwoWin;
        } else if(playerOne.isAtAdvantage(playerTwo)) {
            gameState = PlayerOneAdvantage;
        } else if(playerTwo.isAtAdvantage(playerOne)) {
            gameState = PlayerTwoAdvantage;
        } else if(p1Score.equals("40") && p1Score.equals(p2Score)) {
            gameState = Deuce;
        } else {
            gameState = NumericalScores;
        }
    }


    public String score() {
        String p1Score = String.valueOf(playerOne.currentScore());
        String p2Score = String.valueOf(playerTwo.currentScore());

        switch(gameState) {
            case PlayerOneWin:
                return "Game - Player One";
            case PlayerTwoWin:
                return "Game - Player Two";
            case PlayerOneAdvantage:
                return "Advantage/-";
            case PlayerTwoAdvantage:
                return "-/Advantage";
            case Deuce:
                return "Deuce";
            default:
                return p1Score + DELIMITER + p2Score;
        }
    }
}
