package edu.fafic;

import edu.fafic.domain.Championship;
import edu.fafic.domain.Coach;
import edu.fafic.domain.Player;
import edu.fafic.domain.Team;
import edu.fafic.factory.ConnectionFacade;
import edu.fafic.service.ChampionshipService;
import edu.fafic.service.CoachService;
import edu.fafic.service.PlayerService;
import edu.fafic.service.TeamService;

import java.util.Set;

public class Main {
    public static void main(String[] args) {

        Championship worldCup = new Championship();
        worldCup.setName("World Cup 2026");

        Coach coach = new Coach();
        coach.setName("Alex Ferguson");

        Team team = new Team();
        team.setName("Dream FC");
        team.setCoach(coach);
        team.setChampionships(Set.of(worldCup));

        Player p1 = new Player();
        p1.setName("John Doe");
        p1.setStatus("Active");
        p1.setPosition("Forward");
        p1.setTeam(team);

        Player p2 = new Player();
        p2.setName("Mike Smith");
        p2.setStatus("Active");
        p2.setPosition("Midfielder");
        p2.setTeam(team);

        ConnectionFacade.execute(em -> {
            em.persist(worldCup);
            em.persist(coach);
            em.persist(team);
            em.persist(p1);
            em.persist(p2);
        });

        System.out.println("\nPlayer Queries\n");

        PlayerService playerService = new PlayerService();
        playerService.getByName(p1.getName()).ifPresent(System.out::println);
        playerService.getByStatus(p2.getStatus()).forEach(System.out::println);
        playerService.getByTeamId(team.getId()).forEach(System.out::println);

        System.out.println("\nTeam Queries\n");

        TeamService teamService = new TeamService();
        teamService.getByName(team.getName()).ifPresent(System.out::println);
        teamService.getByPlayerName(p1.getName()).ifPresent(System.out::println);
        teamService.getChampionshipsById(team.getId()).forEach(System.out::println);

        System.out.println("\nCoach Queries\n");

        CoachService coachService = new CoachService();
        coachService.getTeamAndPlayersByName(coach.getName()).ifPresent(System.out::println);

        System.out.println("\nChampionship Queries\n");

        ChampionshipService championshipService = new ChampionshipService();
        championshipService.getTeamsByName(worldCup.getName()).forEach(System.out::println);
    }
}
