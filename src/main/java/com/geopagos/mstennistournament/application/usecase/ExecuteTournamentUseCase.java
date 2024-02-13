package com.geopagos.mstennistournament.application.usecase;

import com.geopagos.mstennistournament.adapter.controller.model.MatchExecutionRequestModel;
import com.geopagos.mstennistournament.application.port.in.ExecuteTournamentCommand;
import com.geopagos.mstennistournament.domain.MatchResult;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExecuteTournamentUseCase implements ExecuteTournamentCommand {


    @Override
    public MatchResult execute(List<MatchExecutionRequestModel> playersRequestModel) {

        return null;
    }
}
