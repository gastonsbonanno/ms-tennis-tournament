package com.geopagos.mstennistournament.application.usecase;

import com.geopagos.mstennistournament.application.port.in.ExecuteTournamentCommand;
import com.geopagos.mstennistournament.application.usecase.genderstrategy.FemaleTournament;
import com.geopagos.mstennistournament.application.usecase.genderstrategy.Tournament;
import com.geopagos.mstennistournament.application.usecase.genderstrategy.TournamentExecution;
import com.geopagos.mstennistournament.application.usecase.genderstrategy.MaleTournament;
import com.geopagos.mstennistournament.domain.Gender;
import com.geopagos.mstennistournament.domain.MatchResult;
import com.geopagos.mstennistournament.domain.Player;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExecuteTournamentUseCase implements ExecuteTournamentCommand {

    private final MaleTournament maleTournament;
    private final FemaleTournament femaleTournament;
    private final Tournament tournament;

    public ExecuteTournamentUseCase(@Qualifier("MaleStrategy") MaleTournament maleTournament,
                                    @Qualifier("FemaleStrategy") FemaleTournament femaleTournament,
                                    Tournament tournament) {
        this.maleTournament = maleTournament;
        this.femaleTournament = femaleTournament;
        this.tournament = tournament;
    }

    @Override
    public MatchResult execute(List<Player> players, Gender gender) {
        return findStrategy(gender).execute(players);
    }

    private TournamentExecution findStrategy(Gender gender) {
        TournamentExecution tournamentExecution;
        switch (gender) {
            case MALE -> tournamentExecution = maleTournament;
            case FEMALE -> tournamentExecution = femaleTournament;
            default -> tournamentExecution = tournament;
        }
        return tournamentExecution;
    }

}
