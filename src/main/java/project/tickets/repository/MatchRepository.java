package project.tickets.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.tickets.model.Match;
import project.tickets.model.Team;

import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<Match,Long> {

    List<Match>findAllByHomeTeam(Team team);

}
