package com.demo.rectanglecalculator.service;

import com.demo.rectanglecalculator.model.Shape;
import com.demo.rectanglecalculator.model.output.AdjacencyResult;
import com.demo.rectanglecalculator.model.output.AdjacencyType;

import java.util.Map;
import java.util.Optional;

public interface ShapeAdjacencyCalculator<T extends Shape> {

    Optional<AdjacencyType> checkForAdjacency(final Map<String, T> rectangles);

    AdjacencyResult calculateAdjacency(final AdjacencyType adjacencyType, final Map<String, T> rectangles);
}
