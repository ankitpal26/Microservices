package com.micro.service.hotel.services.impl;

import com.micro.service.hotel.entities.Hotel;
import com.micro.service.hotel.exceptions.ResourceNotFoundException;
import com.micro.service.hotel.repositories.HotelRepository;
import com.micro.service.hotel.services.HotelServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelServices {

    @Autowired
    private HotelRepository hotelRepository;
    @Override
    public Hotel create(Hotel hotel) {
        String hotelId = UUID.randomUUID().toString();
        hotel.setId(hotelId);
        return hotelRepository.save(hotel);
    }

    @Override
    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel getSingleHotel(String id) {
        return hotelRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("hotel with given id is not found" +id));
    }

    @Override
    public Hotel updateHotel(Hotel hotel, String id) {
        Hotel  hotel1= hotelRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Hotel with given id not updated because this is not found " + id));
        hotel1.setName(hotel.getName());
        hotel1.setAbout(hotel.getAbout());
        hotel1.setLocation(hotel.getLocation());
         return    hotelRepository.save(hotel1);

    }

    @Override
    public void deleteHotel(String id) {
        hotelRepository.deleteById(id);
    }
}
