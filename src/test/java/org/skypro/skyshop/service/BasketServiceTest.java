package org.skypro.skyshop.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BasketServiceTest {

    @Mock
    private ProductBasket productBasket;

    @Mock
    private StorageService storageService;

    @InjectMocks
    private BasketService basketService;

    private final UUID testProductId = UUID.randomUUID();
    private final Product testProduct = new SimpleProduct("Test Product", 100, testProductId);

    // 1. Добавление несуществующего товара в корзину приводит к выбросу исключения
    @Test
    void whenProductNotExistsAddProduct_thenThrowException() {

        // Given
        when(storageService.getProductById(testProductId)).thenReturn(Optional.empty());

        // Then
        assertThrows(IllegalArgumentException.class, () -> basketService.addProduct(testProductId));
        verify(storageService, times(1)).getProductById(testProductId);
    }

    // 2. Добавление существующего товара вызывает метод addProduct у ProductBasket
    @Test
    void whenProductExists_thenAddProduct() {
        // Given
        when(storageService.getProductById(testProductId)).thenReturn(Optional.of(testProduct));

        // When
        basketService.addProduct(testProductId);

        // Then
        verify(storageService, times(1)).getProductById(testProductId);
        verify(productBasket, times(1)).addProduct(testProductId);
    }

    // 3. Метод getUserBasket возвращает пустую корзину, если ProductBasket пуст
    @Test
    void whenProductBasketIsEmpty_thenGetUserBasketReturnEmptyBasket() {
        // Given
        when(productBasket.getProductBasket()).thenReturn(Map.of());

        // When
        UserBasket result = basketService.getUserBasket();

        // Then
        assertTrue(result.getItems().isEmpty());
        verify(productBasket, times(1)).getProductBasket();
    }

    // 4. Метод getUserBasket возвращает подходящую корзину, если в ProductBasket есть товары
    @Test
    void whenBasketHasProducts_thenGetUserBasketReturnAppropriateBasket() {
        // Given
        int productCount = 2;
        when(productBasket.getProductBasket()).thenReturn(Map.of(testProductId, productCount));
        when(storageService.getProductById(testProductId)).thenReturn(Optional.of(testProduct));

        // When
        UserBasket result = basketService.getUserBasket();

        // Then
        assertEquals(testProduct, result.getItems().get(0).getProduct());
        verify(productBasket).getProductBasket();
        verify(storageService).getProductById(testProductId);
    }

}
