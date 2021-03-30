package project.tickets.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.tickets.model.Team;

import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<Team,Long> {
    Optional<Team> findByName(String name);
    Optional<Team> findByCity(String city);
    Optional<Team> findById(Long id);
}

