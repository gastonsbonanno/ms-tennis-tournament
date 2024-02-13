package com.geopagos.mstennistournament.adapter.controller;

import com.geopagos.mstennistournament.adapter.controller.model.MatchResultResponseModel;
import com.geopagos.mstennistournament.adapter.controller.model.MatchExecutionRequestModel;
import com.geopagos.mstennistournament.application.port.in.ExecuteTournamentCommand;
import com.geopagos.mstennistournament.domain.MatchResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/match")
@Slf4j
public class MatchController {

    private ExecuteTournamentCommand executeTournamentCommand;

    public MatchController(ExecuteTournamentCommand executeTournamentCommand) {
        this.executeTournamentCommand = executeTournamentCommand;
    }

    @PostMapping(value = "/execute")
    public ResponseEntity<MatchResultResponseModel> execute(@RequestBody List<MatchExecutionRequestModel> playersRequestModel) {
        log.info("Start execution for players: {}", playersRequestModel);

        MatchResult matchResult = executeTournamentCommand.execute(playersRequestModel);
        MatchResultResponseModel matchResultResponseModel = MatchResultResponseModel.toResponseModel(matchResult);

        log.info("Match execution ends");
        return ResponseEntity.ok(matchResultResponseModel);
    }
}
