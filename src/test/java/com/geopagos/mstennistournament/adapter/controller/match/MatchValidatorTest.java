package com.geopagos.mstennistournament.adapter.controller.match;

import com.geopagos.mstennistournament.adapter.controller.match.model.MatchExecutionRequestModel;
import com.geopagos.mstennistournament.domain.Player;
import com.geopagos.mstennistournament.factory.MocksFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class MatchValidatorTest {

    @InjectMocks
    private MatchValidator matchValidator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void executeValidateMatchCorrectly() throws Exception {

        List<Player> requestPlayers = new ArrayList<>();
        requestPlayers.addAll(MocksFactory.getMalePlayersMock());
        requestPlayers.addAll(MocksFactory.getFemalePlayersMock());

        MatchExecutionRequestModel requestModel = new MatchExecutionRequestModel(requestPlayers);
        String matchGender = "MALE";

        List<Player> players = matchValidator.validateExecute(requestModel, matchGender);

        assertEquals(MocksFactory.getMalePlayersMock(), players);
    }

    @Test
    void executeValidateMatchWithError() throws Exception {

        List<Player> requestPlayers = new ArrayList<>();
        requestPlayers.addAll(MocksFactory.getMalePlayersMock());
        requestPlayers.addAll(MocksFactory.getFemalePlayersMock());

        MatchExecutionRequestModel requestModel = new MatchExecutionRequestModel(requestPlayers);
        String matchGender = "MAL";

        assertThrows(Exception.class, () -> matchValidator.validateExecute(requestModel, matchGender));
    }
}
