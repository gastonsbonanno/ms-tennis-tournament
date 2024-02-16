package com.geopagos.mstennistournament.adapter.sqlserver;

import com.geopagos.mstennistournament.adapter.sqlserver.model.TournamentResultSqlServerModel;
import com.geopagos.mstennistournament.application.port.out.TournamentRepository;
import com.geopagos.mstennistournament.domain.MatchResult;
import com.geopagos.mstennistournament.domain.TournamentResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Slf4j
public class SqlServerAdapter implements TournamentRepository {

    private static final String SAVED_ERROR_EXCEPTION = "Error saving data to database";
    private static final String FIND_ERROR_EXCEPTION = "Error finding data from database";
    private static final String SQL_SAVED = "Match result saved correctly";
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
            log.info(SQL_SAVED);
        } catch (Exception e) {
            log.error(SAVED_ERROR_EXCEPTION, e);
        }
    }

    @Override
    public List<TournamentResult> findTournamentResult(String winnerName, String gender, Date date) throws Exception {
        try {
            List<TournamentResultSqlServerModel> model = connection.findAllByExecutionDateAndGenderAndWinnerName(date, gender, winnerName);
            return model.stream().map(TournamentResultSqlServerModel::toDomain).collect(Collectors.toList());
        } catch (Exception e) {
            log.error(FIND_ERROR_EXCEPTION, e);
            throw new Exception(FIND_ERROR_EXCEPTION + ": "+ e.getMessage());
        }
    }

}
