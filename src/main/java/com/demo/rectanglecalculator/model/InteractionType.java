package com.demo.rectanglecalculator.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum InteractionType {

    ADJACENCY("adjacency"),
    INTERSECTION("intersection"),
    CONTAINMENT("containment");

    private final String value;

    InteractionType(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue(){
        return this.value;
    }
}
