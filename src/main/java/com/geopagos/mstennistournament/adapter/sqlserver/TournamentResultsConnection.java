package com.geopagos.mstennistournament.adapter.sqlserver;

import com.geopagos.mstennistournament.adapter.sqlserver.model.TournamentResultSqlServerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface TournamentResultsConnection extends JpaRepository<TournamentResultSqlServerModel, Long> {

    @Query("select tr from TournamentResultSqlServerModel tr where " +
            "(:date is null or :date = tr.executionDate) and " +
            "(:gender is null or :gender = tr.gender) and " +
            "(:winnerName is null or :winnerName = tr.winnerName)")
    List<TournamentResultSqlServerModel> findAllByExecutionDateAndGenderAndWinnerName(Date date,
                                                                                      String gender,
                                                                                      String winnerName);

}
