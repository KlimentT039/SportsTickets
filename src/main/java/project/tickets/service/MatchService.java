package project.tickets.service;

import project.tickets.model.Match;
import project.tickets.model.Team;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface MatchService {
    List<Match>findAll();
    List<Match>findByHomeTeam(Long id);
    Match update(Long id,Team homeTeam, Team awayTeam, Date date, int attendance,float priceTickets);
    Match create(Team homeTeam, Team awayTeam, Date date, int attendance,float priceTickets);
    void delete(Long id);
    Optional<Match>findMatchById(Long id);
}
