package org.skypro.skyshop.model.article;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.model.search.Searchable;

import java.util.Objects;
import java.util.UUID;

public class Article implements Searchable {
    private String articleTitle;
    private String textOfTheArticle;
    private final UUID ID;

    public Article(String articleTitle, String textOfTheArticle, UUID id) {
        this.articleTitle = articleTitle;
        this.textOfTheArticle = textOfTheArticle;
        this.ID = id;
    }

    @Override
    public UUID getId() {
        return this.ID;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return Objects.equals(articleTitle, article.articleTitle);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(articleTitle);
    }

    @Override
    public String toString() {
        return articleTitle + " " + textOfTheArticle;
    }

    @JsonIgnore
    @Override
    public String getSearchTerm() {
        return toString();
    }

    @JsonIgnore
    @Override
    public String getContentType() {
        return "ARTICLE";
    }

    @Override
    public String getName() {
        return articleTitle;
    }



}

