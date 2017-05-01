package com.bellproject.domain;

import com.bellproject.entity.User;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class UserDao implements UserRepository{
    private static Map<Integer, User> _users;

    static
    {
        _users = new HashMap<Integer, User>();
        {
            _users.put(1, new User(1, "Francis"));
            _users.put(2, new User(2, "Edouard"));
            _users.put(3, new User(3, "Divya"));
        }
    }

    public Collection<User> getAllUsers()
    {
        return _users.values();
    }

    @Override
    public User findUserFrom(int id)
    {
        return _users.get(id);
    }

}
