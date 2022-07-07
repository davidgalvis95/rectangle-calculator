package com.demo.rectanglecalculator.service.rectangle;

import com.demo.rectanglecalculator.model.InteractionType;
import com.demo.rectanglecalculator.model.rectangle.Rectangle;
import com.demo.rectanglecalculator.model.output.AdjacencyResult;
import com.demo.rectanglecalculator.model.output.AdjacencySubType;
import com.demo.rectanglecalculator.model.output.AdjacencyType;
import com.demo.rectanglecalculator.model.rectangle.RectangleType;
import com.demo.rectanglecalculator.service.ShapeAdjacencyCalculator;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

@Component
public class RectangleAdjacencyCalculator implements ShapeAdjacencyCalculator<Rectangle> {

    public Optional<AdjacencyType> checkForAdjacency(final Map<String, Rectangle> rectangles) {

        final Rectangle interactionRectangle = rectangles.get(RectangleType.INTERACTION.toString());

        if (interactionRectangle.getBottomLeft().getYCoordinate() == interactionRectangle.getTopRight().getYCoordinate() &&
                interactionRectangle.getBottomLeft().getXCoordinate() == interactionRectangle.getTopRight().getXCoordinate()) {
            return Optional.of(AdjacencyType.CORNER);
        } else if (interactionRectangle.getBottomLeft().getXCoordinate() == interactionRectangle.getTopRight().getXCoordinate()) {
            return Optional.of(AdjacencyType.Y_ADJACENCY);
        } else if (interactionRectangle.getBottomLeft().getYCoordinate() == interactionRectangle.getTopRight().getYCoordinate()) {
            return Optional.of(AdjacencyType.X_ADJACENCY);
        } else {
            return Optional.empty();
        }
    }

    public AdjacencyResult calculateAdjacency(final AdjacencyType adjacencyType, final Map<String, Rectangle> rectangles) {

        final Rectangle interactionRectangle = rectangles.get(RectangleType.INTERACTION.toString());
        final Rectangle thisRectangle = rectangles.get(RectangleType.THIS_RECTANGLE.toString());
        final Rectangle otherRectangle = rectangles.get(RectangleType.OTHER_RECTANGLE.toString());

        final AdjacencyResult.AdjacencyResultBuilder adjacencyResultBuilder = AdjacencyResult.builder().interactionType(InteractionType.ADJACENCY);
        if (adjacencyType == AdjacencyType.X_ADJACENCY) {
            adjacencyResultBuilder
                    .type(AdjacencyType.X_ADJACENCY)
                    .from(interactionRectangle.getBottomLeft())
                    .to(interactionRectangle.getTopRight());

            if (rectanglesHaveProperXAdjacency(thisRectangle, otherRectangle, interactionRectangle)) {
                return adjacencyResultBuilder
                        .subType(AdjacencySubType.PROPER)
                        .build();
            } else if (rectanglesHaveSubLineXAdjacency(thisRectangle, otherRectangle, interactionRectangle)) {
                return adjacencyResultBuilder
                        .subType(AdjacencySubType.SUB_LINE)
                        .build();
            } else {
                return adjacencyResultBuilder
                        .subType(AdjacencySubType.PARTIAL)
                        .build();
            }
        } else if (adjacencyType == AdjacencyType.Y_ADJACENCY) {

            adjacencyResultBuilder
                    .type(AdjacencyType.Y_ADJACENCY)
                    .from(interactionRectangle.getBottomLeft())
                    .to(interactionRectangle.getTopRight());

            if (rectanglesHaveProperYAdjacency(thisRectangle, otherRectangle, interactionRectangle)) {
                return adjacencyResultBuilder
                        .subType(AdjacencySubType.PROPER)
                        .build();
            } else if (rectanglesHaveSubLineYAdjacency(thisRectangle, otherRectangle, interactionRectangle)) {
                return adjacencyResultBuilder
                        .subType(AdjacencySubType.SUB_LINE)
                        .build();
            } else {
                return adjacencyResultBuilder
                        .subType(AdjacencySubType.PARTIAL)
                        .build();
            }
        } else {
            return adjacencyResultBuilder
                    .type(AdjacencyType.CORNER)
                    .from(interactionRectangle.getBottomLeft())
                    .to(interactionRectangle.getTopRight())
                    .build();
        }
    }

    private boolean rectanglesHaveProperXAdjacency(final Rectangle rectangle1, final Rectangle rectangle2, final Rectangle interactionRectangle) {

        return interactionRectangle.getBottomLeft().getXCoordinate() == rectangle1.getBottomLeft().getXCoordinate() &&
                interactionRectangle.getTopRight().getXCoordinate() == rectangle1.getTopRight().getXCoordinate() &&
                interactionRectangle.getBottomLeft().getXCoordinate() == rectangle2.getBottomLeft().getXCoordinate() &&
                interactionRectangle.getTopRight().getXCoordinate() == rectangle2.getTopRight().getXCoordinate();
    }

    private boolean rectanglesHaveProperYAdjacency(final Rectangle rectangle1, final Rectangle rectangle2, final Rectangle interactionRectangle) {

        return interactionRectangle.getBottomLeft().getYCoordinate() == rectangle1.getBottomLeft().getYCoordinate() &&
                interactionRectangle.getTopRight().getYCoordinate() == rectangle1.getTopRight().getYCoordinate() &&
                interactionRectangle.getBottomLeft().getYCoordinate() == rectangle2.getBottomLeft().getYCoordinate() &&
                interactionRectangle.getTopRight().getYCoordinate() == rectangle2.getTopRight().getYCoordinate();
    }

    private boolean rectanglesHaveSubLineXAdjacency(final Rectangle rectangle1, final Rectangle rectangle2, final Rectangle interactionRectangle) {

        return (interactionRectangle.getBottomLeft().getXCoordinate() == rectangle1.getBottomLeft().getXCoordinate() &&
                interactionRectangle.getTopRight().getXCoordinate() == rectangle1.getTopRight().getXCoordinate()) ||
                (interactionRectangle.getBottomLeft().getXCoordinate() == rectangle2.getBottomLeft().getXCoordinate() &&
                        interactionRectangle.getTopRight().getXCoordinate() == rectangle2.getTopRight().getXCoordinate());
    }

    private boolean rectanglesHaveSubLineYAdjacency(final Rectangle rectangle1, final Rectangle rectangle2, final Rectangle interactionRectangle) {

        return (interactionRectangle.getBottomLeft().getYCoordinate() == rectangle1.getBottomLeft().getYCoordinate() &&
                interactionRectangle.getTopRight().getYCoordinate() == rectangle1.getTopRight().getYCoordinate()) ||
                (interactionRectangle.getBottomLeft().getYCoordinate() == rectangle2.getBottomLeft().getYCoordinate() &&
                        interactionRectangle.getTopRight().getYCoordinate() == rectangle2.getTopRight().getYCoordinate());
    }
}
