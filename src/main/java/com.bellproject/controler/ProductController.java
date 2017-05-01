package com.bellproject.controler;

import com.bellproject.entity.Product;
import com.bellproject.service.ProductService;
import com.bellproject.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Controller to return product information}.
 */
@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService _productService;

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Product> getAllProduct()
    {
        return _productService.getAllProduct();
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Product getProductById(@PathVariable("id") int id)
    {
        return _productService.getProductById(id);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE )
    public void addProduct(@RequestBody Product product)
    {
        _productService.addProduct(product);
    }

}
