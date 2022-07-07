package com.demo.rectanglecalculator.deserializer;

import com.demo.rectanglecalculator.model.Shape;
import com.demo.rectanglecalculator.model.ShapeType;
import com.demo.rectanglecalculator.model.Vertex;
import com.demo.rectanglecalculator.model.rectangle.Rectangle;
import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class ShapeDeserializer extends StdDeserializer<Shape> {

    protected ShapeDeserializer(Class<?> src) {
        super(src);
    }

    public ShapeDeserializer() {
        this(Shape.class);
    }

    @Override
    public Shape deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        final JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        final ObjectMapper mapper = (ObjectMapper)jsonParser.getCodec();

        if (node.hasNonNull("type")) {

            if(node.get("type").textValue().equals(ShapeType.RECTANGLE.getValue())) {
                final Vertex bottomLeft = mapper.treeToValue(node.get("bottomLeft"), Vertex.class);
                final Vertex topRight = mapper.treeToValue(node.get("topRight"), Vertex.class);
                final String name = node.get("name").textValue();
                final ShapeType type = ShapeType.parseValue(node.get("type").textValue());
                return new Rectangle(name, type, bottomLeft, topRight);
            }
        }

        throw new JsonParseException(jsonParser, "Shape type was not able to be parsed, a known shape 'type' must be passed");
    }
}
