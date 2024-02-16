package com.geopagos.mstennistournament.adapter.controller.match;

import com.geopagos.mstennistournament.adapter.controller.match.model.MatchExecutionRequestModel;
import com.geopagos.mstennistournament.domain.Gender;
import com.geopagos.mstennistournament.domain.Player;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class MatchValidator {

    private static final String INVALID_GENDER = "El valor ingresado para genero del torneo es incorrecto, posibles valores: " + Arrays.toString(Gender.values());
    private static final String INVALID_SKILL_LEVEL = "Los siguientes participantes tienen nivel de habilidad superior a 100 o inferior a 0: {value}";

    public List<Player> validate(MatchExecutionRequestModel matchExecutionRequestModel, String matchGender) throws Exception {
        validateGender(matchGender);

        List<Player> validPlayers = obtainValidPlayers(matchExecutionRequestModel, matchGender);
        validateSkillLevels(validPlayers);

        return validPlayers;
    }

    private List<Player> obtainValidPlayers(MatchExecutionRequestModel matchExecutionRequestModel, String matchGender) {
        List<Player> validPlayers = matchExecutionRequestModel.players().stream().filter(p -> p.gender().name().equals(matchGender)).toList();
        List<Player> nonValidPlayers = matchExecutionRequestModel.players().stream().filter(p -> !p.gender().name().equals(matchGender)).toList();

        if(validPlayers.size() < matchExecutionRequestModel.players().size())
            log.info("participants {} will not play because they do not belong to the category {}",
                    nonValidPlayers.stream().map(Player::name).collect(Collectors.toList()), matchGender);

        return validPlayers;
    }

    private void validateGender(String gender) throws Exception {
        if(Gender.map(gender) == null)
            throw new Exception(INVALID_GENDER);
    }

    private void validateSkillLevels(List<Player> players) throws Exception {
        List<Player> invalidPlayers = players.stream().filter(p -> p.skillLevel() > 100 || p.skillLevel() < 0).toList();
        if(invalidPlayers.size() > 0)
            throw new Exception(INVALID_SKILL_LEVEL.replace("{value}", invalidPlayers.stream().map(Player::name).toList().toString()));
    }

}
