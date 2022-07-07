package com.demo.rectanglecalculator.model.output;


import com.demo.rectanglecalculator.model.InteractionType;
import com.demo.rectanglecalculator.model.rectangle.Rectangle;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = true)
public class ContainmentRectangleResult extends ContainmentResult {

    private final Rectangle containedRectangle;

    @Builder
    public ContainmentRectangleResult(final InteractionType interactionType, final Rectangle containedRectangle) {
        super(interactionType);
        this.containedRectangle = containedRectangle;
    }
}
