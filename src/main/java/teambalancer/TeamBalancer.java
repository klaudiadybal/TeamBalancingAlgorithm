package teambalancer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TeamBalancer {

    public Team findTeamWithTheLowestTeamSkill(List<Team> teams) {
        Team lowest = teams.get(0);
        for(Team team : teams){
            if(team.getTeamSkill() < lowest.getTeamSkill()){
                lowest = team;
            }
        }

        return lowest;
    }

    public List<Team> balanceTeams(Map<String, Double> individuals, int numberOfTeams) {

        List<Team> teams = new ArrayList<>();

        for(int i = 0; i < numberOfTeams; i++) {
            teams.add(new Team());
        }

        return teams;
    }
}
