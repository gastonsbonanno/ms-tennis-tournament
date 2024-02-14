package com.geopagos.mstennistournament.application.usecase.genderstrategy;

import com.geopagos.mstennistournament.domain.Gender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
public class TournamentGenderStrategy {

    public static GenderStrategy findStrategy(Gender gender) {
        if(Gender.MALE.equals(gender)){
            return new MaleStrategy();
        } else {
            return new FemaleStrategy();
        }
    }
}
