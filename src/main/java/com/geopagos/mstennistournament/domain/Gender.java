package com.geopagos.mstennistournament.domain;

public enum Gender {
    MALE, FEMALE;

    public static Gender map(String name) {
        for(Gender val: values()){
            if(val.name().equals(name.toUpperCase()))
                return val;
        }
        return null;
    }
}
