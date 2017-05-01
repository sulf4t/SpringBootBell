package com.bellproject.service;

import com.bellproject.controler.UserIdNotFoundException;
import com.bellproject.domain.UserDao;
import com.bellproject.domain.UserRepository;
import com.bellproject.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Controller service used for user.
 */

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Collection<User> getAllUsers()
    {
        return userRepository.getAllUsers();
    }

    public User getUserById(int id)
    {
        User user = this.userRepository.findUserFrom(id);
        if(user == null)
            throw new UserIdNotFoundException(id);
        return userRepository.findUserFrom(id);
    }
}
