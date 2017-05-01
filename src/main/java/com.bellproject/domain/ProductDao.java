package com.bellproject.domain;

import com.bellproject.entity.Product;

import java.util.Collection;

public interface ProductDao {
    Product findProductFrom(int id) ;

    Collection<Product> getAllProduct() ;

    void addProduct(Product product) ;
}
