package com.demo.rectanglecalculator.model.output;

import com.demo.rectanglecalculator.model.Shape;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CalculationResult<T extends Shape> {

    private T thisShape;

    private T otherShape;

    boolean thereIsInteraction;

    Interaction interaction;
}
