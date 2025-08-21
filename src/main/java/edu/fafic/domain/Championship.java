package edu.fafic.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@NamedQueries({
        @NamedQuery(
                name = "championship.getTeamsByName",
                query = "SELECT t FROM Championship c JOIN c.teams t WHERE c.name = :name"
        )
})
public class Championship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "championships")
    private Set<Team> teams = new HashSet<>();

    @Override
    public String toString() {
        return "Championship{" +
               "id=" + id +
               '}';
    }
}
