package com.bellproject.domain;

import com.bellproject.controler.ProductNotFoundException;
import com.bellproject.controler.UserIdNotFoundException;
import com.bellproject.entity.LineItem;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * This class is an implementation for test purpose.
 */
@Repository
public class FakeCartImpl implements CartDao {

    //
    private Map<Integer, Collection<LineItem>> _cart = new HashMap<Integer, Collection<LineItem>>();
    {
        Collection<LineItem> lineItems = new ArrayList<>();
        lineItems.add((new LineItem(1, 1, 2 )));
        _cart.put(1, lineItems);
    }

    @Override
    public Collection<LineItem> getCartByUserId(int  userId)
    {
        return _cart.get(userId);
    }

    @Override
    public void removeProductFromCartByUserId(int  userId, int productId)
    {
        if (_cart.containsKey(userId))
        {
            Collection<LineItem> lineItems = _cart.get(userId);
            LineItem lineItemToDelete = null;
            for (LineItem lineItem : lineItems)
            {
                if (lineItem.getProductId() == productId)
                {
                    if (lineItem.getQuantity() >1) {
                        lineItem.setQuantity(lineItem.getQuantity() -1);
                        return;
                    }
                    else
                        lineItemToDelete = lineItem;
                }
                else
                {
                    throw new ProductNotFoundException(productId);
                }
            }

            if(lineItemToDelete != null)
            {
                lineItems.remove(lineItemToDelete);
                _cart.put(userId, lineItems);
            }
            _cart.remove(userId);
        }
        else
        {
            throw new UserIdNotFoundException(userId);
        }


    }

    @Override
    public void addProductFromCartBy(int userId, int productId) {
        if (_cart.containsKey(userId))
        {
            Collection<LineItem> lineItems = _cart.get(userId);
            if (lineItems != null)
            {
                for (LineItem lineItem : lineItems)
                {
                    if (lineItem.getProductId() == productId)
                    {
                        lineItem.setQuantity(lineItem.getQuantity() +1);
                        _cart.put(userId, lineItems);
                        return;
                    }
                }
                LineItem lineItem = new LineItem(lineItems.size() +1, productId, 1);
                lineItems.add(lineItem);
                _cart.put(userId, lineItems);
            }
            else
            {

                _cart.put(userId, lineItems);
            }
        }
        else
        {
            _cart.put(userId, createEmptyLineItem(productId));
        }
    }

    private Collection<LineItem> createEmptyLineItem(int productId) {
        Collection<LineItem> lineItems = new ArrayList<>();
        LineItem lineItem = new LineItem(1, productId, 1);
        lineItems.add(lineItem);
        return lineItems;
    }
}
