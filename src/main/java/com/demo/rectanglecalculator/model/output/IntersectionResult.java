package com.demo.rectanglecalculator.model.output;

import com.demo.rectanglecalculator.model.InteractionType;
import com.demo.rectanglecalculator.model.Vertex;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Set;

@Getter
@EqualsAndHashCode(callSuper = true)
public class IntersectionResult extends Interaction {

    private final Set<Vertex> intersectionPoints;

    @Builder
    public IntersectionResult(final InteractionType interactionType, final Set<Vertex> intersectionPoints) {
        super(interactionType);
        this.intersectionPoints = intersectionPoints;
    }
}
