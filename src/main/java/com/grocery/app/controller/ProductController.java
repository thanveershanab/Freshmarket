package com.grocery.app.controller;

import com.grocery.app.model.Product;
import com.grocery.app.service.GroceryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private GroceryService groceryService;

    @GetMapping
    public List<Product> getAllProducts() {
        return groceryService.getAllProducts();
    }
}
