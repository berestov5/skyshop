package org.skypro.skyshop.model.product;

import java.util.UUID;

public class DiscountedProduct extends Product {
    private int basePrice;
    private int discountInPercent;

    public DiscountedProduct(String nameProduct, int basePrice, int discountInPercent, UUID id) {
        super(nameProduct, id);
        if (basePrice <= 0) throw new IllegalArgumentException("Цена продукта \"" + nameProduct + "\" должна быть больше нуля!");
        this.basePrice = basePrice;
        if(discountInPercent < 0 || discountInPercent > 100) throw new IllegalArgumentException("Процент должен быть в диапазоне от 0 до 100 включительно");
        this.discountInPercent = discountInPercent;
    }

    @Override
    public int getPrice() {
        return basePrice - (basePrice * discountInPercent) / 100;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public String toString() {
        return "<" + super.getName() + ">: " +
                "<" + getPrice() + ">" +
                "(" + discountInPercent + "%)";
    }
}


