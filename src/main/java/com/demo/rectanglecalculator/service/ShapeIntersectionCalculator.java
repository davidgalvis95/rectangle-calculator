package com.demo.rectanglecalculator.service;

import com.demo.rectanglecalculator.model.Shape;
import com.demo.rectanglecalculator.model.output.IntersectionResult;

import java.util.Map;

public interface ShapeIntersectionCalculator<T extends Shape> {

    IntersectionResult calculateIntersectionPoints(final Map<String, T> rectangles);

}
