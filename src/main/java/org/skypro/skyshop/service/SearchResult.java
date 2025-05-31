package org.skypro.skyshop.service;

import org.skypro.skyshop.model.search.Searchable;

import java.util.UUID;

public class SearchResult {
    private final String id;
    private final String name;
    private final String contentType;

    private SearchResult(String id, String name, String contentType) {
        this.id = id;
        this.name = name;
        this.contentType = contentType;
    }

    public static SearchResult fromSearchable(Searchable searchable) {
        return new SearchResult(UUID.randomUUID().toString(), searchable.getName(), searchable.getContentType());
    }

        public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContentType() {
        return contentType;
    }

}
