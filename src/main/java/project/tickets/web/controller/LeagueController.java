package project.tickets.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.tickets.model.League;
import project.tickets.model.Team;
import project.tickets.service.LeagueService;
import project.tickets.service.TeamService;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.List;

@Controller
@Transactional
public class LeagueController {

    private String nameTeam="";
    private String leagueName="";
    private final LeagueService leagueService;
    private final TeamService teamService;
    public LeagueController(LeagueService leagueService,TeamService teamService){
        this.leagueService = leagueService;
        this.teamService = teamService;
    }

    @GetMapping({"/","leagues"})
    public String showLeague(Model model){
        List<League> leagues = this.leagueService.findAll();
        model.addAttribute("leagues",leagues);
        return "leagues.html";
    }

    @PostMapping("/leagues/{id}")
    public String selectLeague(@PathVariable("id") String name){
        this.leagueName= name;
        return "redirect:/teams";
    }

    @GetMapping("/teams")
    public String listTeams(Model model){
        List<Team>teamList = this.leagueService.findTeamsByLeague(leagueName);
        model.addAttribute("teams",teamList);
        return "teams.html";
    }

    @GetMapping("/leagues/{id}/edit")
    public String showEdit(@PathVariable("id") String name,Model model){
        League league = this.leagueService.findByName(name);
        List<Team>teamList = teamService.findAll();
        model.addAttribute("league",league);
        model.addAttribute("teams",teamList);
        return "formLeague.html";
    }
    @GetMapping("/leagues/add")
    public String showAdd(Model model){
        return "formLeague.html";
    }

    @PostMapping("/leagues")
    public String create(String country,String name){
        this.leagueService.create(country, name);
        return "redirect:/leagues";
    }


    @PostMapping("/leagues/{id}/delete")
    public String delete(@PathVariable("id")String name){
        this.leagueService.deleteByName(name);
        return "redirect:/leagues";
    }

    public String getNameTeam(){
        return nameTeam;
    }

    @GetMapping("/leagues/teams")
    public String addTeam(Model model){
        List<League>leagues = leagueService.findAll();
        model.addAttribute("leagues",leagues);
        List<Team>teamList = teamService.findAll();
        model.addAttribute("teams",teamList);
        return "listTeams.html";
    }

    @PostMapping("/leagues/addTeams")
    public String teamInLeague(HttpServletRequest request,@RequestParam Long team,@RequestParam String league){
        nameTeam = league;
        leagueService.addTeamInLeague(nameTeam,team);
        return "redirect:/leagues";
    }
    

}
