package com.demo.rectanglecalculator.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@AllArgsConstructor
@Builder
@Value
public class Vertex {

    int xCoordinate;

    int yCoordinate;
}
