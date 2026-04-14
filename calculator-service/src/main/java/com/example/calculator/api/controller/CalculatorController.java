package com.example.calculator.api.controller;

import com.example.calculator.api.entity.HistoryEntity;
import com.example.calculator.api.model.CalculatorRequest;
import com.example.calculator.api.repository.HistoryRepository;
import com.example.calculator.api.service.ExternalApiLogger;
import com.example.calculator.api.service.JooqHistoryService;
import com.example.calculator.core.Calculator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

/**
 * REST Controller for the Calculator service.
 * Demonstrates:
 * - @RestController, @RequestMapping: Standard Spring Web MVC.
 * - @Autowired: Dependency Injection (beans from library and local services).
 * - JPA and JOOQ integration in a single controller.
 */
@RestController
@RequestMapping("/calculator")
public class CalculatorController {

    private final Calculator calculator;
    private final HistoryRepository historyRepo;
    private final JooqHistoryService jooqService;
    private final ExternalApiLogger externalLogger;

    @Autowired
    public CalculatorController(Calculator calculator, 
                              HistoryRepository historyRepo, 
                              JooqHistoryService jooqService,
                              ExternalApiLogger externalLogger) {
        this.calculator = calculator;
        this.historyRepo = historyRepo;
        this.jooqService = jooqService;
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

        // 1. Save to database using JPA
        historyRepo.save(new HistoryEntity(request.getOperation(), a, b, result));

        // 2. Simulate external logging with custom headers
        externalLogger.logToExternalService(request.getOperation(), result);

        return ResponseEntity.ok(result);
    }

    /**
     * Fetch history using JPA.
     */
    @GetMapping("/history/jpa")
    public List<HistoryEntity> getHistoryJpa() {
        return historyRepo.findAll();
    }

    /**
     * Fetch history using JOOQ - Demonstrating direct SQL building.
     */
    @GetMapping("/history/jooq")
    public List<Map<String, Object>> getHistoryJooq(@RequestParam(defaultValue = "0") double minResult) {
        return jooqService.getCalculationsByResultGreaterThan(minResult);
    }
}
