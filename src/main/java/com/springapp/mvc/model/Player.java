package com.springapp.mvc.model;

import org.springframework.stereotype.Component;

@Component
public class Player {
    static final Player NOBODY = new Player() {
        public Integer getCurrentScore() {
            return 0;
        }

        public void incrementScore() {
        }

        public void resetScore() {
        }
    };

    private Integer currentScore;
    private Integer numberOfPointsScored;

    public Player() {
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
    }

    public Boolean defeated(Player opponent) {
        if(numberOfPointsScored > opponent.numberOfPointsScored && numberOfPointsScored >= 4) {
            return true;
        }
        return false;
    }
}
