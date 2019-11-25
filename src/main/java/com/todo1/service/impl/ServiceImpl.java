package com.todo1.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.todo1.model.Product;
import com.todo1.repository.ProductRepository;
import com.todo1.service.Todo1Service;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceImpl implements Todo1Service {

    @Autowired
    ProductRepository repository;

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = (List<Product>) repository.findAll();
        return products;
    }

    @Override
    public String addProduct(Map<String, Object> product) {
        Product newProduct = new Product();
        
        newProduct.setName(product.get("name").toString());
        newProduct.setCompany(product.get("company").toString());
        newProduct.setType(product.get("type").toString());
        newProduct.setPrice(Float.parseFloat(product.get("price").toString()));
        newProduct.setDescription(product.get("description").toString());
        newProduct.setQuantity(Integer.parseInt(product.get("quantity").toString()));
        repository.save(newProduct);
        return "Producto guardado";
    }

    @Override
    public String updateProduct(Map<String, Object> product) throws ServiceException {
        int id = Integer.parseInt(product.get("id").toString());
        Optional<Product> oldProduct = repository.findById(id);
        if (!oldProduct.isPresent())
            throw new ServiceException("PRODUCT_NOT_FOUND");
        oldProduct.get().setDescription(product.get("description").toString());
        oldProduct.get().setName(product.get("name").toString());
        oldProduct.get().setType(product.get("type").toString());
        oldProduct.get().setQuantity(Integer.parseInt(product.get("quantity").toString()));
        oldProduct.get().setPrice(Float.parseFloat(product.get("description").toString()));
        oldProduct.get().setCompany(product.get("company").toString());
        repository.save(oldProduct.get());
        return "Producto actualizado";
    }
}
