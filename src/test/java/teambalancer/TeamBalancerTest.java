package teambalancer;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


class TeamBalancerTest {

    TeamBalancer teamBalancer = new TeamBalancer();

    @Test
    public void shouldFindTeamWithTheSmallestTeamSkill() {
        List<Team> teams = new ArrayList<>();

        Team team1 = new Team();
        team1.setTeamSkill(10);
        teams.add(team1);

        Team team2 = new Team();
        team2.setTeamSkill(9);
        teams.add(team2);

        Team team3 = new Team();
        team3.setTeamSkill(8);
        teams.add(team3);

        Team lowest = teamBalancer.findTeamWithTheLowestTeamSkill(teams);

        assertThat(lowest).isEqualTo(team3);
    }

    @Test
    public void shouldNotFailIfTeamSkillsAreEqual() {
        List<Team> teams = new ArrayList<>();

        Team team1 = new Team();
        team1.setTeamSkill(10);
        teams.add(team1);

        Team team2 = new Team();
        team2.setTeamSkill(10);
        teams.add(team2);

        Team team3 = new Team();
        team3.setTeamSkill(10);
        teams.add(team3);

        Team lowest = teamBalancer.findTeamWithTheLowestTeamSkill(teams);

        assertThat(lowest.getTeamSkill()).isEqualTo(10);
    }
}