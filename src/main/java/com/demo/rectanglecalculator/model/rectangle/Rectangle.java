package com.demo.rectanglecalculator.model.rectangle;

import com.demo.rectanglecalculator.model.Shape;
import com.demo.rectanglecalculator.model.ShapeType;
import com.demo.rectanglecalculator.model.Vertex;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
public class Rectangle extends Shape {

    @NotNull
    private Vertex bottomLeft;

    @NotNull
    private Vertex topRight;

    public Rectangle(){
    }

    @Builder
    public Rectangle(UUID id, String name, ShapeType type, Vertex bottomLeft, Vertex topRight){
        super(id, name, type);
        this.bottomLeft = bottomLeft;
        this.topRight = topRight;
    }

    @Builder
    public Rectangle(String name, ShapeType type, Vertex bottomLeft, Vertex topRight){
        super(name, type);
        this.bottomLeft = bottomLeft;
        this.topRight = topRight;
    }
}
