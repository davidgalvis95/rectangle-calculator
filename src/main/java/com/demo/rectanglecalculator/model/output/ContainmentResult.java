package com.demo.rectanglecalculator.model.output;

import com.demo.rectanglecalculator.model.InteractionType;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public abstract class ContainmentResult extends Interaction {

    public ContainmentResult(final InteractionType interactionType) {
        super(interactionType);
    }
}
