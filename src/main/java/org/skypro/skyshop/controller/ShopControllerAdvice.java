package org.skypro.skyshop.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class ShopControllerAdvice {

    @ExceptionHandler(NoSuchProductException.class)
    public ResponseEntity<ShopError> handleNoSuchProduct(NoSuchProductException e) {
        ShopError shopError = new ShopError("PRODUCT_NOT_FOUND","Product not found: " + e.getMessage());
        return ResponseEntity.status( HttpStatus.NOT_FOUND).contentType(MediaType.APPLICATION_JSON).body(shopError);
    }

}
