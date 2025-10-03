package com.example.entrypoint.rest.controller;

import com.example.ApplicationTests;
import com.example.infrastructure.persistence.entity.ProductEntity;
import com.example.infrastructure.persistence.repository.ProductDataRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.UUID;

import static com.example.stub.ProductStub.getProductEntity;
import static com.example.stub.ProductStub.getProductRequest;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ProductControllerTest extends ApplicationTests {

    @MockitoBean
    private ProductDataRepository productDataRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void create() throws Exception {

        var id = UUID.randomUUID();
        var request = getProductRequest();
        var stubEntity = getProductEntity()
                .id(id)
                .build();

        when(productDataRepository.save(any(ProductEntity.class))).thenReturn(stubEntity);

        mockMvc.perform(post("/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(id.toString()))
                .andExpect(jsonPath("$.name").value("Test Product"))
                .andExpect(jsonPath("$.description").value("Test Description"))
                .andExpect(jsonPath("$.value").value(10.0));
    }


    @Test
    void search() {
    }
}