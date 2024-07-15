package com.revature.repo;

import com.revature.entity.User;

import java.util.ArrayList;
import java.util.List;

public class InMemoryUser implements UserDao{

    private List<User> users;

    public InMemoryUser(){

        users = new ArrayList<User>();
        users.add(new User("admin", "1234"));
    }

    @Override
    public User createUser(User newUserCred) {
        users.add(newUserCred);
        return newUserCred;
    }

    public List<User> getAllUsers(){
        return users;
    }
}
