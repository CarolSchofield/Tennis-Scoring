package com.springapp.mvc.service;


import com.springapp.mvc.model.Player;
import org.springframework.stereotype.Service;

@Service
public class RefereeService {
    
    
    public void pointBy(Player player) {
            player.incrementScore();
    }
}
