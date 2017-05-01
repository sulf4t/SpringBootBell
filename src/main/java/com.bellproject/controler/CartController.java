package com.bellproject.controler;

import com.bellproject.entity.LineItem;
import com.bellproject.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.logging.Logger;

/**
 * Controller to return cart information for a given {@link com.bellproject.entity.User  and/or {@link com.bellproject.entity.Product}.
 */
@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService _cartService;

    @RequestMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<LineItem> getUserById(@PathVariable("id") int id)
    {
        return _cartService.getCartByUserId(id);
    }

    @RequestMapping(value = "/{id}//{productId}", method = RequestMethod.DELETE)
    public void removeProductFromCartBy(@PathVariable("id") int id, @PathVariable("productId") int productId)
    {
        _cartService.removeProductFromCartBy(id, productId);
    }

    @RequestMapping(value = "/{id}/{productId}", method = RequestMethod.PUT)
    public void addProductFromCartBy(@PathVariable("id") int id, @PathVariable("productId") int productId)
    {
        _cartService.addProductFromCartBy(id, productId);
    }
}
