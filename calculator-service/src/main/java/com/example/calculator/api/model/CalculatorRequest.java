package com.example.calculator.api.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

/**
 * Request DTO mapping to the JSON schema.
 */
@Data
public class CalculatorRequest {

    @NotNull(message = "Operation is required")
    @Pattern(regexp = "^(ADD|SUBTRACT|MULTIPLY|DIVIDE)$", message = "Invalid operation")
    private String operation;

    @NotNull(message = "Value 'a' is required")
    private Double a;

    @NotNull(message = "Value 'b' is required")
    private Double b;
}
