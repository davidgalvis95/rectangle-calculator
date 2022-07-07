package com.demo.rectanglecalculator.model.output;

import com.fasterxml.jackson.annotation.JsonValue;

public enum AdjacencySubType {

    PROPER("proper"),
    SUB_LINE("sub-line"),
    PARTIAL("partial");

    private final String value;

    AdjacencySubType(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue(){
        return this.value;
    }
}
