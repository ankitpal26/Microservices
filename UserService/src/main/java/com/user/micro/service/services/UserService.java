package com.user.micro.service.services;

import com.user.micro.service.entities.User;

import java.util.List;

public interface UserService {

    //User operation

    //create
    User saveUser(User user);

    //get all user
    List<User> getAllUser();

    //get single user
    User getUser(String userId);

    //delete
    User deleteUser(String userId);

    //update
    User updateUser(User user, String userId);

}
