package com.demo.rectanglecalculator.service.rectangle;

import com.demo.rectanglecalculator.model.InteractionType;
import com.demo.rectanglecalculator.model.rectangle.Rectangle;
import com.demo.rectanglecalculator.model.Vertex;
import com.demo.rectanglecalculator.model.output.IntersectionResult;
import com.demo.rectanglecalculator.model.rectangle.RectangleType;
import com.demo.rectanglecalculator.service.ShapeIntersectionCalculator;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class RectangleIntersectionCalculator implements ShapeIntersectionCalculator<Rectangle> {

    public IntersectionResult calculateIntersectionPoints(final Map<String, Rectangle> rectangles){

        final Set<Vertex> interactionRectanglePoints = calculateAllRectanglePoints(rectangles.get(RectangleType.INTERACTION.name()));
        final Set<Vertex> thisRectangleRectanglePoints = calculateAllRectanglePoints(rectangles.get(RectangleType.THIS_RECTANGLE.name()));
        final Set<Vertex> otherRectangleRectanglePoints = calculateAllRectanglePoints(rectangles.get(RectangleType.OTHER_RECTANGLE.name()));

        final Set<Vertex> intersectionPoints =  interactionRectanglePoints.stream()
                .filter(point -> !thisRectangleRectanglePoints.contains(point))
                .filter(point -> !otherRectangleRectanglePoints.contains(point))
                .collect(Collectors.toSet());

        return IntersectionResult.builder()
                .interactionType(InteractionType.INTERSECTION)
                .intersectionPoints(intersectionPoints).build();
    }

    private Set<Vertex> calculateAllRectanglePoints(final Rectangle rectangle) {
        final Vertex topLeft = new Vertex(rectangle.getBottomLeft().getXCoordinate(), rectangle.getTopRight().getYCoordinate());
        final Vertex bottomRight = new Vertex(rectangle.getTopRight().getXCoordinate(), rectangle.getBottomLeft().getYCoordinate());

        return Set.of(rectangle.getBottomLeft(),
                topLeft,
                rectangle.getTopRight(),
                bottomRight);
    }
}
