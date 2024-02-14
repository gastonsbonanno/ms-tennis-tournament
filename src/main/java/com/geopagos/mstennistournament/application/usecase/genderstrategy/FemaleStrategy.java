package com.geopagos.mstennistournament.application.usecase.genderstrategy;

import com.geopagos.mstennistournament.domain.MatchResult;
import com.geopagos.mstennistournament.domain.Player;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class FemaleStrategy implements GenderStrategy {
    @Override
    public MatchResult executeTournament(List<Player> players) {
        log.info("Inicia el torneo femenino");
        Player winner = executeMatchs(players).get(0);
        log.info("Ganadora del torneo: " + winner);
        return MatchResult.builder().player(winner).build();
    }

    private List<Player> executeMatchs(List<Player> players) {
        List<Player> winners = new ArrayList<>();
        for(int i=0; i < players.size(); i+=2) {
            if(hasPlayerTwo(i, players.size())) {
                Player winner = runMatch(players.get(i), players.get(i+1));
                log.info("Ganadora: " + winner.name());
                winners.add(winner);
            } else {
                log.info("Jugadora " + players.get(i).name() + " pasa de ronda automaticamente");
                winners.add(players.get(i));
            }
        }

        return (winners.size() > 1) ? executeMatchs(winners) : winners;
    }

    private Player runMatch(Player playerOne, Player playerTwo){
        BigDecimal pointsPlayerOne = calculatePoints(playerOne);
        BigDecimal pointsPlayerTwo = calculatePoints(playerTwo);
        log.info("Match en curso: "
                + playerOne.name() + " con " + pointsPlayerOne + " puntos, "
                + playerTwo.name() + " con " + pointsPlayerTwo + " puntos");

        return (pointsPlayerOne.compareTo(pointsPlayerTwo) > 0) ? playerOne : playerTwo;
    }

    private BigDecimal calculatePoints(Player player) {
        return addLuckyToSkillLevel(player.skillLevel())
                .add(BigDecimal.valueOf(player.reaction()));
    }

    private boolean hasPlayerTwo(int i, int playersSize) {
        return i + 1 < playersSize;
    }
}
