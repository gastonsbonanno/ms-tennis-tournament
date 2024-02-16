package com.geopagos.mstennistournament.application.usecase.genderstrategy;

import com.geopagos.mstennistournament.domain.Gender;
import com.geopagos.mstennistournament.domain.MatchResult;
import com.geopagos.mstennistournament.domain.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

class TournamentTest {

    @InjectMocks
    Tournament tournament;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void executeTournamentMaleStragegy() {
        List<Player> requestPlayers = new ArrayList<>(List.of(new Player("Pedro",10,20,30,40, Gender.MALE)));

        MatchResult matchResult = tournament.execute(requestPlayers);

        Assertions.assertEquals("Pedro", matchResult.player().name());
        Assertions.assertEquals(10, matchResult.player().skillLevel());
        Assertions.assertEquals(Gender.MALE, matchResult.player().gender());
    }

}
