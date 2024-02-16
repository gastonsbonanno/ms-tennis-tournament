package com.geopagos.mstennistournament.adapter.controller.match.model;

import com.geopagos.mstennistournament.domain.MatchResult;
import com.geopagos.mstennistournament.domain.Player;
import lombok.Builder;

@Builder
public record MatchExecutionResponseModel(Player player) {

    public static MatchExecutionResponseModel toResponseModel(MatchResult matchResult) {
        return MatchExecutionResponseModel.builder().player(matchResult.player()).build();
    }
}
