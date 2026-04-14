package com.example.calculator.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.example.calculator.core.Calculator;

/**
 * Main application entry point.
 * Demonstrates:
 * - @SpringBootApplication: Key annotation for Spring Boot auto-configuration,
 *   component scanning, and configuration.
 * - @Bean: Manual registration of a bean from the external library.
 */
@SpringBootApplication(scanBasePackages = "com.example.calculator")
public class CalculatorApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(CalculatorApiApplication.class, args);
    }

    /**
     * Creating a Bean for the Calculator from the core library.
     * This allows it to be @Autowired into other components.
     */
    @Bean
    public Calculator calculator() {
        return new Calculator();
    }
}
