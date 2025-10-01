package com.example.application.usecase.sale;

import com.example.domain.exception.NotFoundBusinessException;
import com.example.domain.model.AddSaleItem;
import com.example.domain.model.Product;
import com.example.domain.model.Sale;
import com.example.domain.model.SaleItem;
import com.example.domain.repository.ProductRepository;
import com.example.domain.repository.SaleRepository;
import com.example.domain.service.SaleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

public class ConsumeSaleItemSaleUseCase {

    private static final String SALE_NOT_FOUND = "Venda não encontrado.";
    private static final String PRODUCT_NOT_FOUND = "Produto não encontrado.";

    private static final Logger LOGGER = LoggerFactory.getLogger(ConsumeSaleItemSaleUseCase.class);
    private final SaleRepository repository;
    private final ProductRepository productRepository;
    private final SaleService saleService;

    public ConsumeSaleItemSaleUseCase(SaleRepository repository,
                                      ProductRepository productRepository,
                                      SaleService saleService) {

        this.repository = repository;
        this.productRepository = productRepository;
        this.saleService = saleService;
    }

    @Transactional
    public AddSaleItem execute(AddSaleItem addSaleItem) {

        Sale sale = repository.findById(addSaleItem.getSaleId())
                .orElseThrow(() -> new NotFoundBusinessException(SALE_NOT_FOUND));

        Product product = productRepository.findById(addSaleItem.getProductId())
                .orElseThrow(() -> new NotFoundBusinessException(PRODUCT_NOT_FOUND));

        if (Objects.nonNull(sale.getItems())) {
            modifySaleItem(addSaleItem, sale, product);
        }

        LOGGER.info("ConsumeSaleItemSaleUseCase.execute - [{}]", addSaleItem);
        return addSaleItem;
    }

    private void modifySaleItem(AddSaleItem addSaleItem, Sale sale, Product product) {

        var productId = addSaleItem.getProductId();
        Optional<SaleItem> optSaleItem = sale.getItems().stream()
                .filter(item -> item.getProductId().equals(productId))
                .findFirst();

        if (optSaleItem.isPresent()) {
            var saleItem = optSaleItem.get();
            var totalValue = saleItem.getTotalValue() + (addSaleItem.getQuantity() * product.getValue());
            var quantity = saleItem.getQuantity() + addSaleItem.getQuantity();
            saleItem.setTotalValue(totalValue);
            saleItem.setQuantity(quantity);
        } else {

            ArrayList<SaleItem> saleItems = new ArrayList<>(sale.getItems());
            SaleItem saleItem = SaleItem.builder()
                    .productId(productId)
                    .quantity(addSaleItem.getQuantity())
                    .totalValue(product.getValue() * addSaleItem.getQuantity())
                    .build();

            saleItems.add(saleItem);
            sale.setItems(saleItems);
        }

        repository.save(sale);
    }
}
