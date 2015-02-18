package com.springapp.mvc.game;

public enum GameState {
    PlayerOneWin("Game - Player One"),
    PlayerTwoWin("Game - Player Two"),
    PlayerOneAdvantage("Advantage/-"),
    PlayerTwoAdvantage("-/Advantage"),
    Deuce("Deuce"),
    NumericalScores("need to know about the players");

    private String message;

    GameState(String message) {
        this.message = message;
    }

    public String message() {
        return message;
    }
}
