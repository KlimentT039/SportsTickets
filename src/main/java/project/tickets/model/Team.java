package project.tickets.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(updatable = false, nullable = false)
    private Long id;

    private String name;
    private String city;

    public Team(String name,String city) {
        this.name = name;
        this.city = city;
    }


    public Team() {

    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
