package com.demo.rectanglecalculator.service;

import com.demo.rectanglecalculator.model.InteractionType;
import com.demo.rectanglecalculator.model.Vertex;
import com.demo.rectanglecalculator.model.output.*;
import com.demo.rectanglecalculator.model.rectangle.Rectangle;
import com.demo.rectanglecalculator.service.rectangle.RectangleAdjacencyCalculator;
import com.demo.rectanglecalculator.service.rectangle.RectangleContainmentCalculator;
import com.demo.rectanglecalculator.service.rectangle.RectangleInteractionsCalculator;
import com.demo.rectanglecalculator.service.rectangle.RectangleIntersectionCalculator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ContextConfiguration(classes = {
        RectangleInteractionsCalculator.class,
        RectangleIntersectionCalculator.class,
        RectangleAdjacencyCalculator.class,
        RectangleContainmentCalculator.class,
})
public class RectangleInteractionsCalculatorTest {

    @Autowired
    private RectangleInteractionsCalculator rectangleInteractionsCalculator;


    @Test
    void calculateOneToOneInteractionsWhenContained(){
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
                        .xCoordinate(3)
                        .yCoordinate(5)
                        .build())
                .topRight(Vertex.builder()
                        .xCoordinate(6)
                        .yCoordinate(7)
                        .build())
                .build();

        final CalculationResult<Rectangle> result = rectangleInteractionsCalculator.calculateOneToOneInteractions(thisRectangle, otherRectangle);
        final ContainmentRectangleResult containmentResult = (ContainmentRectangleResult) result.getInteraction();

        assertTrue(result.isThereIsInteraction());
        assertEquals(thisRectangle, result.getThisShape());
        assertEquals(otherRectangle, result.getOtherShape());
        assertEquals(InteractionType.CONTAINMENT, containmentResult.getInteractionType());
        assertEquals(otherRectangle, containmentResult.getContainedRectangle());
    }

    @Test
    void calculateOneToOneInteractionsWhenAdjacency(){
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
                        .xCoordinate(2)
                        .yCoordinate(8)
                        .build())
                .topRight(Vertex.builder()
                        .xCoordinate(7)
                        .yCoordinate(9)
                        .build())
                .build();

        final AdjacencyResult adjacencyExpectedResult = AdjacencyResult.builder()
                .interactionType(InteractionType.ADJACENCY)
                .type(AdjacencyType.X_ADJACENCY)
                .subType(AdjacencySubType.SUB_LINE)
                .from(Vertex.builder()
                        .xCoordinate(2)
                        .yCoordinate(8)
                        .build())
                .to(Vertex.builder()
                        .xCoordinate(7)
                        .yCoordinate(8)
                        .build())
                .build();

        final CalculationResult<Rectangle> result = rectangleInteractionsCalculator.calculateOneToOneInteractions(thisRectangle, otherRectangle);
        final AdjacencyResult adjacencyCalculatedResult = (AdjacencyResult) result.getInteraction();

        assertTrue(result.isThereIsInteraction());
        assertEquals(thisRectangle, result.getThisShape());
        assertEquals(otherRectangle, result.getOtherShape());
        assertNotNull(adjacencyCalculatedResult);
        assertEquals(adjacencyExpectedResult.getInteractionType(), adjacencyCalculatedResult.getInteractionType());
        assertEquals(adjacencyExpectedResult.getType(), adjacencyCalculatedResult.getType());
        assertEquals(adjacencyExpectedResult.getSubType(), adjacencyCalculatedResult.getSubType());
        assertEquals(adjacencyExpectedResult.getFrom(), adjacencyCalculatedResult.getFrom());
        assertEquals(adjacencyExpectedResult.getTo(), adjacencyCalculatedResult.getTo());
    }

    @Test
    void calculateOneToOneInteractionsWhenIntersections(){
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

        final Set<Vertex> intersectionPoints = Set.of(
                Vertex.builder()
                        .xCoordinate(8)
                        .yCoordinate(8)
                        .build(),
                Vertex.builder()
                        .xCoordinate(10)
                        .yCoordinate(6)
                        .build());

        final CalculationResult<Rectangle> result = rectangleInteractionsCalculator.calculateOneToOneInteractions(thisRectangle, otherRectangle);
        final IntersectionResult intersectionResult = (IntersectionResult) result.getInteraction();

        assertTrue(result.isThereIsInteraction());
        assertEquals(thisRectangle, result.getThisShape());
        assertEquals(otherRectangle, result.getOtherShape());
        assertNotNull(intersectionResult);
        assertEquals(InteractionType.INTERSECTION, intersectionResult.getInteractionType());
        assertEquals(2, intersectionResult.getIntersectionPoints().size());
        assertTrue(intersectionResult.getIntersectionPoints().containsAll(intersectionPoints));
    }

    @Test
    void calculateOneToOneInteractionsWhenNoInteractions(){

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
                        .xCoordinate(2)
                        .yCoordinate(10)
                        .build())
                .topRight(Vertex.builder()
                        .xCoordinate(7)
                        .yCoordinate(11)
                        .build())
                .build();

        final CalculationResult<Rectangle> result = rectangleInteractionsCalculator.calculateOneToOneInteractions(thisRectangle, otherRectangle);

        assertFalse(result.isThereIsInteraction());
        assertEquals(thisRectangle, result.getThisShape());
        assertEquals(otherRectangle, result.getOtherShape());
        assertNull(result.getInteraction());
    }

}
