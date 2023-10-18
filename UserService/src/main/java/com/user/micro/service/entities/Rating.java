package com.user.micro.service.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Rating {

    private String ratingId;
    private String UserId;
    private String hotelId;
    private int rating;
    private String feedback;
}
