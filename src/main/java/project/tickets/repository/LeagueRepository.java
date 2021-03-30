package project.tickets.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.tickets.model.League;

import java.util.Optional;

@Repository
public interface LeagueRepository extends JpaRepository<League,String> {
    League findByName(String name);
    Optional<League>findByCountry(String country);
    void deleteByName(String name);

}
