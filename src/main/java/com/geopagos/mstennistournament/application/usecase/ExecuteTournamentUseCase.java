package com.geopagos.mstennistournament.application.usecase;

import com.geopagos.mstennistournament.application.port.in.ExecuteTournamentCommand;
import com.geopagos.mstennistournament.application.usecase.genderstrategy.FemaleStrategy;
import com.geopagos.mstennistournament.application.usecase.genderstrategy.GenderStrategy;
import com.geopagos.mstennistournament.application.usecase.genderstrategy.MaleStrategy;
import com.geopagos.mstennistournament.domain.Gender;
import com.geopagos.mstennistournament.domain.MatchResult;
import com.geopagos.mstennistournament.domain.Player;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExecuteTournamentUseCase implements ExecuteTournamentCommand {

    private final MaleStrategy maleStrategy;
    private final FemaleStrategy femaleStrategy;

    public ExecuteTournamentUseCase(@Qualifier("MaleStrategy") MaleStrategy maleStrategy, @Qualifier("FemaleStrategy") FemaleStrategy femaleStrategy) {
        this.maleStrategy = maleStrategy;
        this.femaleStrategy = femaleStrategy;
    }

    @Override
    public MatchResult execute(List<Player> players, Gender gender) {
        GenderStrategy strategy = Gender.MALE.equals(gender) ? maleStrategy : femaleStrategy;
        return strategy.executeTournament(players);
    }

}
