package com.bellproject.domain;

import com.bellproject.entity.Product;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.Collection;

@Repository
public class ProductDaoImpl implements ProductDao {

    @Override
    public Product findProductFrom(int id){
        try
        {
            return DBQueries.getInstance().findProductFrom(id);
        }
        catch (SQLException e)
        {
            throw new SqlException(e.getMessage());
        }
    }


    @Override
    public Collection<Product> getAllProduct()  {
        try
        {
        return DBQueries.getInstance().getAllProduct();
    }
        catch (SQLException e)
                {
                throw new SqlException(e.getMessage());
                }
    }

    @Override
    public void addProduct(Product product) {
        try
        {
            DBQueries.getInstance().addProduct(product);
        }
        catch (SQLException e)
        {
            throw new SqlException(e.getMessage());
        }
    }
}
