package com.geopagos.mstennistournament.application.port.in;

import com.geopagos.mstennistournament.adapter.controller.model.MatchExecutionRequestModel;
import com.geopagos.mstennistournament.domain.MatchResult;

import java.util.List;

public interface ExecuteTournamentCommand {

    MatchResult execute(List<MatchExecutionRequestModel> playersRequestModel);
}
