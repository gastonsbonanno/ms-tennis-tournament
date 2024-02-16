package com.geopagos.mstennistournament.adapter.controller.match;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.geopagos.mstennistournament.adapter.controller.match.model.MatchExecutionRequestModel;
import com.geopagos.mstennistournament.adapter.controller.match.model.MatchResultResponseModel;
import com.geopagos.mstennistournament.application.port.in.ExecuteTournamentCommand;
import com.geopagos.mstennistournament.domain.Gender;
import com.geopagos.mstennistournament.domain.MatchResult;
import com.geopagos.mstennistournament.domain.Player;
import com.geopagos.mstennistournament.factory.MocksFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MatchController.class)
class MatchControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ExecuteTournamentCommand executeTournamentCommand;

    @MockBean
    private MatchValidator validator;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void executeValidRequestReturnsMatchResultResponse() throws Exception {
        List<Player> requestPlayers = new ArrayList<>();
        requestPlayers.addAll(MocksFactory.getMalePlayersMock());
        requestPlayers.addAll(MocksFactory.getFemalePlayersMock());

        MatchExecutionRequestModel requestModel = new MatchExecutionRequestModel(requestPlayers);
        List<Player> validPlayers = MocksFactory.getMalePlayersMock();

        Gender gender = Gender.MALE;
        MatchResult matchResult = new MatchResult(new Player("Pedro",10,20,30,40, Gender.MALE));
        MatchResultResponseModel expectedResponseModel = new MatchResultResponseModel(new Player("Pedro",10,20,30,40, Gender.MALE));

        when(validator.validate(requestModel, gender.name())).thenReturn(validPlayers);
        when(executeTournamentCommand.execute(validPlayers, gender)).thenReturn(matchResult);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/match/execute")
                        .queryParam("matchGender","MALE")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(requestModel)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(expectedResponseModel)));

        verify(validator).validate(requestModel, gender.name());
        verify(executeTournamentCommand).execute(validPlayers, gender);
    }



    @Test
    void executeRequestReturnsMatchResultResponseWithErrorResponse() throws Exception {
        List<Player> requestPlayers = new ArrayList<>();
        requestPlayers.addAll(MocksFactory.getMalePlayersMock());
        requestPlayers.addAll(MocksFactory.getFemalePlayersMock());

        MatchExecutionRequestModel requestModel = new MatchExecutionRequestModel(requestPlayers);

        when(validator.validate(any(), any())).thenThrow(new Exception(""));

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/match/execute")
                        .queryParam("matchGender","MAL")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(requestModel)))
                .andExpect(status().isInternalServerError());

        verify(validator).validate(any(), any());
        verify(executeTournamentCommand, never()).execute(any(), any());
    }
}
