package com.geopagos.mstennistournament.application.usecase.genderstrategy;

import com.geopagos.mstennistournament.domain.MatchResult;
import com.geopagos.mstennistournament.domain.Player;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component("MaleStrategy")
public class MaleTournament extends Tournament {

    @Override
    protected BigDecimal calculatePoints(Player player) {
        return addLuckyToSkillLevel(player.skillLevel())
                .add(BigDecimal.valueOf(player.strength()))
                .add(BigDecimal.valueOf(player.speed()));
    }

}
