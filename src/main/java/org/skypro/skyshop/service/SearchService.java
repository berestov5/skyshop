package org.skypro.skyshop.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class SearchService {
    private final StorageService storageService;

    public SearchService(StorageService storageService) {
        this.storageService = storageService;
    }

    public List<SearchResult> search (@RequestParam String text) {
        return storageService.getAll().stream()
                .filter(i -> i.getSearchTerm().contains(text))
                .map(SearchResult::fromSearchable)
                .toList();
    }
}
