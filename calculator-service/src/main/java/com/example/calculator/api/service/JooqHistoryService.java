package com.example.calculator.api.service;

import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

/**
 * Service demonstrating the use of JOOQ for complex or direct SQL queries.
 * Demonstrates:
 * - DSLContext: The entry point for JOOQ query building.
 * - Integration with the same database managed by JPA.
 */
@Service
public class JooqHistoryService {

    private final DSLContext dsl;

    @Autowired
    public JooqHistoryService(DSLContext dsl) {
        this.dsl = dsl;
    }

    /**
     * Fetch all history using JOOQ.
     * Demonstrates query building using JOOQ's DSL.
     */
    public List<Map<String, Object>> getCalculationsByResultGreaterThan(double minResult) {
        // Querying the "calculation_history" table using string-based field references
        // as we haven't run the JOOQ code generator yet.
        return dsl.select()
                .from(DSL.table("calculation_history"))
                .where(DSL.field("result").gt(minResult))
                .fetchMaps();
    }
}
