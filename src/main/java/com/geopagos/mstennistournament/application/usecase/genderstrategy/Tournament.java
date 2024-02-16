package com.geopagos.mstennistournament.application.usecase.genderstrategy;

import com.geopagos.mstennistournament.domain.MatchResult;
import com.geopagos.mstennistournament.domain.Player;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Slf4j
@Component
public class Tournament implements TournamentExecution {

    @Override
    public MatchResult execute(List<Player> players) {
        log.info("Tournament begins");
        Player winner = executeMatchs(players).get(0);
        log.info("Tournament Winner: " + winner);
        return MatchResult.builder().player(winner).build();
    }

    private List<Player> executeMatchs(List<Player> players) {
        List<Player> winners = new ArrayList<>();
        for(int i=0; i < players.size(); i+=2) {
            if(hasPlayerTwo(i, players.size())) {
                Player winner = runMatch(players.get(i), players.get(i+1));
                log.info("Match winner: " + winner.name());
                winners.add(winner);
            } else {
                log.info("Player " + players.get(i).name() + " passes the round automatically");
                winners.add(players.get(i));
            }
        }

        return (winners.size() > 1) ? executeMatchs(winners) : winners;
    }

    private Player runMatch(Player playerOne, Player playerTwo){
        BigDecimal pointsPlayerOne = calculatePoints(playerOne);
        BigDecimal pointsPlayerTwo = calculatePoints(playerTwo);
        log.info("Match: "
                + playerOne.name() + " with " + pointsPlayerOne + " points, "
                + playerTwo.name() + " with " + pointsPlayerTwo + " points");

        return (pointsPlayerOne.compareTo(pointsPlayerTwo) > 0) ? playerOne : playerTwo;
    }

    protected BigDecimal calculatePoints(Player player) {
        return addLuckyToSkillLevel(player.skillLevel())
                .add(BigDecimal.valueOf(player.strength()))
                .add(BigDecimal.valueOf(player.reaction()))
                .add(BigDecimal.valueOf(player.speed()));
    }


    protected BigDecimal addLuckyToSkillLevel(int skillLevel) {
        BigDecimal lucky = BigDecimal.valueOf(skillLevel/4).multiply(BigDecimal.valueOf((new Random().nextFloat())));
        return lucky.add(BigDecimal.valueOf(skillLevel));
    }

    private boolean hasPlayerTwo(int i, int playersSize) {
        return i + 1 < playersSize;
    }

}
