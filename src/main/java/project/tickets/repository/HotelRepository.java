package project.tickets.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.tickets.model.Hotel;

import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository<Hotel,Long> {
    List<Hotel> findAllByCountryAndCity(String country,String city);
    List<Hotel>findByCity(String city);
}
