package edu.fafic.service;

import edu.fafic.domain.Team;
import edu.fafic.factory.ConnectionFacade;

import java.util.List;

public class ChampionshipService {

    public List<Team> getTeamsByName(String name) {
        return ConnectionFacade.executeAndReturn(em -> em.createNamedQuery("championship.getTeamsByName", Team.class)
                .setParameter("name", name)
                .getResultList());
    }
}
