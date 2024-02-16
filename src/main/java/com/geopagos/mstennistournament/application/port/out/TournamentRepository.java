package com.geopagos.mstennistournament.application.port.out;

import com.geopagos.mstennistournament.domain.MatchResult;

public interface TournamentRepository {
    void saveTournamentResult(MatchResult matchResult);
}
