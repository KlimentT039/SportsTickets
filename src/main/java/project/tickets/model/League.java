package project.tickets.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class League {
    String country;
    @Id
    String name;
    @OneToMany
    List<Team> teams;
    String logoUrl;
    public League(String country,String name,List<Team>teams,String logoUrl){
        this.country = country;
        this.name = name;
        this.teams = teams;
        this.logoUrl = logoUrl;
    }

    public League() {

    }

    public League(String country, String name) {
        this.country = country;
        this.name = name;
        this.teams=new ArrayList<Team>();
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public void setLogoUrl(String logoUrl){
        this.logoUrl = logoUrl;
    }

    public String getLogoUrl() {
        return logoUrl;
    }
}
