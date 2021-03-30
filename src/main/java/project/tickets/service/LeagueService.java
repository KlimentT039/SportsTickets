package project.tickets.service;

import project.tickets.model.League;
import project.tickets.model.Team;

import java.util.List;
import java.util.Optional;

public interface LeagueService {
    List<League> findAll();
    List<Team> findTeamsByLeague(String name);
    League findByName(String name);
    Optional<League>findByCountry(String country);
    League create(String country,String name);
    void deleteByName(String name);
    League update(String country, String name);
    void addTeamInLeague(String name,Long teamId);
}
