package com.demo.rectanglecalculator.service.rectangle;

import com.demo.rectanglecalculator.model.rectangle.Rectangle;
import com.demo.rectanglecalculator.model.Vertex;
import com.demo.rectanglecalculator.model.rectangle.RectangleType;
import com.demo.rectanglecalculator.model.output.*;
import com.demo.rectanglecalculator.service.ShapeInteractionsCalculator;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Data
@Service
public class RectangleInteractionsCalculator implements ShapeInteractionsCalculator<Rectangle> {

    private Rectangle interactionRectangle;

    private RectangleContainmentCalculator containmentCalculator;

    private RectangleAdjacencyCalculator adjacencyCalculator;

    private RectangleIntersectionCalculator intersectionCalculator;


    @Autowired
    public RectangleInteractionsCalculator(final RectangleContainmentCalculator containmentCalculator,
                                           final RectangleAdjacencyCalculator adjacencyCalculator,
                                           final RectangleIntersectionCalculator intersectionCalculator) {
        this.containmentCalculator = containmentCalculator;
        this.adjacencyCalculator = adjacencyCalculator;
        this.intersectionCalculator = intersectionCalculator;
    }

    @Override
    public List<CalculationResult<Rectangle>> calculateAllInteractions(final List<Rectangle> rectangles) {

        if(rectangles.isEmpty()) {
            return Collections.emptyList();
        }

        final List<CalculationResult<Rectangle>> rectangleInteractionsResults = new ArrayList<>();
        final Rectangle[] rectanglesArray = rectangles.stream()
                .filter(Objects::nonNull)
                .toArray(Rectangle[]::new);

        for (int i = 0; i < rectanglesArray.length; i++) {
            for (int j = i + 1; j < rectanglesArray.length; j++) {
                rectangleInteractionsResults.add(calculateOneToOneInteractions(rectanglesArray[i], rectanglesArray[j]));
            }
        }

        return rectangleInteractionsResults;
    }

    @Override
    public CalculationResult<Rectangle> calculateOneToOneInteractions(final Rectangle thisRectangle, final Rectangle otherRectangle) {

        interactionRectangle = buildInteractionRectangle(thisRectangle, otherRectangle);

        if (interactionRectangle.getBottomLeft().getXCoordinate() > interactionRectangle.getTopRight().getXCoordinate() ||
                interactionRectangle.getBottomLeft().getYCoordinate() > interactionRectangle.getTopRight().getYCoordinate()) {
            return CalculationResult.<Rectangle>builder()
                    .thisShape(thisRectangle)
                    .otherShape(otherRectangle)
                    .thereIsInteraction(false)
                    .build();
        } else {
            return CalculationResult.<Rectangle>builder()
                    .thisShape(thisRectangle)
                    .otherShape(otherRectangle)
                    .thereIsInteraction(true)
                    .interaction(calculateShapeInteractions(thisRectangle, otherRectangle))
                    .build();
        }
    }

    private Interaction calculateShapeInteractions(final Rectangle thisRectangle, final Rectangle otherRectangle) {
        if (containmentCalculator.isContained(thisRectangle, interactionRectangle)) {
            return containmentCalculator.buildContainmentResult(thisRectangle);
        } else if (containmentCalculator.isContained(otherRectangle, interactionRectangle)) {
            return containmentCalculator.buildContainmentResult(otherRectangle);
        }

        final Optional<AdjacencyType> adjacencyType = adjacencyCalculator.checkForAdjacency(Map.of(RectangleType.INTERACTION.toString(), interactionRectangle));
        if (adjacencyType.isPresent()) {
            return adjacencyCalculator.calculateAdjacency(adjacencyType.get(), buildRectanglesMap(thisRectangle, otherRectangle));
        }

        return intersectionCalculator.calculateIntersectionPoints(buildRectanglesMap(thisRectangle, otherRectangle));
    }

    protected Rectangle buildInteractionRectangle(final Rectangle thisRectangle, final Rectangle otherRectangle) {
        return Rectangle.builder()
                .bottomLeft(Vertex.builder()
                        .xCoordinate(Math.max(thisRectangle.getBottomLeft().getXCoordinate(), otherRectangle.getBottomLeft().getXCoordinate()))
                        .yCoordinate(Math.max(thisRectangle.getBottomLeft().getYCoordinate(), otherRectangle.getBottomLeft().getYCoordinate()))
                        .build())
                .topRight(Vertex.builder()
                        .xCoordinate(Math.min(thisRectangle.getTopRight().getXCoordinate(), otherRectangle.getTopRight().getXCoordinate()))
                        .yCoordinate(Math.min(thisRectangle.getTopRight().getYCoordinate(), otherRectangle.getTopRight().getYCoordinate()))
                        .build())
                .build();
    }

    private Map<String, Rectangle> buildRectanglesMap(final Rectangle thisRectangle, final Rectangle otherRectangle) {
        return Map.of(RectangleType.INTERACTION.toString(), interactionRectangle,
                RectangleType.THIS_RECTANGLE.toString(), thisRectangle,
                RectangleType.OTHER_RECTANGLE.toString(), otherRectangle);
    }
}
