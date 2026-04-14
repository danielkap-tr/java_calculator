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
@Getter
@Setter
@NoArgsConstructor
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

    public HistoryEntity(String operation, double inputA, double inputB, double result) {
        this.operation = operation;
        this.inputA = inputA;
        this.inputB = inputB;
        this.result = result;
        this.calculationTime = LocalDateTime.now();
    }
}
