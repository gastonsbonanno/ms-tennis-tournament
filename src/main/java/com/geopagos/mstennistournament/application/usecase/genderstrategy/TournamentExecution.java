package com.geopagos.mstennistournament.application.usecase.genderstrategy;

import com.geopagos.mstennistournament.domain.MatchResult;
import com.geopagos.mstennistournament.domain.Player;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

public interface TournamentExecution {
    MatchResult execute(List<Player> players);

}
