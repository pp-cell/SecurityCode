package com.store.shop.repo;

import com.store.shop.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepo extends JpaRepository<Product,Integer> {
    

}
