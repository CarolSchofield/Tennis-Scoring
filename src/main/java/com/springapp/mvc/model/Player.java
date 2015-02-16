package com.springapp.mvc.model;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import static java.lang.Math.min;

@ToString
@EqualsAndHashCode
public class Player {
    public static final Player NOBODY = new Player("Nobody") {
        public Integer getCurrentScore() {return 0;}
        public void incrementScore() {}
        public void resetScore() {}
    };

    private final String playerId;
    private Integer currentScore;
    private Integer numberOfPointsScored;

    public Player(String playerId, Integer currentScore, Integer numberOfPointsScored) {
        this.playerId = playerId;
        this.currentScore = currentScore;
        this.numberOfPointsScored = numberOfPointsScored;
    }

    public Player(String playerId) {
        this.playerId = playerId;
        this.currentScore = 0;
        this.numberOfPointsScored = 0;
    }

    public Integer getCurrentScore() {
        return currentScore;
    }

    public void incrementScore() {
        currentScore = min(currentScore + 15, 40);
        numberOfPointsScored++;
    }

    public void resetScore() {
        currentScore = 0;
        numberOfPointsScored = 0;
    }

    public Boolean defeated(Player opponent) {
        if((numberOfPointsScored > opponent.numberOfPointsScored + 1) && numberOfPointsScored >= 4) {
            return true;
        }
        return false;
    }
}
