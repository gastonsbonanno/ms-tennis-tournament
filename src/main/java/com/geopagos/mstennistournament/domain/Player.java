package com.geopagos.mstennistournament.domain;

import lombok.NonNull;

public record Player(@NonNull String name,
                     int skillLevel,
                     int strength,
                     int speed,
                     int reaction,
                     @NonNull Gender gender) {
}
