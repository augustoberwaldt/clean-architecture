package com.example.entrypoint.rest.controller;

import com.example.application.usecase.customer.CreateCustomerUseCase;
import com.example.application.usecase.customer.ListCustomersUseCase;
import com.example.application.usecase.customer.SearchCustomerUseCase;
import com.example.entrypoint.rest.mapper.CustomerMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static com.example.stub.CustomerStub.getCustomer;
import static com.example.stub.CustomerStub.getCustomerResponse;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private SearchCustomerUseCase searchCustomerUseCase;

    @MockitoBean
    private ListCustomersUseCase listCustomersUseCase;

    @MockitoBean
    private CreateCustomerUseCase createCustomerUseCase;

    @MockitoBean
    private CustomerMapper customerMapper;

    @Test
    void search() throws Exception {

        String id = "cust-123";
        var customer = getCustomer().build();
        var response = getCustomerResponse()
                .id(id)
                .build();

        when(searchCustomerUseCase.execute(anyString())).thenReturn(customer);
        when(customerMapper.from(customer)).thenReturn(response);

        mockMvc.perform(get("/customer/{id}", id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value("Test Customer"))
                .andExpect(jsonPath("$.age").value(30));
    }
}
