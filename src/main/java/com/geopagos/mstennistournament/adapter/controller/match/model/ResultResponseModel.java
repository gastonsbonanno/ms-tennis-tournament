package com.geopagos.mstennistournament.adapter.controller.match.model;

import com.geopagos.mstennistournament.domain.TournamentResult;
import lombok.Builder;

import java.util.Date;

@Builder
public record ResultResponseModel(Date executionDate, String gender, String winnerName, Long matchsPlayed) {

    public static ResultResponseModel toResponseModel(TournamentResult tournamentResult) {
        return ResultResponseModel.builder()
                .executionDate(tournamentResult.executionDate())
                .gender(tournamentResult.gender())
                .winnerName(tournamentResult.winnerName())
                .matchsPlayed(tournamentResult.matchsPlayed())
                .build();
    }
}
