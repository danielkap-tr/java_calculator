package com.example.calculator.api.controller;

import com.example.calculator.api.security.JwtService;
import com.example.calculator.api.model.CalculatorRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration Test for Calculator Controller.
 * Demonstrates:
 * - MockMvc: Testing web layer without starting a full server.
 * - JWT Authentication integration in tests.
 */
@SpringBootTest
@AutoConfigureMockMvc
class CalculatorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * Test Health endpoint - Public access.
     */
    @Test
    void testHealth() throws Exception {
        mockMvc.perform(get("/calculator/health"))
                .andExpect(status().isOk())
                .andExpect(content().string("Calculator Service is Up!"));
    }

    /**
     * Test Secure endpoint - Denied without token.
     */
    @Test
    void testCalculateWithoutToken() throws Exception {
        mockMvc.perform(post("/calculator/calculate"))
                .andExpect(status().isForbidden());
    }

    /**
     * Test Secure endpoint - Success with token.
     */
    @Test
    @SuppressWarnings("null")
    void testCalculateWithToken() throws Exception {
        UserDetails user = userDetailsService.loadUserByUsername("admin");
        String token = jwtService.generateToken(user);

        CalculatorRequest request = new CalculatorRequest();
        request.setOperation("ADD");
        request.setA(10.0);
        request.setB(20.0);

        mockMvc.perform(post("/calculator/calculate")
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().string("30.0"));
    }
}
