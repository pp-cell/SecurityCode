package com.store.shop.serviceTest;

import com.store.shop.entity.Product;
import com.store.shop.repo.ProductRepo;
import com.store.shop.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.util.AssertionErrors;


import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.when;


@ExtendWith(value = {MockitoExtension.class})
public class ProductServiceTest {
    @Mock
    private ProductRepo  repo;
    @InjectMocks
    private ProductService service;

    private Product product1;
    private  Product product2;
     @BeforeEach
    public void init()
    {
      product1=new Product(101,"relame","mobile",20000.0);
      product2=new Product(102,"iqoo","mobile",25000.0);
    }
    @DisplayName("create data")
    @Test
    public void  addTest()
    {

        given(repo.save(any(Product.class))).willReturn(product1);
        Product product=service.saveProduct(product1);
        assertThat(product).isNotNull();
        assertThat(product.getP_name()).isEqualTo("relame");
        ArgumentCaptor<Product> captor=ArgumentCaptor.forClass(Product.class);
        then(repo).should().save(captor.capture());
        assertThat(captor.getValue().getP_name()).isEqualTo("relame");
    }
    @Test
    @DisplayName("gddggg")
    public  void getAllTest()
    {
        given(repo.findAll()).willReturn(Arrays.asList(product1,product2));
        List<Product> product=  service.getProducts();

        assertThat(product).isNotEmpty().hasSize(2).contains();
    }



}
