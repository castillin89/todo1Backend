package com.todo1.service;

import java.util.List;
import java.util.Map;

import com.todo1.model.Product;

import org.hibernate.service.spi.ServiceException;

public interface Todo1Service {

    public List<Product> getAllProducts();
    public String addProduct(Map<String, Object> product);
    public String updateProduct(Map<String, Object> product) throws ServiceException;
}
