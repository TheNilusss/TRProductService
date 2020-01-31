package com.study.productService.controller;

import com.study.productService.entity.Product;
import com.study.productService.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class ProductServiceController {
    @Autowired
    private ProductRepository m_repository;

    @PostMapping("/Product")
    void createProduct(@RequestParam final String name, @RequestParam final double price, @RequestParam final String currency) {
        if (existProduct(name)) {
            System.out.println("Customer don't created.");
        } else {
            m_repository.save(new Product(name,price,currency));
            System.out.println("Customer created.");
        }
    }

    boolean existProduct(String name) {
        if (m_repository.findByName(name) == null) {
            System.out.println("Product exist");
            return false;
        }
        System.out.println("Product don't exist");
        return true;
    }

    @GetMapping("/product")
    Product getProduct(@RequestParam final String ID) {
        Product product = m_repository.findByid(ID);
        if (product != null) {
            System.out.println("send product by ID: " + product.name);
        } else {
            System.out.println("send no product");
        }
        return product;
    }

    //DEBUGGING
    @GetMapping("/product/db/show")
    ArrayList<Product> showDb() {
        ArrayList<Product> arrayListObj = new ArrayList<>();
        for (Product obj : m_repository.findAll()) {
            arrayListObj.add(obj);
        }
        return arrayListObj;
    }

    //DEBUGGING
    @PostMapping("/product/db/clear")
    void clear() {
        m_repository.deleteAll();
    }
}