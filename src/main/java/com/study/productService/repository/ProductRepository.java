package com.study.productService.repository;

import com.study.productService.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {

    public Product findByid(String name);
    public Product findByName(String name);
}