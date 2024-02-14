package com.geopagos.mstennistournament.adapter.controller.match.model;

import com.geopagos.mstennistournament.domain.MatchResult;
import com.geopagos.mstennistournament.domain.Player;
import lombok.Builder;

@Builder
public record MatchResultResponseModel(Player player) {

    public static MatchResultResponseModel toResponseModel(MatchResult matchResult) {
        return MatchResultResponseModel.builder().player(matchResult.player()).build();
    }
}
