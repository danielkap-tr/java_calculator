package com.example.calculator.api.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

/**
 * JPA Entity for calculation history.
 * Demonstrates:
 * - @Entity: JPA managed object.
 * - @Table: Database table mapping.
 * - @Id, @GeneratedValue: Primary key mapping.
 */
@Entity
@Table(name = "calculation_history")
public class HistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String operation;
    private double inputA;
    private double inputB;
    private double result;
    
    @Column(name = "calculation_time")
    private LocalDateTime calculationTime;

    // Constructors
    public HistoryEntity() {}

    public HistoryEntity(String operation, double inputA, double inputB, double result) {
        this.operation = operation;
        this.inputA = inputA;
        this.inputB = inputB;
        this.result = result;
        this.calculationTime = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() { return id; }
    public String getOperation() { return operation; }
    public double getInputA() { return inputA; }
    public double getInputB() { return inputB; }
    public double getResult() { return result; }
    public LocalDateTime getCalculationTime() { return calculationTime; }
    
    public void setId(Long id) { this.id = id; }
    public void setOperation(String operation) { this.operation = operation; }
    public void setInputA(double inputA) { this.inputA = inputA; }
    public void setInputB(double inputB) { this.inputB = inputB; }
    public void setResult(double result) { this.result = result; }
    public void setCalculationTime(LocalDateTime calculationTime) { this.calculationTime = calculationTime; }
}
