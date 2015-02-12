package com.springapp.mvc.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@AllArgsConstructor
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

    public Player(String playerId) {
        this.playerId = playerId;
        this.currentScore = 0;
        this.numberOfPointsScored = 0;
    }

    public Integer getCurrentScore() {
        return currentScore;
    }

    public void incrementScore() {
        currentScore = Math.min(currentScore + 15, 40);
        numberOfPointsScored++;
    }

    public void resetScore() {
        currentScore = 0;
        numberOfPointsScored = 0;
    }

    public Boolean defeated(Player opponent) {
        if(numberOfPointsScored > opponent.numberOfPointsScored && numberOfPointsScored >= 4) {
            return true;
        }
        return false;
    }
}
