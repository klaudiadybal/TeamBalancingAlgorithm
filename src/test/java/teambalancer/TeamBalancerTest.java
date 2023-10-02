package teambalancer;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.in;


class TeamBalancerTest {

    TeamBalancer teamBalancer = new TeamBalancer();

    @Test
    public void shouldFindTeamWithTheSmallestTeamSkill() {
        List<Team> teams = new ArrayList<>();

        Team team1 = new Team();
        team1.setTeamSkill(10.0);
        teams.add(team1);

        Team team2 = new Team();
        team2.setTeamSkill(9.0);
        teams.add(team2);

        Team team3 = new Team();
        team3.setTeamSkill(8.0);
        teams.add(team3);

        Team lowest = teamBalancer.findTeamWithTheLowestTeamSkill(teams);

        assertThat(lowest).isEqualTo(team3);
    }

    @Test
    public void shouldNotFailIfTeamSkillsAreEqual() {
        List<Team> teams = new ArrayList<>();

        Team team1 = new Team();
        team1.setTeamSkill(10.0);
        teams.add(team1);

        Team team2 = new Team();
        team2.setTeamSkill(10.0);
        teams.add(team2);

        Team team3 = new Team();
        team3.setTeamSkill(10.0);
        teams.add(team3);

        Team lowest = teamBalancer.findTeamWithTheLowestTeamSkill(teams);

        assertThat(lowest.getTeamSkill()).isEqualTo(10.0);
    }

    @Test
    public void shouldReturnCorrectNumberOfTeams() {
        Map<String, Double> individuals = new HashMap<>();
        individuals.put("Jude", 6.5);
        individuals.put("Johnny", 6.0);
        individuals.put("Scarlet", 5.5);

        int numberOfTeams = 3;

        List<Team> teams = teamBalancer.balanceTeams(individuals, numberOfTeams);

        assertThat(teams.size()).isEqualTo(3);

    }

    @Test
    public void shouldReturnTeamsWithCorrectSize() {
        Map<String, Double> individuals = new HashMap<>();
        individuals.put("Johnny", 8.0);
        individuals.put("Robbie", 5.0);
        individuals.put("Juliet", 3.0);
        individuals.put("Scarlet", 5.0);
        individuals.put("Jude", 9.0);
        individuals.put("Deborah", 6.0);

        int numberOfTeams = 3;

        List<Team> teams = teamBalancer.balanceTeams(individuals, numberOfTeams);

        assertThat(teams.get(0).getIndividuals().size()).isEqualTo(2);
        assertThat(teams.get(1).getIndividuals().size()).isEqualTo(2);
        assertThat(teams.get(2).getIndividuals().size()).isEqualTo(2);
    }

    @Test
    public void shouldCreateTeamsWithEqualNumbersOfIndividuals() {
        Map<String, Double> individuals = new HashMap<>();
        individuals.put("Robert", 1.0);
        individuals.put("Olivia", 5.0);
        individuals.put("Harry", 4.0);
        individuals.put("Alice", 6.0);
        individuals.put("Jack", 5.0);

        int numberOfTeams = 4;

        List<Team> teams = teamBalancer.balanceTeams(individuals, numberOfTeams);

        assertThat(teams.size()).isEqualTo(5);

    }

    @Test
    public void shouldHandleIndivisibleNumberOfIndividuals(){
        Map<String, Double> individuals = new HashMap<>();
        individuals.put("Robert", 1.0);
        individuals.put("Olivia", 5.0);
        individuals.put("Harry", 4.0);
        individuals.put("Alice", 6.0);
        individuals.put("Jack", 5.0);
        individuals.put("Robbie", 5.0);
        individuals.put("Juliet", 3.0);
        individuals.put("Scarlet", 5.0);
        individuals.put("Jude", 9.0);
        individuals.put("Deborah", 6.0);

        int numberOfTeams = 3;

        List<Team> teams = teamBalancer.balanceTeams(individuals, numberOfTeams);

        assertThat(teams.size()).isEqualTo(5);
    }

    @Test
    public void shouldBalanceTeamsAppropriately() {
        Map<String, Double> individuals = new HashMap<>();
        individuals.put("Johnny", 8.0);
        individuals.put("Robbie", 5.0);
        individuals.put("Juliet", 3.0);
        individuals.put("Scarlet", 5.0);
        individuals.put("Jude", 9.0);
        individuals.put("Deborah", 6.0);

        int numberOfTeams = 3;

        List<Team> teams = teamBalancer.balanceTeams(individuals, numberOfTeams);

        assertThat(teams.get(0).getTeamSkill() / 2).isEqualTo(6.0);
        assertThat(teams.get(1).getTeamSkill() / 2).isEqualTo(6.5);
        assertThat(teams.get(2).getTeamSkill() / 2).isEqualTo(5.5);
    }

    @Test
    public void shouldAssignCorrectIndividualsToTeams(){
        Map<String, Double> individuals = new HashMap<>();
        individuals.put("Johnny", 8.0);
        individuals.put("Robbie", 5.0);
        individuals.put("Juliet", 3.0);
        individuals.put("Scarlet", 5.0);
        individuals.put("Jude", 9.0);
        individuals.put("Deborah", 6.0);

        int numberOfTeams = 3;

        List<Team> teams = teamBalancer.balanceTeams(individuals, numberOfTeams);


        assertThat(teams.get(0).getIndividuals().get(0)).isEqualTo("Jude");
        assertThat(teams.get(0).getIndividuals().get(1)).isEqualTo("Juliet");
        assertThat(teams.get(1).getIndividuals().get(0)).isEqualTo("Johnny");
        assertThat(teams.get(1).getIndividuals().get(1)).isEqualTo("Robbie");
        assertThat(teams.get(2).getIndividuals().get(0)).isEqualTo("Deborah");
        assertThat(teams.get(2).getIndividuals().get(1)).isEqualTo("Scarlet");
    }

    @Test
    public void shouldReturnEmptyListIfNumberOfTeamsIsGreaterThanNumberOfIndividuals(){
        Map<String, Double> individuals = new HashMap<>();
        individuals.put("Johnny", 8.0);
        individuals.put("Robbie", 5.0);

        int numberOfTeams = 3;

        List<Team> teams = teamBalancer.balanceTeams(individuals, numberOfTeams);

        assertThat(teams.size()).isEqualTo(0);
        assertThat(teams).isEmpty();
    }
}