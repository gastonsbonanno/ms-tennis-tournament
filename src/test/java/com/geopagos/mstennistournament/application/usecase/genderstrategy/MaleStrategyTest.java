package com.geopagos.mstennistournament.application.usecase.genderstrategy;

import com.geopagos.mstennistournament.domain.Player;
import com.geopagos.mstennistournament.factory.MocksFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MaleStrategyTest {

    @InjectMocks
    MaleStrategy maleStrategy;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void executeTournamentMaleStragegy() {

        List<Player> requestPlayers = new ArrayList<>(MocksFactory.getMalePlayersMock());

        maleStrategy.executeTournament(requestPlayers);

    }

}
