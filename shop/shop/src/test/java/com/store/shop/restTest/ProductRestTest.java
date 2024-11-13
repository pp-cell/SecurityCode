package com.store.shop.restTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.store.shop.entity.Product;
import com.store.shop.rest.ProductRest;
import com.store.shop.service.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(value = ProductRest.class)
public class ProductRestTest {
    @InjectMocks
    private  MockMvc mvc;
    @MockBean
    private ProductService service;
    @Test
    public void addTest() throws Exception {
        Product product=new Product(101,"realm","mobile",20000.0);
        ObjectMapper mapper=new ObjectMapper();
       String dataJson= mapper.writeValueAsString(product);
        when(service.saveProduct(ArgumentMatchers.any())).thenReturn(product);
        MockHttpServletRequestBuilder requestBuilder= MockMvcRequestBuilders.post("/")
                .contentType(MediaType.APPLICATION_JSON).content(dataJson);
       ResultActions res= mvc.perform(requestBuilder);
     MvcResult result= res.andReturn();
        MockHttpServletResponse response=result.getResponse();
       int status= response.getStatus();
       assertEquals(201,status);
    }
    @Test
    public void getProduct() throws Exception {
        Product product=new Product(101,"realm","mobile",20000.0);

        when(service.getProductById(101)).thenReturn(product);
      MockHttpServletRequestBuilder requestBuilder=  MockMvcRequestBuilders.get("/get/101");
      ResultActions res=mvc.perform(requestBuilder).andExpect( jsonPath("$.p_name").value("realm"));
      MvcResult result=res.andReturn();
      MockHttpServletResponse response=result.getResponse();
      int status=response.getStatus();
      assertEquals(200,status);

    }
    @Test
    public void updateTest() throws Exception {
        Product product = new Product(101, "realm", "mobile", 20000.0);
        Product updateData = new Product(101, "iqo", "mobile", 25000.0);
        ObjectMapper mapper = new ObjectMapper();
        String dataJson = mapper.writeValueAsString(updateData);
        when(service.updateProduct(101, updateData)).thenReturn(updateData);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/updateProduct?id=101")
                .contentType(MediaType.APPLICATION_JSON).content(dataJson);
        ResultActions res = mvc.perform(requestBuilder).andExpect(status().isCreated())
                .andExpect(jsonPath("$.p_name").value("iqo"))
                .andExpect(jsonPath("$.category").value("mobile"))
                .andExpect(jsonPath("$.price").value(25000.0));
        MvcResult result=res.andReturn();
     MockHttpServletResponse response=result.getResponse();
     int status=response.getStatus();
     assertEquals(201,status);

    }
    }
