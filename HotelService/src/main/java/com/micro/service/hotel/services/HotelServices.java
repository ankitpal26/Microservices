package com.micro.service.hotel.services;

import com.micro.service.hotel.entities.Hotel;

import java.util.List;

public interface HotelServices {
    Hotel create(Hotel hotel );

    List<Hotel> getAllHotels();

    Hotel getSingleHotel(String id );


    Hotel updateHotel(Hotel hotel, String id);

     void deleteHotel(String id);


}
