package com.user.micro.service.services.impl;

import com.user.micro.service.entities.Rating;
import com.user.micro.service.entities.User;
import com.user.micro.service.exceptions.ResourceNotFoundException;
import com.user.micro.service.repositories.UserRepository;
import com.user.micro.service.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private RestTemplate restTemplate;
    @Override
    public User saveUser(User user) {
        //generate unique userid
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {

        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given id is not found on server !!" + userId));

        //fetch rating of the above user from rating service
        Arrays forObject = restTemplate.getForObject("http://localhost:8083/ratings/users/b473c7da-e7b2-4570-97da-8d197c56da8f", Arrays.class);
        logger.info("{}",forObject);
        user.setRatings((List<Rating>) forObject);
        return  user;
    }

    @Override
    public User deleteUser(String userId) {
        return null;
    }

    @Override
    public User updateUser(User user, String userId) {
        return null;
    }
}
