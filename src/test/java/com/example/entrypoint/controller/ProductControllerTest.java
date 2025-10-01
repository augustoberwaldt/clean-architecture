package com.example.entrypoint.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.ApplicationTests;
import com.example.entrypoint.rest.request.ProductRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static com.example.stub.ProductStub.getProductRequest;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ProductControllerTest extends ApplicationTests {

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void create() throws Exception {

        ProductRequest request = getProductRequest();

        mockMvc.perform(post("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id").exists())
            .andExpect(jsonPath("$.name").value("Test Product"))
            .andExpect(jsonPath("$.description").value("Test Description"))
            .andExpect(jsonPath("$.value").value(10.0));
    }
}