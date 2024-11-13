package com.store.shop.rest;

import com.store.shop.entity.Product;
import com.store.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/product")
public class ProductRest {
    @Autowired
    private ProductService service;
    @PostMapping(value = "/",consumes = {"application/json"})

    public ResponseEntity<Product> addProduct(@RequestBody Product product)
    {
       Product pro= service.saveProduct(product);
       return ResponseEntity.status(HttpStatus.CREATED).body(pro);
    }
    @GetMapping("/{id}")
    public  ResponseEntity<Product> getProduct(@PathVariable Integer id)
    {
       Product pro= service.getProductById(id);
       return ResponseEntity.status(HttpStatus.OK).body(pro);
    }
    @GetMapping("/")
    public ResponseEntity<List<Product>> getProducts()
    {
       List<Product> data= service.getProducts();
       return ResponseEntity.status(HttpStatus.OK).body(data);
    }
    @PutMapping("/")
    public ResponseEntity<Product> update(@RequestParam("id") Integer id,@RequestBody Product product )
    {
       Product pro= service.updateProduct(id,product);
       return  ResponseEntity.status(HttpStatus.CREATED).body(pro);
    }
    @DeleteMapping("/")
    public ResponseEntity<String> delete(@RequestParam("id") Integer id)
    {
       String msg= service.deleteProductById(id);
       return ResponseEntity.status(HttpStatus.OK).body(msg);
    }
}
