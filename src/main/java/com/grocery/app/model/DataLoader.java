package com.grocery.app.model;

import com.grocery.app.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader {

        @Bean
        CommandLineRunner initDatabase(ProductRepository repository) {
                return args -> {
                        // Fruits
                        createProduct(repository, "Fresh Apples", 180.00, "Fruits",
                                        "https://images.unsplash.com/photo-1560806887-1e4cd0b6cbd6?ixlib=rb-1.2.1&auto=format&fit=crop&w=400&q=80",
                                        "Crisp and sweet red apples.");
                        createProduct(repository, "Organic Bananas", 60.00, "Fruits",
                                        "/images/banana.png",
                                        "High quality organic bananas.");
                        createProduct(repository, "Juicy Oranges", 120.00, "Fruits",
                                        "/images/oranges.png",
                                        "Vitamin C packed oranges.");

                        // Vegetables
                        createProduct(repository, "Fresh Carrots", 80.00, "Vegetables",
                                        "https://images.unsplash.com/photo-1598170845058-32b9d6a5da37?ixlib=rb-1.2.1&auto=format&fit=crop&w=400&q=80",
                                        "Crunchy organic carrots.");
                        createProduct(repository, "Broccoli Heads", 90.00, "Vegetables",
                                        "/images/broccoli.png",
                                        "Fresh green broccoli.");
                        createProduct(repository, "Bell Peppers", 60.00, "Vegetables",
                                        "https://images.unsplash.com/photo-1563565375-f3fdfdbefa83?ixlib=rb-1.2.1&auto=format&fit=crop&w=400&q=80",
                                        "Colorful fresh bell peppers.");

                        // Bakery
                        createProduct(repository, "Whole Wheat Bread", 55.00, "Bakery",
                                        "https://images.unsplash.com/photo-1509440159596-0249088772ff?ixlib=rb-1.2.1&auto=format&fit=crop&w=400&q=80",
                                        "Freshly baked whole wheat bread.");
                        createProduct(repository, "Croissants (4 pack)", 220.00, "Bakery",
                                        "/images/croissants.png",
                                        "Buttery french croissants.");
                        createProduct(repository, "Chocolate Cookies", 150.00, "Bakery",
                                        "/images/cookies.png",
                                        "Soft baked chocolate chip cookies.");

                        // Dairy
                        createProduct(repository, "Milk (1 Gallon)", 260.00, "Dairy",
                                        "https://images.unsplash.com/photo-1563636619-e9143da7973b?ixlib=rb-1.2.1&auto=format&fit=crop&w=400&q=80",
                                        "Fresh whole milk.");
                        createProduct(repository, "Eggs (Dozen)", 140.00, "Dairy",
                                        "https://images.unsplash.com/photo-1587486913049-53fc88980cfc?ixlib=rb-1.2.1&auto=format&fit=crop&w=400&q=80",
                                        "Farm fresh eggs.");
                        createProduct(repository, "Greek Yogurt", 180.00, "Dairy",
                                        "/images/yogurt.png",
                                        "Creamy plain Greek yogurt.");
                        createProduct(repository, "Cheddar Cheese", 350.00, "Dairy",
                                        "https://images.unsplash.com/photo-1618164436241-4473940d1f5c?ixlib=rb-1.2.1&auto=format&fit=crop&w=400&q=80",
                                        "Sharp cheddar cheese block.");

                        // Meat
                        createProduct(repository, "Chicken Breast", 450.00, "Meat",
                                        "https://images.unsplash.com/photo-1604503468506-a8da13d82791?ixlib=rb-1.2.1&auto=format&fit=crop&w=400&q=80",
                                        "Boneless skinless chicken breast.");
                        createProduct(repository, "Ground Beef (1lb)", 480.00, "Meat",
                                        "https://images.unsplash.com/photo-1529692236671-f1f6cf9683ba?ixlib=rb-1.2.1&auto=format&fit=crop&w=400&q=80",
                                        "Lean ground beef for burgers.");
                        createProduct(repository, "Salmon Fillet", 950.00, "Meat",
                                        "https://images.unsplash.com/photo-1599084993091-1cb5c0721cc6?ixlib=rb-1.2.1&auto=format&fit=crop&w=400&q=80",
                                        "Fresh Atlantic salmon fillet.");

                        // Pantry & Snacks
                        createProduct(repository, "Olive Oil", 850.00, "Pantry",
                                        "/images/olive_oil.png",
                                        "Extra virgin olive oil.");
                        createProduct(repository, "Itallian Pasta", 180.00, "Pantry",
                                        "/images/pasta.png",
                                        "Authentic Italian spaghetti.");
                        createProduct(repository, "Potato Chips", 60.00, "Snacks",
                                        "https://images.unsplash.com/photo-1566478989037-eec170784d0b?ixlib=rb-1.2.1&auto=format&fit=crop&w=400&q=80",
                                        "Crispy sea salt potato chips.");

                        // Beverages
                        createProduct(repository, "Orange Juice", 180.00, "Beverages",
                                        "https://images.unsplash.com/photo-1621506289937-a8e4df240d0b?ixlib=rb-1.2.1&auto=format&fit=crop&w=400&q=80",
                                        "Freshly squeezed orange juice.");
                        createProduct(repository, "Coffee Beans", 650.00, "Beverages",
                                        "/images/coffee.png",
                                        "Premium roasted coffee beans.");
                        createProduct(repository, "Green Tea", 250.00, "Beverages",
                                        "https://images.unsplash.com/photo-1627435601361-ec25f5b1d0e5?ixlib=rb-1.2.1&auto=format&fit=crop&w=400&q=80",
                                        "Organic green tea bags.");
                };
        }

        private void createProduct(ProductRepository repository, String name, Double price, String category,
                        String imageUrl, String description) {
                Product product = new Product();
                product.setName(name);
                product.setPrice(price);
                product.setCategory(category);
                product.setImageUrl(imageUrl);
                product.setDescription(description);
                repository.save(product);
        }
}
