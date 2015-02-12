package com.springapp.mvc.service;

import com.springapp.mvc.model.Player;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PlayerService {
    private static Map<String, Player> players;

    static {
        players = new HashMap();
        players.put("Player One", new Player("Player One"));
        players.put("Player Two", new Player("Player Two"));
    };

    public Player findPlayer(String playerId) {
        if(players.get(playerId) != null) {
            return players.get(playerId);
        }
        return Player.NOBODY;
    }
}
