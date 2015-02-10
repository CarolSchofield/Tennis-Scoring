package com.springapp.mvc.model;

import org.springframework.stereotype.Component;

@Component
public class Player {
    private Integer currentScore;

    public Player() {
        this.currentScore = 0;
    }

    public Integer getCurrentScore() {
        return currentScore;
    }

    public void scorePoint() {
        currentScore += 15;
    }

    public void resetScore() {
        currentScore = 0;
    }
}
