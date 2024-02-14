package com.geopagos.mstennistournament.adapter.controller.match.model;

import com.geopagos.mstennistournament.domain.MatchResult;
import lombok.Builder;

@Builder
public record MatchResultResponseModel() {

    public static MatchResultResponseModel toResponseModel(MatchResult matchResult) {
        return MatchResultResponseModel.builder().build();
    }
}
