package com.example.entrypoint.rest.controller;

import com.example.application.usecase.product.CreateProductUseCase;
import com.example.application.usecase.product.ListProductUseCase;
import com.example.application.usecase.product.SearchProductUseCase;
import com.example.application.usecase.product.UpdateProductUseCase;
import com.example.domain.model.Product;
import com.example.entrypoint.rest.mapper.ProductMapper;
import com.example.entrypoint.rest.request.ProductRequest;
import com.example.entrypoint.rest.response.PaginationPageResponse;
import com.example.entrypoint.rest.response.ProductResponse;
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

@Tag(name = "Produtos", description = "API de Produtos")
@RequiredArgsConstructor
@RestController
@RequestMapping("/product")
public class ProductController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    private final UpdateProductUseCase updateProductUseCase;
    private final CreateProductUseCase createProductUseCase;
    private final SearchProductUseCase searchProductUseCase;
    private final ListProductUseCase listProductUseCase;
    private final ProductMapper productMapper;

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> search(@PathVariable String id) {

        LOGGER.info("Received request to Search Customer by id: {}", id);
        var customer = searchProductUseCase.execute(id.trim());
        var customerResponse = productMapper.from(customer);
        return ResponseEntity.ok(customerResponse);
    }

    @PostMapping
    public ResponseEntity<ProductResponse> create(@RequestBody ProductRequest request) {

        LOGGER.info("Received request to create product: {}", request);
        Product product = productMapper.from(request);
        Product created = createProductUseCase.execute(product);
        ProductResponse response = productMapper.from(created);
        var createdUri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.getId())
                .toUri();
        return ResponseEntity.created(createdUri).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> update(@PathVariable String id,
                                                  @RequestBody ProductRequest request) {

        LOGGER.info("Received request to create product: {}", request);
        Product product = productMapper.from(request);
        Product created = updateProductUseCase.execute(id.trim(), product);
        ProductResponse response = productMapper.from(created);

        return ResponseEntity.ok().body(response);
    }

    @GetMapping
    public ResponseEntity<PaginationPageResponse<ProductResponse>> list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String pagingState) {

        LOGGER.info("Received request to list products: page={}, size={}, pagingState={}", page, size, pagingState);
        Pageable pageable = PaginationComponent.buildPageable(size, pagingState);
        var products = listProductUseCase.execute(pageable);
        List<ProductResponse> content = products.map(productMapper::from).getContent();
        String nextPagingState = PaginationComponent.extractPagingState(products);
        return ResponseEntity.ok(new PaginationPageResponse<>(content, nextPagingState));
    }
}
