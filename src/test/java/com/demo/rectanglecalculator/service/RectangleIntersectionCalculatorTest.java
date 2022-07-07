package com.demo.rectanglecalculator.service;

import com.demo.rectanglecalculator.model.InteractionType;
import com.demo.rectanglecalculator.model.Vertex;
import com.demo.rectanglecalculator.model.output.IntersectionResult;
import com.demo.rectanglecalculator.model.rectangle.Rectangle;
import com.demo.rectanglecalculator.service.rectangle.RectangleIntersectionCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RectangleIntersectionCalculatorTest {

    private RectangleIntersectionCalculator rectangleIntersectionCalculator;

    @BeforeEach
    void setUp(){
        rectangleIntersectionCalculator = new RectangleIntersectionCalculator();
    }

    @Test
    void calculateIntersectionPointsTest(){
        final Rectangle interactionRectangle = Rectangle.builder()
                .bottomLeft(Vertex.builder()
                        .xCoordinate(8)
                        .yCoordinate(6)
                        .build())
                .topRight(Vertex.builder()
                        .xCoordinate(10)
                        .yCoordinate(8)
                        .build())
                .build();

        final Rectangle thisRectangle = Rectangle.builder()
                .bottomLeft(Vertex.builder()
                        .xCoordinate(0)
                        .yCoordinate(0)
                        .build())
                .topRight(Vertex.builder()
                        .xCoordinate(10)
                        .yCoordinate(8)
                        .build())
                .build();

        final Rectangle otherRectangle = Rectangle.builder()
                .bottomLeft(Vertex.builder()
                        .xCoordinate(8)
                        .yCoordinate(6)
                        .build())
                .topRight(Vertex.builder()
                        .xCoordinate(11)
                        .yCoordinate(9)
                        .build())
                .build();

        final Map<String, Rectangle> rectangleMap = Map.of(
                "INTERACTION", interactionRectangle,
                "THIS_RECTANGLE", thisRectangle,
                "OTHER_RECTANGLE", otherRectangle);

        final IntersectionResult intersectionResult = rectangleIntersectionCalculator.calculateIntersectionPoints(rectangleMap);

        final Set<Vertex> intersectionPoints = Set.of(
                Vertex.builder()
                        .xCoordinate(8)
                        .yCoordinate(8)
                        .build(),
                Vertex.builder()
                        .xCoordinate(10)
                        .yCoordinate(6)
                        .build());

        assertEquals(InteractionType.INTERSECTION, intersectionResult.getInteractionType());
        assertEquals(2, intersectionResult.getIntersectionPoints().size());
        assertTrue(intersectionResult.getIntersectionPoints().containsAll(intersectionPoints));
    }
}
