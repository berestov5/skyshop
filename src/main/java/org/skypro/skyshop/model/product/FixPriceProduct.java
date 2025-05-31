package org.skypro.skyshop.model.product;

import java.util.UUID;

public class FixPriceProduct extends Product {

    private static final int FIX_PRICE = 30;

    public FixPriceProduct(String nameProduct, UUID id) {
        super(nameProduct, id);
    }

    @Override
    public int getPrice() {
        return FIX_PRICE;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public String toString() {
        return "<" + super.getName() + ">:" +
                " Фиксированная цена" +
                "<" + getPrice() + ">";
    }
}

