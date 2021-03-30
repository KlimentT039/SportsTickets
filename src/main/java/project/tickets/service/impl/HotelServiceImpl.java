package project.tickets.service.impl;

import org.springframework.stereotype.Service;
import project.tickets.model.Hotel;
import project.tickets.repository.HotelRepository;
import project.tickets.service.HotelService;

import java.util.List;
@Service
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;

    public HotelServiceImpl(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @Override
    public List<Hotel> findAll() {
        return hotelRepository.findAll();
    }

    @Override
    public List<Hotel> findByCountryCity(String country, String city) {
        return hotelRepository.findAllByCountryAndCity(country, city);
    }

    @Override
    public Hotel save(String country, String city, Long stars, Long priceByNight) {
        if (country == null || city == null || country.isEmpty() || city.isEmpty()) {
            throw new IllegalArgumentException();
        }
        Hotel h = new Hotel(country,city,stars,priceByNight);
        hotelRepository.save(h);
        return h;
    }

    @Override
    public List<Hotel> findHotelsByCity(String city) {
        return this.hotelRepository.findByCity(city);
    }
}

