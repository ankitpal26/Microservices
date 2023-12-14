package com.user.micro.service.services.impl;

import com.user.micro.service.entities.Hotel;
import com.user.micro.service.entities.Rating;
import com.user.micro.service.entities.User;
import com.user.micro.service.exceptions.ResourceNotFoundException;
import com.user.micro.service.external.services.HotelService;
import com.user.micro.service.repositories.UserRepository;
import com.user.micro.service.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HotelService hotelService;
    @Autowired
    private RestTemplate restTemplate;

    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
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
        //http://localhost:8083/ratings/users/45929c68-6ed9-48e1-8a58-76d95d29cf3e
        Rating[] ratingsOfUsers = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/"+user.getUserId(), Rating[].class);
        logger.info("{} ",ratingsOfUsers);
        List<Rating> ratings = Arrays.stream(ratingsOfUsers).toList();
        List<Rating> ratingList = ratings.stream().map(rating -> {
            //api call for hotel service to get hotel details
        //http://localhost:8082/hotels/a52eb0df-a458-4e3b-bf6d-38900167332e
//            Hotel hotel = restTemplate.getForObject("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(), Hotel.class);
//            logger.info("{}",hotel);
            Hotel hotel = hotelService.getHotel(rating.getHotelId());
            //set the hotel detail for rating
            rating.setHotel(hotel);
       //return the rating
            return  rating;
        }).collect(Collectors.toList());
        user.setRatings(ratingList);
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
