package com.geopagos.mstennistournament.domain;

import lombok.Builder;

import java.util.Date;

@Builder
public record TournamentResult(Date executionDate, String gender, String winnerName, Long matchsPlayed) {

}
