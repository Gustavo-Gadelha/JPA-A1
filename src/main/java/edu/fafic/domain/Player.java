package edu.fafic.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@NamedQueries({
        @NamedQuery(
                name = "player.getByName",
                query = "SELECT p FROM Player p WHERE p.name = :name"
        ),
        @NamedQuery(
                name = "player.getByStatus",
                query = "SELECT p FROM Player p WHERE p.status = :status"
        ),
        @NamedQuery(
                name = "player.getByTeamId",
                query = "SELECT p FROM Player p JOIN p.team t WHERE t.id = :teamId"
        )
})
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String position;

    private String status;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @Override
    public String toString() {
        return "Player{" +
               "id=" + id +
               '}';
    }
}
