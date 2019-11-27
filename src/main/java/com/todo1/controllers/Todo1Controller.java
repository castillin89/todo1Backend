package com.todo1.controllers;

import java.util.List;
import java.util.Map;

import com.todo1.model.Product;
import com.todo1.service.Todo1Service;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
@RequestMapping("todo1")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})

public class Todo1Controller {

    @Autowired
    Todo1Service todo1Service;

    @GetMapping("/getAll")
    public List<Product> getAllProducts(){
        return todo1Service.getAllProducts();
    }

    @PostMapping("/addProduct")
    public String addProduct(@RequestBody Map<String, Object> product){
        return todo1Service.addProduct(product);
    }

    @PostMapping("/updateProduct")
    //Este método será empleado para actualización y salida de productos. En el Front se debe hacer
    // la validación de inventario disponible, en Backend se valida que la cantidad a actualizar no sea
    // menor a cero (0).
    public String updateProduct(@RequestBody Map<String, Object> product) throws ServiceException   {
        return todo1Service.updateProduct(product);
    } 
}
