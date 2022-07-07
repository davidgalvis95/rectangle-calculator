package com.demo.rectanglecalculator.service;

import com.demo.rectanglecalculator.model.Shape;
import com.demo.rectanglecalculator.model.output.*;

import java.util.List;
import java.util.Set;

public interface ShapeInteractionsCalculator<T extends Shape> {

    List<CalculationResult<T>> calculateAllInteractions(List<T> shapes);

    CalculationResult<T> calculateOneToOneInteractions(T thisShape, T otherShape);
}
