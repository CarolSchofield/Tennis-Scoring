package com.springapp.mvc.model;

public class PlayerBuilder {
    private String playerId = "default id";
    private Integer currentScore = 0;
    private Integer numberOfPointsScored = 0;

    public static PlayerBuilder aPlayer() {
        return new PlayerBuilder();
    }

    public PlayerBuilder withPlayerId(String playerId) {
        this.playerId = playerId;
        return this;
    }

    public PlayerBuilder withCurrentScore(Integer currentScore) {
        this.currentScore = currentScore;
        return this;
    }

    public PlayerBuilder withNumberOfPointsScored(Integer numberOfPointsScored) {
        this.numberOfPointsScored = numberOfPointsScored;
        return this;
    }

    public PlayerBuilder but() {
        return aPlayer().withPlayerId(playerId).withCurrentScore(currentScore).withNumberOfPointsScored(numberOfPointsScored);
    }

    public Player build() {
        Player player = new Player(playerId, currentScore, numberOfPointsScored);
        return player;
    }

}
