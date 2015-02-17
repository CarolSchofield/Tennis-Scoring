package com.springapp.mvc.model;


import org.springframework.stereotype.Service;

@Service
public class RefereeService {
    
    
    public void pointBy(Player player) {
            player.incrementScore();
    }
}
