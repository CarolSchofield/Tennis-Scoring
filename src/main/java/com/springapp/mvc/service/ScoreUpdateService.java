package com.springapp.mvc.service;

import org.springframework.stereotype.Service;

@Service
public class ScoreUpdateService {
    private Integer currentScore;

    public ScoreUpdateService(){
        currentScore = 0;
    }

    public void scorePoint() {
        currentScore += 15;
    }

    public Integer getCurrentScore() {
        return currentScore;
    }

    public void resetScore() {
        currentScore = 0;
    }
}
