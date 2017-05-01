package com.bellproject.domain;

import com.bellproject.entity.Product;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * This class was an implementation for test purpose.
 */

//@Repository
public class FakeProductDaoImpl implements ProductDao {
    private static Map<Integer, Product> _products;

    static
    {
        _products = new HashMap<Integer, Product>();
        {
            _products.put(1, new Product(1, "MacBook Pro", "Line of a Macintosh portable computers", 2400.00));
            _products.put(2, new Product(2, "Sony Vaio", "Line of a Sony portable computers", 2000.00 ));
            _products.put(3, new Product(3, "HP Envy", "Line of a HP portable computers", 1500.00));
        }
    }


    @Override
    public Collection<Product> getAllProduct()
    {
        return _products.values();
    }

    @Override
    public Product findProductFrom(int id)
    {
        return _products.get(id);
    }

    @Override
    public void addProduct(Product product) {
        int size = getAllProduct().size();
        _products.put(size+1, product);
    }
}
