package com.grocery.app.controller;

import com.grocery.app.model.Order;
import com.grocery.app.service.GroceryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private GroceryService groceryService;

    @PostMapping
    public Order placeOrder(@RequestBody Order order, java.security.Principal principal) {
        order.setUsername(principal.getName());
        return groceryService.placeOrder(order);
    }

    @org.springframework.web.bind.annotation.GetMapping("/my")
    public java.util.List<Order> getMyOrders(java.security.Principal principal) {
        return groceryService.getOrdersByUsername(principal.getName());
    }

    @org.springframework.web.bind.annotation.GetMapping("/auth/me")
    public java.util.Map<String, String> getCurrentUser(java.security.Principal principal) {
        return java.util.Map.of("username", principal.getName());
    }

    @org.springframework.web.bind.annotation.GetMapping
    @org.springframework.security.access.prepost.PreAuthorize("hasRole('ADMIN')")
    public java.util.List<Order> getAllOrders() {
        return groceryService.getAllOrders();
    }
}
