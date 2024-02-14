package com.geopagos.mstennistournament.adapter.controller.match.model;

import com.geopagos.mstennistournament.domain.Player;

import java.util.List;

public record MatchExecutionRequestModel(List<Player> players, String matchGender) {
}
