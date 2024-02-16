package com.geopagos.mstennistournament.application.port.in;

import com.geopagos.mstennistournament.domain.TournamentResult;

import java.util.Date;
import java.util.List;

public interface FindResultsCommand {
    List<TournamentResult> execute(String winnerName, String gender, Date date) throws Exception;
}
