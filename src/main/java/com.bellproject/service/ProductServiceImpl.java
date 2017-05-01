package com.bellproject.service;

import com.bellproject.controler.ProductNotFoundException;
import com.bellproject.domain.ProductDao;
import com.bellproject.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Created by sulf on 4/27/2017.
 */

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductDao _productRepository;

    public Collection<Product> getAllProduct()
    {
        return _productRepository.getAllProduct();
    }

    public Product getProductById(int id)
    {
        Product product = _productRepository.findProductFrom(id);
        if (product == null)
            throw new ProductNotFoundException(id);
        return product;
    }

    public void addProduct(Product product) {
        _productRepository.addProduct(product);
    }
}
