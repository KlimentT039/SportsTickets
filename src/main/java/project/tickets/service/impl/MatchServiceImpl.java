package project.tickets.service.impl;

import org.springframework.stereotype.Service;
import project.tickets.model.Match;
import project.tickets.model.Team;
import project.tickets.repository.MatchRepository;
import project.tickets.repository.TeamRepository;
import project.tickets.service.MatchService;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MatchServiceImpl implements MatchService {

    public final MatchRepository matchRepository;
    private final TeamRepository teamRepository;
    public MatchServiceImpl(MatchRepository matchRepository,TeamRepository teamRepository){
        this.matchRepository = matchRepository;
        this.teamRepository = teamRepository;
    }


    @Override
    public List<Match> findAll() {
        return matchRepository.findAll();
    }

    @Override
    public List<Match> findByHomeTeam(Long id) {
        Team t = teamRepository.findById(id).orElse(null);
        if(t==null){
            throw new IllegalArgumentException();
        }
        return matchRepository.findAllByHomeTeam(t);
    }

    @Override
    public Match update(Long id,Team homeTeam, Team awayTeam, Date date, int attendance, float priceTickets) {
        Match m = this.findMatchById(id).orElse(null);
        m.setHomeTeam(homeTeam);
        m.setAwayTeam(awayTeam);
        m.setDate(date);
        m.setAttendance(attendance);
        m.setPriceTickets(priceTickets);
        return this.matchRepository.save(m);
    }

    @Override
    public Match create(Team homeTeam, Team awayTeam, Date date, int attendance, float priceTickets) {
        if(homeTeam==null || awayTeam==null || date==null){
            throw new IllegalArgumentException();
        }
        Match m = new Match(homeTeam,awayTeam,date,attendance,priceTickets);
        matchRepository.save(m);
        return m;
    }

    @Override
    public void delete(Long id) {
        this.matchRepository.deleteById(id);
    }

    @Override
    public Optional<Match> findMatchById(Long id) {
        return this.matchRepository.findById(id);
    }
}
