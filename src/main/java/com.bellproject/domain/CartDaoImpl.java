package com.bellproject.domain;

import com.bellproject.entity.LineItem;

import java.sql.SQLException;
import java.util.Collection;

/**
 * This class should be  the production class. I did not finish to implement all methods of the DBQueries class.
 * Once it's done, uncomment //@Repository
 */
//@Repository
public class CartDaoImpl implements CartDao {
    @Override
    public Collection<LineItem> getCartByUserId(int id) {
        try
        {
            return DBQueries.getInstance().getCartByUserId(id);
        }
        catch (SQLException e)
        {
            throw new SqlException(e.getMessage());
        }
    }

    @Override
    public void removeProductFromCartByUserId(int userId, int productId) {
        try
        {
            DBQueries.getInstance().removeProductFromCartByUserId(userId, productId );
        }
        catch (SQLException e)
        {
            throw new SqlException(e.getMessage());
        }
    }

    @Override
    public void addProductFromCartBy(int userId, int productId) {
        try
        {
            DBQueries.getInstance().addProductFromCartBy(userId, productId);
        }
        catch (SQLException e)
        {
            throw new SqlException(e.getMessage());
        }
    }
}
