package com.store.shop.exceptions;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductNotFound extends RuntimeException{

    private String error_Code;
    public ProductNotFound(String msg,String error_Code)
    {
        super(msg);
        this.error_Code=error_Code;
    }

}
