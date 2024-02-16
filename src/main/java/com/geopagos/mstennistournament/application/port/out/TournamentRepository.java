package com.geopagos.mstennistournament.application.port.out;

import com.geopagos.mstennistournament.domain.MatchResult;
import com.geopagos.mstennistournament.domain.TournamentResult;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface TournamentRepository {
    void saveTournamentResult(MatchResult matchResult);
    List<TournamentResult> findTournamentResult(String winnerName, String gender, Date date) throws Exception;
}
