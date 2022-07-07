package com.demo.rectanglecalculator.service;

import com.demo.rectanglecalculator.model.Shape;
import com.demo.rectanglecalculator.model.output.ContainmentResult;

public interface ShapeContainmentCalculator<T extends Shape> {

    boolean isContained(final T thisShape, final T otherShape);

    ContainmentResult buildContainmentResult(final T shape);
}
