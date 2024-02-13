package com.geopagos.mstennistournament.adapter.controller.model;

import com.geopagos.mstennistournament.domain.Gender;
import com.geopagos.mstennistournament.domain.Player;

import java.util.List;

public record MatchExecutionRequestModel(List<Player> players, String matchGender) {
}
