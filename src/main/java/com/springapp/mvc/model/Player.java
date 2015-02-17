package com.springapp.mvc.model;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import static java.lang.Math.min;

@ToString
@EqualsAndHashCode
public class Player {
    public static final Player NOBODY = new Player("Nobody") {
        public Integer currentScore() {return 0;}
        public void incrementScore() {}
        public void resetScore() {}
    };

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

    public Integer currentScore() {
        return min(numberOfPointsScored * 15, 40) ;
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
