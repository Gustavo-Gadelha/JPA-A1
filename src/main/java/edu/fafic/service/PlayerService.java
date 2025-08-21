package edu.fafic.service;

import edu.fafic.domain.Player;
import edu.fafic.factory.ConnectionFacade;

import java.util.List;
import java.util.Optional;

public class PlayerService {

    public Optional<Player> getByName(String name) {
        return ConnectionFacade.executeAndReturn(em -> em.createNamedQuery("player.getByName", Player.class)
                .setParameter("name", name)
                .getResultList()
                .stream()
                .findFirst());
    }

    public List<Player> getByStatus(String status) {
        return ConnectionFacade.executeAndReturn(em -> em.createNamedQuery("player.getByStatus", Player.class)
                .setParameter("status", status)
                .getResultList());
    }

    public List<Player> getByTeamId(Long teamId) {
        return ConnectionFacade.executeAndReturn(em -> em.createNamedQuery("player.getByTeamId", Player.class)
                .setParameter("teamId", teamId)
                .getResultList());
    }
}
