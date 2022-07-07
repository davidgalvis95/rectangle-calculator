package com.demo.rectanglecalculator.service;

import com.demo.rectanglecalculator.model.InteractionType;
import com.demo.rectanglecalculator.model.Vertex;
import com.demo.rectanglecalculator.model.output.ContainmentRectangleResult;
import com.demo.rectanglecalculator.model.rectangle.Rectangle;
import com.demo.rectanglecalculator.service.rectangle.RectangleContainmentCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RectangleContainmentCalculatorTest {

    private RectangleContainmentCalculator rectangleContainmentCalculator;

    @BeforeEach
    void setUp(){
        rectangleContainmentCalculator = new RectangleContainmentCalculator();
    }

    @Test
    void isContainedTest(){
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

        final Rectangle rectangle = Rectangle.builder()
                .bottomLeft(Vertex.builder()
                        .xCoordinate(8)
                        .yCoordinate(6)
                        .build())
                .topRight(Vertex.builder()
                        .xCoordinate(10)
                        .yCoordinate(8)
                        .build())
                .build();

        assertTrue(rectangleContainmentCalculator.isContained(rectangle, interactionRectangle));
    }

    @Test
    void isNotContainedTest(){
        final Rectangle interactionRectangle = Rectangle.builder()
                .bottomLeft(Vertex.builder()
                        .xCoordinate(8)
                        .yCoordinate(6)
                        .build())
                .topRight(Vertex.builder()
                        .xCoordinate(11)
                        .yCoordinate(9)
                        .build())
                .build();

        final Rectangle rectangle = Rectangle.builder()
                .bottomLeft(Vertex.builder()
                        .xCoordinate(8)
                        .yCoordinate(6)
                        .build())
                .topRight(Vertex.builder()
                        .xCoordinate(10)
                        .yCoordinate(8)
                        .build())
                .build();

        assertFalse(rectangleContainmentCalculator.isContained(rectangle, interactionRectangle));
    }

    @Test
    void buildContainmentResultTest(){
        final Rectangle rectangle = Rectangle.builder()
                .bottomLeft(Vertex.builder()
                        .xCoordinate(8)
                        .yCoordinate(6)
                        .build())
                .topRight(Vertex.builder()
                        .xCoordinate(10)
                        .yCoordinate(8)
                        .build())
                .build();

        final ContainmentRectangleResult containmentRectangleResult = rectangleContainmentCalculator.buildContainmentResult(rectangle);

        assertEquals(InteractionType.CONTAINMENT, containmentRectangleResult.getInteractionType());
        assertEquals(rectangle, containmentRectangleResult.getContainedRectangle());
    }
}
