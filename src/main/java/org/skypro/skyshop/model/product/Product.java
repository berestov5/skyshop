package org.skypro.skyshop.model.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.model.search.Searchable;

import java.util.Objects;
import java.util.UUID;

public abstract class Product implements Searchable {
    private String nameProduct;
    private UUID id;

    public Product(String nameProduct, UUID id) {
        if(nameProduct == null || nameProduct.isBlank()) throw new IllegalArgumentException("Наименование продукта не может быть null " +
                "или пустым, введите корректное наименование!");
        this.nameProduct = nameProduct;
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(nameProduct, product.nameProduct);
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nameProduct);
    }

    @JsonIgnore
    @Override
    public String getSearchTerm() {
        return nameProduct;
    }

    @JsonIgnore
    @Override
    public String getContentType() {
        return "PRODUCT";
    }

    @Override
    public String getName() {
        return nameProduct;
    }

    public abstract int getPrice();

    public boolean isSpecial() {
        return false;
    }

    @Override
    public String toString() {
        return "имя продукта: " + nameProduct;
    }
}
