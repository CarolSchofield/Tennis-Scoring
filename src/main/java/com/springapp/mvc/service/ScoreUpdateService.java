package com.springapp.mvc.service;

import com.springapp.mvc.model.Game;
import com.springapp.mvc.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScoreUpdateService {
    Game game;

    @Autowired
    public ScoreUpdateService(PlayerService playerService) {
        Player playerOne = playerService.findPlayer("Player One");
        Player playerTwo = playerService.findPlayer("Player Two");
        this.game = new Game(playerOne, playerTwo);
    }

    public void scorePoint(Player player) {
        game.pointBy(player);
    }

    public String getCurrentScore() {
        return game.score();
    }

    public void resetScore() {
        game.resetScore();
    }

    public Player winner() {
        return game.winner();
    }
}
