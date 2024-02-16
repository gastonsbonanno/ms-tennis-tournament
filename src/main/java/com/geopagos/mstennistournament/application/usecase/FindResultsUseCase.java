package com.geopagos.mstennistournament.application.usecase;

import com.geopagos.mstennistournament.application.port.in.FindResultsCommand;
import com.geopagos.mstennistournament.application.port.out.TournamentRepository;
import com.geopagos.mstennistournament.domain.TournamentResult;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class FindResultsUseCase implements FindResultsCommand {

    private final TournamentRepository tournamentRepository;

    public FindResultsUseCase(TournamentRepository tournamentRepository) {
        this.tournamentRepository = tournamentRepository;
    }

    @Override
    public List<TournamentResult> execute(String winnerName, String gender, Date date) throws Exception {
        return tournamentRepository.findTournamentResult(winnerName, gender, date);
    }
}
