package edu.fafic.service;

import edu.fafic.domain.Team;
import edu.fafic.factory.ConnectionFacade;

import java.util.Optional;

public class CoachService {

    public Optional<Team> getTeamAndPlayersByName(String coachName) {
        return ConnectionFacade.executeAndReturn(em -> em.createNamedQuery("coach.getTeamByName", Team.class)
                .setParameter("name", coachName)
                .getResultList()
                .stream()
                .findFirst());
    }
}
