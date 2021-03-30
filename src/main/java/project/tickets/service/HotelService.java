package project.tickets.service;

import project.tickets.model.Hotel;

import java.util.List;

public interface HotelService {
    List<Hotel>findAll();
    List<Hotel>findByCountryCity(String country,String city);
    Hotel save( String country, String city, Long stars, Long priceByNight);
    List<Hotel>findHotelsByCity(String city);
}
