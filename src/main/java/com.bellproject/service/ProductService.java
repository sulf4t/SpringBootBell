package com.bellproject.service;

import com.bellproject.entity.Product;

import java.util.Collection;

/**
 * Controller service used for Products.
 */

public interface ProductService {
    public Collection<Product> getAllProduct();

    public Product getProductById(int id);

    public void addProduct(Product product);
}
