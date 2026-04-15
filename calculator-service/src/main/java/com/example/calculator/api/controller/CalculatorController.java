package com.example.calculator.api.controller;

import com.example.calculator.api.model.CalculatorRequest;
import com.example.calculator.api.service.ExternalApiLogger;
import com.example.calculator.core.Calculator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST Controller for the Calculator service.
 * Demonstrates:
 * - @RestController, @RequestMapping: Standard Spring Web MVC.
 * - @Autowired: Dependency Injection (beans from library and local services).
 */
@RestController
@RequestMapping("/calculator")
public class CalculatorController {

    private final Calculator calculator;
    private final ExternalApiLogger externalLogger;

    @Autowired
    public CalculatorController(Calculator calculator, 
                              ExternalApiLogger externalLogger) {
        this.calculator = calculator;
        this.externalLogger = externalLogger;
    }

    /**
     * Health check - Public.
     */
    @GetMapping("/health")
    public String health() {
        return "Calculator Service is Up!";
    }

    /**
     * Main calculation endpoint - Secured by JWT.
     */
    @PostMapping("/calculate")
    public ResponseEntity<Double> calculate(@Valid @RequestBody CalculatorRequest request) {
        double result;
        double a = request.getA();
        double b = request.getB();

        switch (request.getOperation().toUpperCase()) {
            case "ADD" -> result = calculator.add(a, b);
            case "SUBTRACT" -> result = calculator.subtract(a, b);
            case "MULTIPLY" -> result = calculator.multiply(a, b);
            case "DIVIDE" -> result = calculator.divide(a, b);
            default -> throw new IllegalArgumentException("Unsupported operation");
        }


        // 2. Simulate external logging with custom headers
        externalLogger.logToExternalService(request.getOperation(), result);

        return ResponseEntity.ok(result);
    }

}
