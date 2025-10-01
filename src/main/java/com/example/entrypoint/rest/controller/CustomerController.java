package com.example.entrypoint.rest.controller;

import com.example.application.usecase.customer.CreateCustomerUseCase;
import com.example.application.usecase.customer.ListCustomersUseCase;
import com.example.application.usecase.customer.SearchCustomerUseCase;
import com.example.entrypoint.rest.mapper.CustomerMapper;
import com.example.entrypoint.rest.request.CustomerRequest;
import com.example.entrypoint.rest.response.PaginationPageResponse;
import com.example.entrypoint.rest.response.CustomerResponse;
import com.example.shared.PaginationComponent;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@Tag(name = "Clientes", description = "API de Clientes")
@RequiredArgsConstructor
@RestController
@RequestMapping("customer")
public class CustomerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);

    private final ListCustomersUseCase listCustomersUseCase;
    private final SearchCustomerUseCase searchCustomerUseCase;
    private final CreateCustomerUseCase createCustomerUseCase;
    private final CustomerMapper customerMapper;


    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> search(@PathVariable String id) {

        LOGGER.info("Received request to Search Customer by id: {}", id);
        var customer = searchCustomerUseCase.execute(id.trim());
        var customerResponse = customerMapper.from(customer);
        return ResponseEntity.ok(customerResponse);
    }

    @PostMapping
    public ResponseEntity<CustomerResponse> create(@RequestBody CustomerRequest request) {

        LOGGER.info("Received request to Create Customer using {}", request);
        var customer = createCustomerUseCase.execute(customerMapper.from(request));
        var customerResponse = customerMapper.from(customer);
        var createdUri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(customerResponse.getId())
                .toUri();

        return ResponseEntity.created(createdUri).body(customerResponse);
    }

    @GetMapping
    public ResponseEntity<PaginationPageResponse<CustomerResponse>> list(@RequestParam(defaultValue = "10") int size,
                                                       @RequestParam(required = false) String pagingState) {

        LOGGER.info("Received request to list customers: size={}, pagingState={}", size, pagingState);
        Pageable pageable = PaginationComponent.buildPageable(size, pagingState);
        var customers = listCustomersUseCase.execute(pageable);
        List<CustomerResponse> content = customers.map(customerMapper::from).getContent();
        String nextPagingState = PaginationComponent.extractPagingState(customers);
        return ResponseEntity.ok(new PaginationPageResponse<CustomerResponse>(content, nextPagingState));
    }
}
