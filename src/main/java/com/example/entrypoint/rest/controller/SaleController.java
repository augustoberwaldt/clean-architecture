package com.example.entrypoint.rest.controller;

import com.example.application.usecase.sale.AddItemSaleUseCase;
import com.example.application.usecase.sale.CreateSaleUseCase;
import com.example.application.usecase.sale.SearchSaleUseCase;
import com.example.domain.model.AddSaleItem;
import com.example.domain.model.Sale;
import com.example.entrypoint.rest.mapper.SaleMapper;
import com.example.entrypoint.rest.request.AddItemSaleRequest;
import com.example.entrypoint.rest.request.SaleRequest;
import com.example.entrypoint.rest.response.SaleResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Tag(name = "Vendas", description = "API de Vendas")
@RequiredArgsConstructor
@RestController
@RequestMapping("/sale")
public class SaleController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SaleController.class);

    private final CreateSaleUseCase createSaleUseCase;
    private final SearchSaleUseCase searchSaleUseCase;
    private final AddItemSaleUseCase addItemSaleUseCase;

    private final SaleMapper saleMapper;

    @GetMapping("/{id}")
    public ResponseEntity<SaleResponse> search(@PathVariable String id) {

        LOGGER.info("Received request to search sale by id: {}", id);
        Sale sale = searchSaleUseCase.execute(id);
        SaleResponse response = saleMapper.from(sale);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping
    public ResponseEntity<SaleResponse> create(@RequestBody SaleRequest request) {

        LOGGER.info("Received request to create product: {}", request);
        Sale sale = saleMapper.from(request);
        Sale created = createSaleUseCase.execute(sale);
        SaleResponse response = saleMapper.from(created);
        var createdUri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.getId())
                .toUri();
        return ResponseEntity.created(createdUri).body(response);
    }

    @PostMapping("/{id}/item")
    public ResponseEntity<Void> addSaleItem(@PathVariable("id") String id,
                                            @RequestBody AddItemSaleRequest request) {

        AddSaleItem addSaleItem = saleMapper.from(id, request);
        addItemSaleUseCase.execute(addSaleItem);

        return ResponseEntity.accepted().build();
    }
}