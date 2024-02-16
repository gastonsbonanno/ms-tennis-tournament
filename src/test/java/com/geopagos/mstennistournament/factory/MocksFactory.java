package com.geopagos.mstennistournament.factory;

import com.geopagos.mstennistournament.domain.Gender;
import com.geopagos.mstennistournament.domain.Player;

import java.util.List;

public class MocksFactory {

    public static List<Player> getMalePlayersMock(){
        return List.of(
                new Player("Pedro",10,20,30,40, Gender.MALE),
                new Player("Roberto",10,20,30,40, Gender.MALE),
                new Player("Julian",10,20,30,40, Gender.MALE)
        );
    }

    public static List<Player> getFemalePlayersMock(){
        return List.of(
                new Player("Maria",10,20,30,40, Gender.FEMALE),
                new Player("Julieta",10,20,30,40, Gender.FEMALE),
                new Player("Sofia",10,20,30,40, Gender.FEMALE)
        );
    }


}
