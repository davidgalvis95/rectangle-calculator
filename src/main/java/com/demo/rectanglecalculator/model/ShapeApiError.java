package com.demo.rectanglecalculator.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ShapeApiError {

    private LocalDateTime datetime;

    private Integer statusCode;

    private String errorMessage;

    private String detail;

    private String sourcePath;
}
