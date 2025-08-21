package edu.fafic.service;

import edu.fafic.domain.Championship;
import edu.fafic.domain.Team;
import edu.fafic.factory.ConnectionFacade;

import java.util.List;
import java.util.Optional;

public class TeamService {

    public Optional<Team> getByName(String name) {
        return ConnectionFacade.executeAndReturn(em -> em.createNamedQuery("team.getByName", Team.class)
                .setParameter("name", name)
                .getResultList()
                .stream()
                .findFirst());
    }

    public Optional<Team> getByPlayerName(String playerName) {
        return ConnectionFacade.executeAndReturn(em -> em.createNamedQuery("team.getByPlayerName", Team.class)
                .setParameter("playerName", playerName)
                .getResultList()
                .stream()
                .findFirst());
    }

    public List<Championship> getChampionshipsById(Long teamId) {
        return ConnectionFacade.executeAndReturn(em -> em.createNamedQuery("team.getChampionshipsById", Championship.class)
                .setParameter("id", teamId)
                .getResultList());
    }
}
