package org.skypro.skyshop.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.SearchResult;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SearchServiceTest {
    @Mock
    StorageService storageService;

    @InjectMocks
    SearchService searchService;

    @Test
    void whenSearchSuccessful_thenReturnMatchingResults() {
        // Given
        Product product1 = new SimpleProduct("Хлеб", 50, UUID.randomUUID());
        Product product2 = new SimpleProduct("Молоко", 70, UUID.randomUUID());
        Article article = new Article("Хлеб всему голова", "Описание", UUID.randomUUID());

        when(storageService.getAll()).thenReturn(List.of(product1, product2, article));

        // When
        List<SearchResult> results = searchService.search("Хлеб");

        // Then
        assertEquals(2, results.size());
        assertTrue(results.get(0).getName().startsWith("Хлеб"));
        assertTrue(results.get(1).getName().startsWith("Хлеб"));
        verify(storageService, times(1)).getAll();
    }

    @Test
    void whenSearchNotSuccessful_thenReturnIsEmptyList() {
        // Given
        Product product1 = new SimpleProduct("Хлеб", 50, UUID.randomUUID());
        Product product2 = new SimpleProduct("Молоко", 70, UUID.randomUUID());
        Article article = new Article("Хлеб всему голова", "Описание", UUID.randomUUID());

        when(storageService.getAll()).thenReturn(List.of(product1, product2, article));

        // When
        List<SearchResult> results = searchService.search("Непонятки");

        // Then
        assertTrue(results.isEmpty());
        verify(storageService, times(1)).getAll();
    }

    @Test
    void whenSearchInEmptyList_thenReturnIsEmptyList() {
        // Given
        String text = "Хлеб";
        when(storageService.getAll()).thenReturn(List.of());

        // When
        List<SearchResult> results = searchService.search(text);

        // Then
        assertTrue(results.isEmpty());
        verify(storageService, times(1)).getAll();
    }

}
