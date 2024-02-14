package com.geopagos.mstennistournament.adapter.controller.match;

import com.geopagos.mstennistournament.adapter.controller.match.model.MatchResultResponseModel;
import com.geopagos.mstennistournament.adapter.controller.match.model.MatchExecutionRequestModel;
import com.geopagos.mstennistournament.application.port.in.ExecuteTournamentCommand;
import com.geopagos.mstennistournament.domain.Gender;
import com.geopagos.mstennistournament.domain.MatchResult;
import com.geopagos.mstennistournament.domain.Player;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping(value = "/match")
@Slf4j
public class MatchController {

    private final ExecuteTournamentCommand executeTournamentCommand;
    private final MatchValidator validator;

    public MatchController(ExecuteTournamentCommand executeTournamentCommand, MatchValidator validator) {
        this.executeTournamentCommand = executeTournamentCommand;
        this.validator = validator;
    }

    @PostMapping(value = "/execute")
    public ResponseEntity<MatchResultResponseModel> execute(@RequestBody MatchExecutionRequestModel matchExecutionRequestModel, @RequestParam String matchGender) throws Exception {

        List<Player> validPlayers = validator.validate(matchExecutionRequestModel, matchGender);
        Gender gender = Gender.map(matchGender);

        List<String> playersNames = validPlayers.stream().map(Player::name).toList();
        log.info("Start execution {} match for players: {}", matchGender, playersNames);

        MatchResult matchResult = executeTournamentCommand.execute(validPlayers, gender);
        MatchResultResponseModel matchResultResponseModel = MatchResultResponseModel.toResponseModel(matchResult);

        log.info("Match execution ends");
        return ResponseEntity.ok(matchResultResponseModel);
    }


}
