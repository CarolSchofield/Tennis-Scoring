package com.springapp.mvc.functional;

import com.springapp.mvc.model.Player;
import com.springapp.mvc.service.RefereeService;
import com.springapp.mvc.service.PlayerService;
import com.springapp.mvc.service.ScoreBoardService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ScoreKeeperSubcutaneousTest {
    
    @Test
    public void shouldPlayGameToCompletion() {
        ApplicationContext applicationContext = new FileSystemXmlApplicationContext("/src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml");
        ScoreBoardService scoreBoardService = applicationContext.getBean(ScoreBoardService.class);
        RefereeService refereeService = applicationContext.getBean(RefereeService.class);
        PlayerService playerService = applicationContext.getBean(PlayerService.class);

        Player playerOne = playerService.findPlayer("Player One");
        Player playerTwo = playerService.findPlayer("Player Two");

        assertThat(scoreBoardService.getCurrentScore(), is("0/0"));

        refereeService.pointBy(playerOne);
        refereeService.pointBy(playerOne);
        refereeService.pointBy(playerTwo);

        assertThat(scoreBoardService.getCurrentScore(), is("30/15"));

        refereeService.pointBy(playerOne);
        assertThat(scoreBoardService.getCurrentScore(), is("40/15"));

        refereeService.pointBy(playerTwo);
        refereeService.pointBy(playerTwo);
        assertThat(scoreBoardService.getCurrentScore(), is("Deuce"));

        refereeService.pointBy(playerTwo);
        assertThat(scoreBoardService.getCurrentScore(), is("-/Advantage"));

        refereeService.pointBy(playerTwo);
        assertThat(scoreBoardService.getCurrentScore(), is("Game - Player Two"));

        scoreBoardService.resetScore();
        assertThat(scoreBoardService.getCurrentScore(), is("0/0"));
    }
}
