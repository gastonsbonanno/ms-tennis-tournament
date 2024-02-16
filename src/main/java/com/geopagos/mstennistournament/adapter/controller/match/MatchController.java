package com.geopagos.mstennistournament.adapter.controller.match;

import com.geopagos.mstennistournament.adapter.controller.match.model.MatchExecutionRequestModel;
import com.geopagos.mstennistournament.adapter.controller.match.model.MatchExecutionResponseModel;
import com.geopagos.mstennistournament.adapter.controller.match.model.ResultResponseModel;
import com.geopagos.mstennistournament.application.port.in.ExecuteTournamentCommand;
import com.geopagos.mstennistournament.application.port.in.FindResultsCommand;
import com.geopagos.mstennistournament.domain.Gender;
import com.geopagos.mstennistournament.domain.MatchResult;
import com.geopagos.mstennistournament.domain.Player;
import com.geopagos.mstennistournament.domain.TournamentResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/match")
@Slf4j
public class MatchController {

    private final ExecuteTournamentCommand executeTournamentCommand;
    private final MatchValidator validator;
    private final FindResultsCommand findResultsCommand;

    public MatchController(ExecuteTournamentCommand executeTournamentCommand, MatchValidator validator, FindResultsCommand findResultsCommand) {
        this.executeTournamentCommand = executeTournamentCommand;
        this.validator = validator;
        this.findResultsCommand = findResultsCommand;
    }

    @PostMapping(value = "/execute")
    public ResponseEntity<MatchExecutionResponseModel> execute(@RequestBody MatchExecutionRequestModel matchExecutionRequestModel, @RequestParam String matchGender) throws Exception {

        List<Player> validPlayers = validator.validateExecute(matchExecutionRequestModel, matchGender);
        Gender gender = Gender.map(matchGender);

        List<String> playersNames = validPlayers.stream().map(Player::name).toList();
        log.info("Start execution {} match for players: {}", matchGender, playersNames);

        MatchResult matchResult = executeTournamentCommand.execute(validPlayers, gender);
        MatchExecutionResponseModel matchExecutionResponseModel = MatchExecutionResponseModel.toResponseModel(matchResult);

        log.info("Match execution ends");
        return ResponseEntity.ok(matchExecutionResponseModel);
    }

    @GetMapping(value = "/results")
    public ResponseEntity<List<ResultResponseModel>> findResults(
            @RequestParam(required = false) String winnerName,
            @RequestParam(required = false) String gender,
            @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date date
    ) throws Exception {
        validator.validateFindResults(gender);
        log.info("Find results begin with winnerName: {}, gender: {} and date: {}", winnerName, gender, date);
        List<TournamentResult> tournamentResult = findResultsCommand.execute(winnerName, gender, date);
        List<ResultResponseModel> resultResponseModels = tournamentResult.stream().map(ResultResponseModel::toResponseModel).toList();
        return ResponseEntity.ok(resultResponseModels);
    }


}
