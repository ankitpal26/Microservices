package com.user.micro.service.external.services;
import com.user.micro.service.entities.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "HOTEL-SERVICE")
public interface HotelService {

    @GetMapping("hotels/{hotelId}")
    Hotel getHotel(@PathVariable String hotelId);

    @PutMapping("hotels/{hotelId}")
    Hotel updateHotel(@RequestBody Hotel hotel);

    @PostMapping("/hotels")
    Hotel addNewHotel(@RequestBody Hotel hotel);
}
