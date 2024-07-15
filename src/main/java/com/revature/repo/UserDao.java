package com.revature.repo;

import com.revature.entity.User;

import java.util.List;

public interface UserDao {

    User createUser(User newUserCred);
    List<User> getAllUsers();
}
