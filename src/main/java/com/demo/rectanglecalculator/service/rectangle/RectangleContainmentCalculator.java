package com.demo.rectanglecalculator.service.rectangle;

import com.demo.rectanglecalculator.model.InteractionType;
import com.demo.rectanglecalculator.model.rectangle.Rectangle;
import com.demo.rectanglecalculator.model.output.ContainmentRectangleResult;
import com.demo.rectanglecalculator.service.ShapeContainmentCalculator;
import org.springframework.stereotype.Component;

@Component
public class RectangleContainmentCalculator implements ShapeContainmentCalculator<Rectangle> {

    public boolean isContained(final Rectangle rectangle, final Rectangle interactionRectangle) {
        return interactionRectangle.getBottomLeft().getXCoordinate() == rectangle.getBottomLeft().getXCoordinate() &&
                interactionRectangle.getBottomLeft().getYCoordinate() == rectangle.getBottomLeft().getYCoordinate() &&
                interactionRectangle.getTopRight().getXCoordinate() == rectangle.getTopRight().getXCoordinate() &&
                interactionRectangle.getTopRight().getYCoordinate() == rectangle.getTopRight().getYCoordinate();
    }

    public ContainmentRectangleResult buildContainmentResult(final Rectangle rectangle){
        return ContainmentRectangleResult.builder()
                .interactionType(InteractionType.CONTAINMENT)
                .containedRectangle(rectangle)
                .build();
    }
}
