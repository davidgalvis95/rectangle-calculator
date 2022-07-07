package com.demo.rectanglecalculator.model.output;

import com.demo.rectanglecalculator.model.InteractionType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class Interaction {

    private InteractionType interactionType;
}
