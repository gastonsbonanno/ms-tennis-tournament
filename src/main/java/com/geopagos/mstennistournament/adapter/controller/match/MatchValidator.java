package com.geopagos.mstennistournament.adapter.controller.match;

import com.geopagos.mstennistournament.adapter.controller.match.model.MatchExecutionRequestModel;
import com.geopagos.mstennistournament.domain.Gender;
import com.geopagos.mstennistournament.domain.Player;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class MatchValidator {

    private static final String INVALID_PLAYERS_SIZE = "El torneo debe tener una cantidad de jugador@s par";
    private static final String INVALID_GENDER = "El valor ingresado para genero del torneo es incorrecto";
    private static final String INVALID_SKILL_LEVEL = "Los siguientes participantes tienen nivel de habilidad superior a 100 o inferior a 0: {value}";

    public List<Player> validate(MatchExecutionRequestModel matchExecutionRequestModel) throws Exception {
        validateGender(matchExecutionRequestModel.matchGender());

        List<Player> validPlayers = obtainValidPlayers(matchExecutionRequestModel);
        validatePlayersSize(validPlayers);
        validateSkillLevels(validPlayers);

        return validPlayers;
    }

    private List<Player> obtainValidPlayers(MatchExecutionRequestModel matchExecutionRequestModel) {
        List<Player> validPlayers = matchExecutionRequestModel.players().stream().filter(p -> p.gender().name().equals(matchExecutionRequestModel.matchGender().toUpperCase())).toList();
        List<Player> nonValidPlayers = matchExecutionRequestModel.players().stream().filter(p -> !p.gender().name().equals(matchExecutionRequestModel.matchGender().toUpperCase())).toList();

        if(validPlayers.size() <= matchExecutionRequestModel.players().size())
            log.info("Los participantes {} no jugaran porque no pertenecen a la categoría {}",
                    nonValidPlayers.stream().map(Player::name).collect(Collectors.toList()), matchExecutionRequestModel.matchGender());

        return validPlayers;
    }

    private void validateGender(String gender) throws Exception {
        if(Gender.map(gender) == null)
            throw new Exception(INVALID_GENDER);
    }

    private void validatePlayersSize(List<Player> players) throws Exception {
        if(players.size() % 2 != 0)
            throw new Exception(INVALID_PLAYERS_SIZE);
    }

    private void validateSkillLevels(List<Player> players) throws Exception {
        List<Player> invalidPlayers = players.stream().filter(p -> p.skillLevel() > 100 || p.skillLevel() < 0).toList();
        if(invalidPlayers.size() > 0)
            throw new Exception(INVALID_SKILL_LEVEL.replace("{value}", invalidPlayers.stream().map(Player::name).toList().toString()));
    }

}
