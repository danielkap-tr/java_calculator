package com.example.calculator.api.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

/**
 * Request DTO mapping to the JSON schema.
 */
public class CalculatorRequest {

    @NotNull(message = "Operation is required")
    @Pattern(regexp = "^(ADD|SUBTRACT|MULTIPLY|DIVIDE)$", message = "Invalid operation")
    private String operation;

    @NotNull(message = "Value 'a' is required")
    private Double a;

    @NotNull(message = "Value 'b' is required")
    private Double b;

    // Getters and Setters
    public String getOperation() { return operation; }
    public void setOperation(String operation) { this.operation = operation; }
    public Double getA() { return a; }
    public void setA(Double a) { this.a = a; }
    public Double getB() { return b; }
    public void setB(Double b) { this.b = b; }
}
