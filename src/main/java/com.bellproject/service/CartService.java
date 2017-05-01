package com.bellproject.service;

import com.bellproject.controler.UserIdNotFoundException;
import com.bellproject.domain.*;
import com.bellproject.entity.LineItem;
import com.bellproject.entity.Product;
import com.bellproject.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Controller service used for Cart.
 */

@Service
public class CartService {

    @Autowired
    private CartDao _cartDao;

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private ProductDao productRepository;

    public Collection<LineItem> getCartByUserId(int id) throws UserIdNotFoundException
    {
        User user = this.userRepository.findUserFrom(id);
        if(user == null)
            throw new UserIdNotFoundException(id);
        return _cartDao.getCartByUserId(id);
    }

    public void removeProductFromCartBy(int id, int productId) {
        _cartDao.removeProductFromCartByUserId(id, productId);
    }


    public void addProductFromCartBy(int id, int productId) {
        User user = this.userRepository.findUserFrom(id);
        if(user == null)
            throw new UserIdNotFoundException(id);

        Product product = this.productRepository.findProductFrom(productId);
        if(product == null)
            throw new UserIdNotFoundException(productId);
        _cartDao.addProductFromCartBy(id, productId);
    }
}
