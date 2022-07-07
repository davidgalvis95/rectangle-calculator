package com.demo.rectanglecalculator.model;

import com.demo.rectanglecalculator.deserializer.ShapeDeserializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@AllArgsConstructor
@JsonDeserialize(using = ShapeDeserializer.class)
public abstract class Shape {

    @JsonIgnore
    private UUID id;

    private String name;

    @NotNull
    private ShapeType type;

    public Shape(){}

    public Shape(String name, ShapeType type) {
        this.name = name;
        this.type = type;
    }
}
