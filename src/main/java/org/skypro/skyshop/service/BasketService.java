package org.skypro.skyshop.service;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BasketService {
    private final ProductBasket productBasket;
    private final StorageService storageService;

    public BasketService(ProductBasket basket, StorageService storageService) {
        this.productBasket = basket;
        this.storageService = storageService;
    }

    public void addProduct(UUID id) {
        if (!storageService.getProductById(id).isPresent()) {
            throw new IllegalArgumentException("Не найден продукт");
        }
        productBasket.addProduct(id);
    }

    public UserBasket getUserBasket() {
        List<BasketItem> result = productBasket.getProductBasket()
                .entrySet()
                .stream()
                .map(i -> new BasketItem(storageService.getProductById(i.getKey()).orElseThrow(), i.getValue()))
                .toList();
        return new UserBasket(result);
    }
}
