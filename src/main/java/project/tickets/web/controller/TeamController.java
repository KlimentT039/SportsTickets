package project.tickets.web.controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.tickets.model.Hotel;
import project.tickets.model.Match;
import project.tickets.model.Team;
import project.tickets.service.HotelService;
import project.tickets.service.MatchService;
import project.tickets.service.TeamService;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Controller
@Transactional
public class TeamController {
   private Long teamId;
   private Long matchId;

    private final TeamService teamService;
    private final MatchService matchService;
    private final HotelService hotelService;
    public TeamController(TeamService teamService,MatchService matchService,HotelService hotelService){
        this.teamService = teamService;
        this.matchService = matchService;
        this.hotelService = hotelService;

    }

    @GetMapping("/teams/{id}/edit")
    public String showEdit(@PathVariable Long id,Model model){
        Team team = this.teamService.findTeamById(id).orElse(null);
        if(team!=null){
            model.addAttribute("team",team);
        }
        return "formTeam.html";
    }

    @PostMapping("/teamsList/{id}")
    public String update(@PathVariable Long id, @RequestParam String city,
                         @RequestParam String name) {
       this.teamService.update(id,name,city);
        return "redirect:/teamsList";
    }

    @GetMapping("/teams/add")
    public String showAdd(){
        return "formTeam.html";
    }

    @PostMapping("/teamsList")
    public String create(@RequestParam String name,@RequestParam String city){
        teamService.save(name,city);
        return "redirect:/teamsList";
    }

    @PostMapping("/teams/{id}/delete")
    public String delete(@PathVariable Long id){
        this.teamService.deleteById(id);
        return "redirect:/teamsList";
    }

    @GetMapping("/teamsList")
    public String showAllTeams(Model model){
        List<Team>teams = this.teamService.findAll();
        model.addAttribute("teams",teams);
        return "listAllTeams.html";
    }


    @PostMapping("/teams/{id}")
    public String selectTeam(@PathVariable Long id){
        this.teamId = id;
        return "redirect:/matches";
    }

    //MatchFunctions
    ///
    ////
    /////
    //////

    @GetMapping("/matches")
    public String getMatches(Model model){
        Team team = teamService.findTeamById(teamId).orElse(null);
        List<Match>matches = matchService.findByHomeTeam(teamId);
        model.addAttribute("matches",matches);
        return "listMatches.html";
    }

    @GetMapping("/match/add")
    public String showAddMatch(Model model){
        Team team = teamService.findTeamById(teamId).orElse(null);
        List<Team> teams= teamService.findAll();
        model.addAttribute("homeTeam",team);
        model.addAttribute("teams",teams);
        List<Match>matches = matchService.findAll();
        model.addAttribute("matches",matches);
        return "formMatch.html";
    }

    @PostMapping("/matches")
    public String create(@RequestParam Long hteam, @RequestParam Long ateam, @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date,
            @RequestParam int attendance,
            @RequestParam int ptickets){
        Team homeT = teamService.findTeamById(hteam).orElse(null);
        Team awayY = teamService.findTeamById(ateam).orElse(null);
        matchService.create(homeT,awayY,date,attendance,ptickets);
        return "redirect:/matches";
    }


    @GetMapping("/matches/{id}/edit")
    public String showEditMatch(@PathVariable Long id,Model model){
        Match match = matchService.findMatchById(id).orElse(null);
        model.addAttribute("match",match);
        Team team = teamService.findTeamById(teamId).orElse(null);
        model.addAttribute("homeTeam",team);
        List<Match>matches = matchService.findByHomeTeam(teamId);
        model.addAttribute("matches",matches);
        List<Team> teams= teamService.findAll();
        model.addAttribute("teams",teams);
        return "formMatch.html";
    }

    @PostMapping("/matches/{id}")
    public String updateMatch(@PathVariable Long id,@RequestParam Long hteam, @RequestParam Long ateam, @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date,
                              @RequestParam int attendance,
                              @RequestParam float ptickets) {
        Team homeT = teamService.findTeamById(hteam).orElse(null);
        Team awayY = teamService.findTeamById(ateam).orElse(null);
        this.matchService.update(id,homeT,awayY,date,attendance,ptickets);
        return "redirect:/matches";
    }

    @PostMapping("/matches/{id}/delete")
    public String deleteMatch(@PathVariable Long id){
        this.matchService.delete(id);
        return "redirect:/matches";
    }



    //
    ///
    ////
    /////Reserve ticket

    @GetMapping("/reserve/{id}")
    public String reserve(@PathVariable("id") Long id,Model model){
        Match match = matchService.findMatchById(id).orElse(null);
        if(match!=null) {
            Team team = match.getHomeTeam();
            String city = team.getCity();
            List<Hotel> hotels = hotelService.findHotelsByCity(city);
            model.addAttribute("hotels", hotels);
            model.addAttribute("match", match);
        }
        return "reserve.html";
    }

    @PostMapping("reserve/{id}")
   public String reserving(@PathVariable Long id){
        return "redirect:/leagues";
   }



}
