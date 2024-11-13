package com.store.shop.service;

import com.store.shop.entity.Product;
import com.store.shop.exceptions.ProductNotFound;
import com.store.shop.repo.ProductRepo;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.print.DocFlavor;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepo repo;
    @Transactional
    public Product saveProduct(Product product)
    {
        return repo.save(product);
    }
    public Product getProductById(Integer id)
    {
        return repo.findById(id).orElseThrow(()->new ProductNotFound("not found", "101"));
    }
    public List<Product> getProducts()
    {
       return repo.findAll();
    }
    public Product updateProduct(Integer id,Product product)
    {
       Product product1= repo.findById(id).orElse(null);

        if (product1 != null) {
            product1.setP_name(product.getP_name());
            product1.setCategory(product.getCategory());
            product1.setPrice(product.getPrice());
        }
        assert product1 != null;
        return repo.save(product1);
    }
    public String deleteProductById(Integer id)
    {
        repo.deleteById(id);
        return "product is deleted";
    }

}
