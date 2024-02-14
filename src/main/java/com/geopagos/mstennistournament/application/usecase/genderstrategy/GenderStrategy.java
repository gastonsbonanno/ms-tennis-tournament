package com.geopagos.mstennistournament.application.usecase.genderstrategy;

import com.geopagos.mstennistournament.domain.MatchResult;
import com.geopagos.mstennistournament.domain.Player;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

public interface GenderStrategy {
    MatchResult executeTournament(List<Player> players);

    default BigDecimal addLuckyToSkillLevel(int skillLevel) {
        BigDecimal lucky = BigDecimal.valueOf(skillLevel/4).multiply(BigDecimal.valueOf((new Random().nextFloat())));
        return lucky.add(BigDecimal.valueOf(skillLevel));
    }
}
