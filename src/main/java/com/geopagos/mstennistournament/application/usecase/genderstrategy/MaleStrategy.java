package com.geopagos.mstennistournament.application.usecase.genderstrategy;

import com.geopagos.mstennistournament.domain.MatchResult;
import com.geopagos.mstennistournament.domain.Player;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class MaleStrategy implements GenderStrategy {
    @Override
    public MatchResult executeTournament(List<Player> players) {
        log.info("Inicia el torneo masculino");
        return MatchResult.builder().player(executeMatchs(players).get(0)).build();
    }

    private List<Player> executeMatchs(List<Player> players) {
        List<Player> winners = new ArrayList<>();
        for(int i=0; i < players.size(); i+=2) {
            if(hasPlayerTwo(i, players.size())) {
                winners.add(runMatch(players.get(i), players.get(i+1)));
            } else {
                log.info("Jugador " + players.get(i).name() + "pasa de ronda automaticamente");
                winners.add(players.get(i));
            }
        }

        return (winners.size() > 1) ? executeMatchs(winners) : winners;
    }

    private Player runMatch(Player playerOne, Player playerTwo){
        BigDecimal pointsPlayerOne = calculatePoints(playerOne);
        BigDecimal pointsPlayerTwo = calculatePoints(playerTwo);
        log.info("Match en curso: "
                + playerOne.name() + " con " + pointsPlayerOne + " puntos"
                + "\nvs\n"
                + playerTwo.name() + " con " + pointsPlayerTwo + " puntos");

        Player winner = (pointsPlayerOne.compareTo(pointsPlayerTwo) > 0) ? playerOne : playerTwo;
        log.info("Ganador: " + winner.name());
        return winner;
    }

    private BigDecimal calculatePoints(Player player) {
        return addLuckyToSkillLevel(player.skillLevel())
                .add(BigDecimal.valueOf(player.strength()))
                .add(BigDecimal.valueOf(player.speed()));
    }

    private boolean hasPlayerTwo(int i, int playersSize) {
        return i + 1 < playersSize;
    }
}
