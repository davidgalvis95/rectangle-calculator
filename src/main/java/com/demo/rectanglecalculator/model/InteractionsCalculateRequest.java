package com.demo.rectanglecalculator.model;

import lombok.Data;

import java.util.List;

@Data
public class InteractionsCalculateRequest<T extends Shape> {

    private List<T> shapes;

    public InteractionsCalculateRequest(){}
}
