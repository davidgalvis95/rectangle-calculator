package com.demo.rectanglecalculator.model;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class InteractionsCalculateRequest<T extends Shape> {

    @NotNull
    @Valid
    private List<T> shapes;

    public InteractionsCalculateRequest(){}
}
