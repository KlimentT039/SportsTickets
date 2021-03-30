package project.tickets.service.impl;

import org.springframework.stereotype.Service;
import project.tickets.model.League;
import project.tickets.model.Team;
import project.tickets.repository.LeagueRepository;
import project.tickets.repository.TeamRepository;
import project.tickets.service.LeagueService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
@Service
@Transactional
public class LeagueServiceImpl implements LeagueService {

    private final LeagueRepository leagueRepository;
    private final TeamRepository teamRepository;
    public LeagueServiceImpl(LeagueRepository leagueRepository,TeamRepository teamRepository){
        this.leagueRepository = leagueRepository;
        this.teamRepository = teamRepository;
    }

    @Override
    public List<League> findAll() {
        return leagueRepository.findAll();
    }

    @Override
    public List<Team> findTeamsByLeague(String name) {
        League league = this.findByName(name);
        if(league==null){
            throw new IllegalArgumentException();
        }
        else
            return league.getTeams();
    }

    @Override
    public League findByName(String name) {
        return leagueRepository.findByName(name);
    }

    @Override
    public Optional<League> findByCountry(String country) {
        return leagueRepository.findByCountry(country);
    }

    @Override
    public League create(String country, String name) {
        if(country==null || name==null || country.isEmpty() || name.isEmpty()){
            throw new IllegalArgumentException();
        }
        League l = new League(country,name);
        leagueRepository.save(l);
        return l;
    }



    @Override
    public void deleteByName(String name) {
        if(name.isEmpty() || name==null)
            throw new IllegalArgumentException();

        leagueRepository.deleteByName(name);
    }

    @Override
    public League update(String country, String name) {
        League league = this.findByName(name);
        if(league==null)
            throw new IllegalArgumentException();
        league.setCountry(country);


        return this.leagueRepository.save(league);
    }

    @Override
    public void addTeamInLeague(String name, Long teamId) {
        League league = this.findByName(name);
        if(league != null){
            Team team = teamRepository.findById(teamId).orElse(null);
            if(team!=null){
                league.getTeams().add(team);
            }
        }
    }


}
