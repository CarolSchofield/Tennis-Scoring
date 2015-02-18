package com.springapp.mvc.model;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public class Player {
    public static final Player NOBODY = new Player("Nobody") {};

    private final String playerId;
    private Integer numberOfPointsScored;

    public Player(String playerId, Integer numberOfPointsScored) {
        this.playerId = playerId;
        this.numberOfPointsScored = numberOfPointsScored;
    }

    public Player(String playerId) {
        this.playerId = playerId;
        this.numberOfPointsScored = 0;
    }

    public Integer numberOfPointsScored() {
        return numberOfPointsScored;
    }

    public void incrementScore() {
        numberOfPointsScored++;
    }

    public void resetScore() {
        numberOfPointsScored = 0;
    }

    public Boolean isAtAdvantage(Player opponent) {
        return (numberOfPointsScored == opponent.numberOfPointsScored + 1) && numberOfPointsScored >= 4;
    }

    public Boolean defeated(Player opponent) {
        return (numberOfPointsScored > opponent.numberOfPointsScored + 1) && numberOfPointsScored >= 4;
    }
}