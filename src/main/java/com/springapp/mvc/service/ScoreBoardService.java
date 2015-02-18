package com.springapp.mvc.service;

import com.springapp.mvc.model.Scoreboard;
import com.springapp.mvc.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScoreBoardService {
    Scoreboard scoreboard;

    @Autowired
    public ScoreBoardService(PlayerService playerService) {
        Player playerOne = playerService.findPlayer("Player One");
        Player playerTwo = playerService.findPlayer("Player Two");
        this.scoreboard = new Scoreboard(playerOne, playerTwo);
    }

    public String getCurrentScore() {
        return scoreboard.score();
    }

    public void resetScore() {
        scoreboard.resetScore();
    }

    public Player winner() {
        return scoreboard.winner();
    }

    public Boolean isGameOver() {
        return !(scoreboard.winner().equals(Player.NOBODY));
    }

    public void pointBy(Player player) {
        scoreboard.pointBy(player);
    }
}
