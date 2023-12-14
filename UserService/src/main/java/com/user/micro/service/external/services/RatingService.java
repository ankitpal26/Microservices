package com.user.micro.service.external.services;

import com.user.micro.service.entities.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "RATING-SERVICE")
@Service
public interface RatingService {

    @PostMapping("/ratings")
    public Rating createRating(Rating values);

    @PutMapping("/ratings/{ratingId}")
    public Rating updateRating(@PathVariable String ratingId, Rating rating);

    @DeleteMapping("ratings/{ratingId}")
    public void deleteRating(@PathVariable String ratingId);
}
