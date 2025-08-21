package edu.fafic.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@NamedQueries({
        @NamedQuery(
                name = "team.getByName",
                query = "SELECT t FROM Team t WHERE t.name = :name"
        ),
        @NamedQuery(
                name = "team.getByPlayerName",
                query = "SELECT t FROM Team t JOIN t.players p WHERE p.name = :playerName"
        ),
        @NamedQuery(
                name = "team.getChampionshipsById",
                query = "SELECT c FROM Team t JOIN t.championships c WHERE t.id = :id"
        )
})
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
    @ToString.Exclude
    private Set<Player> players;

    @OneToOne(mappedBy = "team", cascade = CascadeType.ALL)
    private Coach coach;

    @ManyToMany
    @ToString.Exclude
    private Set<Championship> championships = new LinkedHashSet<>();

    @Override
    public String toString() {
        return "Team{" +
               "id=" + id +
               '}';
    }
}
