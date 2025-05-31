package org.skypro.skyshop.model.product;

import java.util.UUID;

public class SimpleProduct extends Product {

    private int productPrice;

    public SimpleProduct(String nameProduct, int productPrice, UUID id) {
        super(nameProduct, id);
        if(productPrice <= 0) throw new IllegalArgumentException("Цена продукта \"" + nameProduct + "\" должна быть больше нуля!");
        this.productPrice = productPrice;
    }

    @Override
    public int getPrice() {
        return productPrice;
    }

    @Override
    public boolean isSpecial() {
        return false;
    }

    @Override
    public String toString() {
        return "<" + super.getName() + ">: " +
                "<" + getPrice() + ">";
    }
}

