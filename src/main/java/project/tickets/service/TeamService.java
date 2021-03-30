package project.tickets.service;

import project.tickets.model.Team;
import project.tickets.model.exceptions.InvalidTeamIdException;

import java.util.List;
import java.util.Optional;

public interface TeamService {

    List<Team> findAll();
    Optional<Team> findTeamById(Long id) throws InvalidTeamIdException;
    Optional<Team>findTeamByName(String name);
    Optional<Team>findTeamByCity(String city);
    Team save(String name,String city);
    void deleteById(Long id);
    Team update(Long id,String name,String city);
}
