package project.tickets.service.impl;

import org.springframework.stereotype.Service;
import project.tickets.model.League;
import project.tickets.model.Team;
import project.tickets.model.exceptions.InvalidTeamIdException;
import project.tickets.repository.TeamRepository;
import project.tickets.service.TeamService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
@Service
@Transactional
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;

    public TeamServiceImpl(TeamRepository teamRepository){
        this.teamRepository = teamRepository;
    }

    @Override
    public List<Team> findAll() {
        return teamRepository.findAll();
    }

    @Override
    public Optional<Team> findTeamById(Long id){
        return teamRepository.findById(id);
    }

    @Override
    public Optional<Team> findTeamByName(String name) {
        return teamRepository.findByName(name);
    }

    @Override
    public Optional<Team> findTeamByCity(String city) {
        return teamRepository.findByCity(city);

    }

    @Override
    public Team save(String name, String city) {
        return teamRepository.save(new Team(name,city));
    }

    @Override
    public void deleteById(Long id) {
        Team team = this.findTeamById(id).orElse(null);
        if(team==null)
            throw new IllegalArgumentException();
        teamRepository.delete(team);
    }

    @Override
    public Team update(Long id, String name, String city) {
        Team team = this.findTeamById(id).orElse(null);
            team.setName(name);
            team.setCity(city);
            return this.teamRepository.save(team);
    }
}
