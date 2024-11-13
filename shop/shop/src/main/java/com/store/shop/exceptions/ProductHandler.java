package com.store.shop.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProductHandler {
    @ExceptionHandler(value =ProductNotFound.class)
    public ResponseEntity<ProductExceptResponse> response(ProductNotFound found)
    {
        ProductExceptResponse productExceptResponse=new ProductExceptResponse();
        productExceptResponse.setStatusCode(Integer.parseInt(found.getError_Code()));
        productExceptResponse.setMsg(found.getMessage());
     return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(productExceptResponse);
    }
}
