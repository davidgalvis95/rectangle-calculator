package com.demo.rectanglecalculator.controller;

import com.demo.rectanglecalculator.model.InteractionsCalculateRequest;
import com.demo.rectanglecalculator.model.Shape;
import com.demo.rectanglecalculator.model.output.CalculationResult;
import com.demo.rectanglecalculator.service.ShapeInteractionsCalculator;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/interactions")
public class RectangleInteractionsController<T extends Shape> {

    private ShapeInteractionsCalculator<T> rectangleInteractionsCalculator;

    @GetMapping
    public ResponseEntity<List<CalculationResult<T>>> getInteractionsForShapes(@RequestBody @Valid final InteractionsCalculateRequest<T> interactionsCalculateRequest) {
        return ResponseEntity.ok(rectangleInteractionsCalculator.calculateAllInteractions(interactionsCalculateRequest.getShapes()));
    }
}
