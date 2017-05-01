package com.bellproject.domain;

import com.bellproject.entity.User;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface UserRepository {
    User findUserFrom(int id);

    Collection<User> getAllUsers();
}

