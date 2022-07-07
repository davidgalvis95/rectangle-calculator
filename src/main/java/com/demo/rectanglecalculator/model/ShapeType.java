package com.demo.rectanglecalculator.model;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashMap;
import java.util.Map;

public enum ShapeType {

    RECTANGLE("rectangle"),
    UNKNOWN("unknown");

    private final String value;

    private static final Map<String, ShapeType> STRING_SHAPE_TYPE_MAP = new HashMap<>();

    static {
        for (ShapeType type : ShapeType.values()) {
            STRING_SHAPE_TYPE_MAP.put(type.value, type);
        }
    }

    ShapeType(String value) {
        this.value = value;
    }

    public static ShapeType parseValue(String str) {
        ShapeType type = STRING_SHAPE_TYPE_MAP.get(str);
        return type == null ? ShapeType.UNKNOWN : type;
    }

    @JsonValue
    public String getValue(){
        return this.value;
    }
}
