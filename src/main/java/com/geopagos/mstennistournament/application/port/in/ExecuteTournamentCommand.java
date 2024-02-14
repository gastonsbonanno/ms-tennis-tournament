package com.geopagos.mstennistournament.application.port.in;

import com.geopagos.mstennistournament.adapter.controller.match.model.MatchExecutionRequestModel;
import com.geopagos.mstennistournament.domain.Gender;
import com.geopagos.mstennistournament.domain.MatchResult;
import com.geopagos.mstennistournament.domain.Player;

import java.util.List;

public interface ExecuteTournamentCommand {

    MatchResult execute(List<Player> players, Gender gender);
}
