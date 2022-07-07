package com.demo.rectanglecalculator.model.output;

import com.demo.rectanglecalculator.model.InteractionType;
import com.demo.rectanglecalculator.model.Vertex;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = true)
public class AdjacencyResult extends Interaction{

    private final AdjacencyType type;

    private final AdjacencySubType subType;

    private final Vertex from;

    private final Vertex to;

    @Builder
    public AdjacencyResult(final InteractionType interactionType, final AdjacencyType type, final AdjacencySubType subType, final Vertex from, final Vertex to) {
        super(interactionType);
        this.type = type;
        this.subType = subType;
        this.from = from;
        this.to = to;
    }
}
