package com.store.shop.exceptions;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ProductExceptResponse {
    private int statusCode;
    private String msg;

}
