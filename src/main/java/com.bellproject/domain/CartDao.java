package com.bellproject.domain;

import com.bellproject.entity.LineItem;

import java.util.Collection;


public interface CartDao {
    Collection<LineItem> getCartByUserId(int id);

    void removeProductFromCartByUserId(int userId, int productId);

    void addProductFromCartBy(int userId, int productId);
}
