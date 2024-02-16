package com.geopagos.mstennistournament.adapter.sqlserver;

import com.geopagos.mstennistournament.adapter.sqlserver.model.TournamentResultSqlServerModel;
import com.geopagos.mstennistournament.domain.Gender;
import com.geopagos.mstennistournament.domain.MatchResult;
import com.geopagos.mstennistournament.domain.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class SqlServerAdapterTest {

    @InjectMocks
    SqlServerAdapter sqlServerAdapter;

    @Mock
    TournamentResultsConnection connection;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void executeSqlServerAdapterSaveTournamentResultCorrectly() {
        MatchResult matchResult = MatchResult.builder()
                .matchsPlayed(10L)
                .player(new Player("Pedro",10,20,30,40, Gender.MALE)).build();

        TournamentResultSqlServerModel expectedModel = new TournamentResultSqlServerModel();
        expectedModel.setExecutionDate(new Date());
        expectedModel.setGender("MALE");
        expectedModel.setWinnerName("Pedro");
        expectedModel.setMatchsPlayed(10L);

        sqlServerAdapter.saveTournamentResult(matchResult);

        verify(connection, times(1)).save(expectedModel);
    }
}
