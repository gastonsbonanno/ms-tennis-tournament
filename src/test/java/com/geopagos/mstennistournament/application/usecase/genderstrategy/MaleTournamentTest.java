package com.geopagos.mstennistournament.application.usecase.genderstrategy;

import com.geopagos.mstennistournament.domain.Player;
import com.geopagos.mstennistournament.factory.MocksFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

class MaleTournamentTest {

    @InjectMocks
    MaleTournament maleTournament;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void executeTournamentMaleStragegy() {

        List<Player> requestPlayers = new ArrayList<>(MocksFactory.getMalePlayersMock());

        maleTournament.execute(requestPlayers);

    }

}
