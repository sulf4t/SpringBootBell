package com.bellproject.controler;

import com.bellproject.entity.User;
import com.bellproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * Controller to return user information.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService _userService;

    @RequestMapping(method = RequestMethod.GET)
    public Collection<User> getAllUsers()
    {
        return _userService.getAllUsers();
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public User getUserById(@PathVariable("id") int id)
    {
        return _userService.getUserById(id);
    }
}
