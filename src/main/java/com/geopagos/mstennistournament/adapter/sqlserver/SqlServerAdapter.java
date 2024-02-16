package com.geopagos.mstennistournament.adapter.sqlserver;

import com.geopagos.mstennistournament.adapter.sqlserver.model.TournamentResultSqlServerModel;
import com.geopagos.mstennistournament.application.port.out.TournamentRepository;
import com.geopagos.mstennistournament.domain.Gender;
import com.geopagos.mstennistournament.domain.MatchResult;
import com.geopagos.mstennistournament.domain.Player;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
@Slf4j
public class SqlServerAdapter implements TournamentRepository {

    private static final String SQL_ERROR_EXCEPTION = "Error saving data to database";
    private final TournamentResultsConnection connection;

    public SqlServerAdapter(TournamentResultsConnection connection) {
        this.connection = connection;
    }

    public void saveTournamentResult(MatchResult matchResult) {
        try {
            TournamentResultSqlServerModel model = new TournamentResultSqlServerModel();
            model.setExecutionDate(new Date());
            model.setGender(matchResult.player().gender().name());
            model.setWinnerName(matchResult.player().name());
            model.setMatchsPlayed(matchResult.matchsPlayed());
            connection.save(model);
        } catch (Exception e) {
            log.error(SQL_ERROR_EXCEPTION, e);
        }
    }

}
