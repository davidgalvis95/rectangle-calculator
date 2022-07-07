package com.demo.rectanglecalculator.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Builder
@Value
public class Vertex {

    @NotNull
    int xCoordinate;

    @NotNull
    int yCoordinate;
}
