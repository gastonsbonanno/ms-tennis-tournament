package com.geopagos.mstennistournament.application.usecase;

import com.geopagos.mstennistournament.adapter.controller.match.model.MatchExecutionRequestModel;
import com.geopagos.mstennistournament.application.port.in.ExecuteTournamentCommand;
import com.geopagos.mstennistournament.domain.Gender;
import com.geopagos.mstennistournament.domain.MatchResult;
import com.geopagos.mstennistournament.domain.Player;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExecuteTournamentUseCase implements ExecuteTournamentCommand {

    @Override
    public MatchResult execute(List<Player> players, Gender gender) {
        return MatchResult.builder().build();
    }
}
