package com.demo.rectanglecalculator.service;

import com.demo.rectanglecalculator.model.InteractionType;
import com.demo.rectanglecalculator.model.Vertex;
import com.demo.rectanglecalculator.model.output.AdjacencyResult;
import com.demo.rectanglecalculator.model.output.AdjacencySubType;
import com.demo.rectanglecalculator.model.output.AdjacencyType;
import com.demo.rectanglecalculator.model.rectangle.Rectangle;
import com.demo.rectanglecalculator.service.rectangle.RectangleAdjacencyCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class RectangleAdjacencyCalculatorTest {

    private RectangleAdjacencyCalculator rectangleAdjacencyCalculator;

    @BeforeEach
    void setUp(){
        rectangleAdjacencyCalculator = new RectangleAdjacencyCalculator();
    }

    @Test
    void checkForYAdjacencyTest(){

        final Rectangle interactionRectangle = Rectangle.builder()
                .bottomLeft(Vertex.builder()
                        .xCoordinate(10)
                        .yCoordinate(2)
                        .build())
                .topRight(Vertex.builder()
                        .xCoordinate(10)
                        .yCoordinate(6)
                        .build())
                .build();

        final Map<String, Rectangle> rectangleMap = Map.of("INTERACTION", interactionRectangle);
        final Optional<AdjacencyType> adjacencyTypeOptional = rectangleAdjacencyCalculator.checkForAdjacency(rectangleMap);

        assertTrue(adjacencyTypeOptional.isPresent());
        assertEquals(AdjacencyType.Y_ADJACENCY, adjacencyTypeOptional.get());
    }

    @Test
    void checkForXAdjacencyTest(){
        final Rectangle interactionRectangle = Rectangle.builder()
                .bottomLeft(Vertex.builder()
                        .xCoordinate(2)
                        .yCoordinate(10)
                        .build())
                .topRight(Vertex.builder()
                        .xCoordinate(6)
                        .yCoordinate(10)
                        .build())
                .build();

        final Map<String, Rectangle> rectangleMap = Map.of("INTERACTION", interactionRectangle);
        final Optional<AdjacencyType> adjacencyTypeOptional = rectangleAdjacencyCalculator.checkForAdjacency(rectangleMap);

        assertTrue(adjacencyTypeOptional.isPresent());
        assertEquals(AdjacencyType.X_ADJACENCY, adjacencyTypeOptional.get());
    }

    @Test
    void checkForCornerAdjacencyTest(){
        final Rectangle interactionRectangle = Rectangle.builder()
                .bottomLeft(Vertex.builder()
                        .xCoordinate(10)
                        .yCoordinate(2)
                        .build())
                .topRight(Vertex.builder()
                        .xCoordinate(10)
                        .yCoordinate(2)
                        .build())
                .build();

        final Map<String, Rectangle> rectangleMap = Map.of("INTERACTION", interactionRectangle);
        final Optional<AdjacencyType> adjacencyTypeOptional = rectangleAdjacencyCalculator.checkForAdjacency(rectangleMap);

        assertTrue(adjacencyTypeOptional.isPresent());
        assertEquals(AdjacencyType.CORNER, adjacencyTypeOptional.get());
    }

    @Test
    void calculateAdjacencyWhenXSubLine(){

        final Rectangle interactionRectangle = Rectangle.builder()
                .bottomLeft(Vertex.builder()
                        .xCoordinate(2)
                        .yCoordinate(8)
                        .build())
                .topRight(Vertex.builder()
                        .xCoordinate(7)
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
                        .xCoordinate(2)
                        .yCoordinate(8)
                        .build())
                .topRight(Vertex.builder()
                        .xCoordinate(7)
                        .yCoordinate(9)
                        .build())
                .build();

        final Map<String, Rectangle> rectangleMap = Map.of(
                "INTERACTION", interactionRectangle,
                "THIS_RECTANGLE", thisRectangle,
                "OTHER_RECTANGLE", otherRectangle);

        final AdjacencyResult adjacencyResult = rectangleAdjacencyCalculator.calculateAdjacency(AdjacencyType.X_ADJACENCY, rectangleMap);

        final AdjacencyResult adjacencyResultCopy = AdjacencyResult.builder()
                .interactionType(InteractionType.ADJACENCY)
                .type(AdjacencyType.X_ADJACENCY)
                .subType(AdjacencySubType.SUB_LINE)
                .from(interactionRectangle.getBottomLeft())
                .to(interactionRectangle.getTopRight())
                .build();

        assertNotNull(adjacencyResult);
        assertEquals(adjacencyResult.getInteractionType(), adjacencyResultCopy.getInteractionType());
        assertEquals(adjacencyResult.getType(), adjacencyResultCopy.getType());
        assertEquals(adjacencyResult.getSubType(), adjacencyResultCopy.getSubType());
        assertEquals(adjacencyResult.getFrom(), adjacencyResultCopy.getFrom());
        assertEquals(adjacencyResult.getTo(), adjacencyResultCopy.getTo());
    }

    @Test
    void calculateAdjacencyWhenYSubLine(){

        final Rectangle interactionRectangle = Rectangle.builder()
                .bottomLeft(Vertex.builder()
                        .xCoordinate(10)
                        .yCoordinate(2)
                        .build())
                .topRight(Vertex.builder()
                        .xCoordinate(10)
                        .yCoordinate(6)
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
                        .xCoordinate(10)
                        .yCoordinate(2)
                        .build())
                .topRight(Vertex.builder()
                        .xCoordinate(13)
                        .yCoordinate(6)
                        .build())
                .build();

        final Map<String, Rectangle> rectangleMap = Map.of(
                "INTERACTION", interactionRectangle,
                "THIS_RECTANGLE", thisRectangle,
                "OTHER_RECTANGLE", otherRectangle);

        final AdjacencyResult adjacencyResult = rectangleAdjacencyCalculator.calculateAdjacency(AdjacencyType.Y_ADJACENCY, rectangleMap);

        final AdjacencyResult adjacencyResultCopy = AdjacencyResult.builder()
                .interactionType(InteractionType.ADJACENCY)
                .type(AdjacencyType.Y_ADJACENCY)
                .subType(AdjacencySubType.SUB_LINE)
                .from(interactionRectangle.getBottomLeft())
                .to(interactionRectangle.getTopRight())
                .build();

        assertNotNull(adjacencyResult);
        assertEquals(adjacencyResult.getInteractionType(), adjacencyResultCopy.getInteractionType());
        assertEquals(adjacencyResult.getType(), adjacencyResultCopy.getType());
        assertEquals(adjacencyResult.getSubType(), adjacencyResultCopy.getSubType());
        assertEquals(adjacencyResult.getFrom(), adjacencyResultCopy.getFrom());
        assertEquals(adjacencyResult.getTo(), adjacencyResultCopy.getTo());
    }

    @Test
    void calculateAdjacencyWhenXProper(){

        final Rectangle interactionRectangle = Rectangle.builder()
                .bottomLeft(Vertex.builder()
                        .xCoordinate(0)
                        .yCoordinate(8)
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
                        .xCoordinate(0)
                        .yCoordinate(8)
                        .build())
                .topRight(Vertex.builder()
                        .xCoordinate(10)
                        .yCoordinate(9)
                        .build())
                .build();

        final Map<String, Rectangle> rectangleMap = Map.of(
                "INTERACTION", interactionRectangle,
                "THIS_RECTANGLE", thisRectangle,
                "OTHER_RECTANGLE", otherRectangle);

        final AdjacencyResult adjacencyResult = rectangleAdjacencyCalculator.calculateAdjacency(AdjacencyType.X_ADJACENCY, rectangleMap);

        final AdjacencyResult adjacencyResultCopy = AdjacencyResult.builder()
                .interactionType(InteractionType.ADJACENCY)
                .type(AdjacencyType.X_ADJACENCY)
                .subType(AdjacencySubType.PROPER)
                .from(interactionRectangle.getBottomLeft())
                .to(interactionRectangle.getTopRight())
                .build();

        assertNotNull(adjacencyResult);
        assertEquals(adjacencyResult.getInteractionType(), adjacencyResultCopy.getInteractionType());
        assertEquals(adjacencyResult.getType(), adjacencyResultCopy.getType());
        assertEquals(adjacencyResult.getSubType(), adjacencyResultCopy.getSubType());
        assertEquals(adjacencyResult.getFrom(), adjacencyResultCopy.getFrom());
        assertEquals(adjacencyResult.getTo(), adjacencyResultCopy.getTo());
    }

    @Test
    void calculateAdjacencyWhenYProper(){

        final Rectangle interactionRectangle = Rectangle.builder()
                .bottomLeft(Vertex.builder()
                        .xCoordinate(10)
                        .yCoordinate(2)
                        .build())
                .topRight(Vertex.builder()
                        .xCoordinate(10)
                        .yCoordinate(6)
                        .build())
                .build();

        final Rectangle thisRectangle = Rectangle.builder()
                .bottomLeft(Vertex.builder()
                        .xCoordinate(0)
                        .yCoordinate(2)
                        .build())
                .topRight(Vertex.builder()
                        .xCoordinate(10)
                        .yCoordinate(6)
                        .build())
                .build();

        final Rectangle otherRectangle = Rectangle.builder()
                .bottomLeft(Vertex.builder()
                        .xCoordinate(10)
                        .yCoordinate(2)
                        .build())
                .topRight(Vertex.builder()
                        .xCoordinate(13)
                        .yCoordinate(6)
                        .build())
                .build();

        final Map<String, Rectangle> rectangleMap = Map.of(
                "INTERACTION", interactionRectangle,
                "THIS_RECTANGLE", thisRectangle,
                "OTHER_RECTANGLE", otherRectangle);

        final AdjacencyResult adjacencyResult = rectangleAdjacencyCalculator.calculateAdjacency(AdjacencyType.Y_ADJACENCY, rectangleMap);

        final AdjacencyResult adjacencyResultCopy = AdjacencyResult.builder()
                .interactionType(InteractionType.ADJACENCY)
                .type(AdjacencyType.Y_ADJACENCY)
                .subType(AdjacencySubType.PROPER)
                .from(interactionRectangle.getBottomLeft())
                .to(interactionRectangle.getTopRight())
                .build();

        assertNotNull(adjacencyResult);
        assertEquals(adjacencyResult.getInteractionType(), adjacencyResultCopy.getInteractionType());
        assertEquals(adjacencyResult.getType(), adjacencyResultCopy.getType());
        assertEquals(adjacencyResult.getSubType(), adjacencyResultCopy.getSubType());
        assertEquals(adjacencyResult.getFrom(), adjacencyResultCopy.getFrom());
        assertEquals(adjacencyResult.getTo(), adjacencyResultCopy.getTo());
    }

    @Test
    void calculateAdjacencyWhenXPartial(){

        final Rectangle interactionRectangle = Rectangle.builder()
                .bottomLeft(Vertex.builder()
                        .xCoordinate(9)
                        .yCoordinate(8)
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
                        .xCoordinate(9)
                        .yCoordinate(8)
                        .build())
                .topRight(Vertex.builder()
                        .xCoordinate(13)
                        .yCoordinate(11)
                        .build())
                .build();

        final Map<String, Rectangle> rectangleMap = Map.of(
                "INTERACTION", interactionRectangle,
                "THIS_RECTANGLE", thisRectangle,
                "OTHER_RECTANGLE", otherRectangle);

        final AdjacencyResult adjacencyResult = rectangleAdjacencyCalculator.calculateAdjacency(AdjacencyType.X_ADJACENCY, rectangleMap);

        final AdjacencyResult adjacencyResultCopy = AdjacencyResult.builder()
                .interactionType(InteractionType.ADJACENCY)
                .type(AdjacencyType.X_ADJACENCY)
                .subType(AdjacencySubType.PARTIAL)
                .from(interactionRectangle.getBottomLeft())
                .to(interactionRectangle.getTopRight())
                .build();

        assertNotNull(adjacencyResult);
        assertEquals(adjacencyResult.getInteractionType(), adjacencyResultCopy.getInteractionType());
        assertEquals(adjacencyResult.getType(), adjacencyResultCopy.getType());
        assertEquals(adjacencyResult.getSubType(), adjacencyResultCopy.getSubType());
        assertEquals(adjacencyResult.getFrom(), adjacencyResultCopy.getFrom());
        assertEquals(adjacencyResult.getTo(), adjacencyResultCopy.getTo());
    }

    @Test
    void calculateAdjacencyWhenYPartial(){

        final Rectangle interactionRectangle = Rectangle.builder()
                .bottomLeft(Vertex.builder()
                        .xCoordinate(10)
                        .yCoordinate(7)
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
                        .xCoordinate(10)
                        .yCoordinate(7)
                        .build())
                .topRight(Vertex.builder()
                        .xCoordinate(13)
                        .yCoordinate(11)
                        .build())
                .build();

        final Map<String, Rectangle> rectangleMap = Map.of(
                "INTERACTION", interactionRectangle,
                "THIS_RECTANGLE", thisRectangle,
                "OTHER_RECTANGLE", otherRectangle);

        final AdjacencyResult adjacencyResult = rectangleAdjacencyCalculator.calculateAdjacency(AdjacencyType.Y_ADJACENCY, rectangleMap);

        final AdjacencyResult adjacencyResultCopy = AdjacencyResult.builder()
                .interactionType(InteractionType.ADJACENCY)
                .type(AdjacencyType.Y_ADJACENCY)
                .subType(AdjacencySubType.PARTIAL)
                .from(interactionRectangle.getBottomLeft())
                .to(interactionRectangle.getTopRight())
                .build();

        assertNotNull(adjacencyResult);
        assertEquals(adjacencyResult.getInteractionType(), adjacencyResultCopy.getInteractionType());
        assertEquals(adjacencyResult.getType(), adjacencyResultCopy.getType());
        assertEquals(adjacencyResult.getSubType(), adjacencyResultCopy.getSubType());
        assertEquals(adjacencyResult.getFrom(), adjacencyResultCopy.getFrom());
        assertEquals(adjacencyResult.getTo(), adjacencyResultCopy.getTo());
    }

    @Test
    void calculateAdjacencyWhenCorner(){

        final Rectangle interactionRectangle = Rectangle.builder()
                .bottomLeft(Vertex.builder()
                        .xCoordinate(10)
                        .yCoordinate(8)
                        .build())
                .topRight(Vertex.builder()
                        .xCoordinate(10)
                        .yCoordinate(8)
                        .build())
                .build();

        final Map<String, Rectangle> rectangleMap = Map.of(
                "INTERACTION", interactionRectangle);

        final AdjacencyResult adjacencyResult = rectangleAdjacencyCalculator.calculateAdjacency(AdjacencyType.CORNER, rectangleMap);

        final AdjacencyResult adjacencyResultCopy = AdjacencyResult.builder()
                .interactionType(InteractionType.ADJACENCY)
                .type(AdjacencyType.CORNER)
                .from(interactionRectangle.getBottomLeft())
                .to(interactionRectangle.getTopRight())
                .build();

        assertNotNull(adjacencyResult);
        assertEquals(adjacencyResult.getInteractionType(), adjacencyResultCopy.getInteractionType());
        assertEquals(adjacencyResult.getType(), adjacencyResultCopy.getType());
        assertEquals(adjacencyResult.getFrom(), adjacencyResultCopy.getFrom());
        assertEquals(adjacencyResult.getTo(), adjacencyResultCopy.getTo());
    }
}
