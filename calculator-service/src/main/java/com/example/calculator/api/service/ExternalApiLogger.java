package com.example.calculator.api.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Demonstrates sending REST requests with custom headers.
 */
@Service
public class ExternalApiLogger {

    private final RestTemplate restTemplate = new RestTemplate();

    /**
     * Simulation of sending the calculation result to an external logging service.
     * Demonstrates:
     * - RestTemplate (classic) or could use WebClient.
     * - Setting custom headers (JWT, Trace ID, etc.).
     */
    public void logToExternalService(String operation, double result) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("X-Logger-Source", "CalculatorService");
            headers.set("Authorization", "Bearer MOCK_TOKEN_FOR_EXTERNAL_LOGS");

            Map<String, Object> body = Map.of("op", operation, "res", result);
            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);

            // Simulation: sending to a mock endpoint
            // restTemplate.exchange("http://mock-logger:8080/log", HttpMethod.POST, entity, String.class);
            System.out.println("LOGGED to external service with headers: " + headers);
        } catch (Exception e) {
            // Log error
            System.err.println("Could not log to external service: " + e.getMessage());
        }
    }
}
