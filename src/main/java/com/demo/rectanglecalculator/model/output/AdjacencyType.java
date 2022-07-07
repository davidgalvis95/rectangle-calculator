package com.demo.rectanglecalculator.model.output;

import com.fasterxml.jackson.annotation.JsonValue;

public enum AdjacencyType {

    X_ADJACENCY("x-adjacency"),
    Y_ADJACENCY("y-adjacency"),
    CORNER("corner");

    private final String value;

    AdjacencyType(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue(){
        return this.value;
    }
}
