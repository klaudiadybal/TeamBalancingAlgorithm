package teambalancer;

import java.util.*;

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

        List<String> individualsNames = new ArrayList<>(individuals.keySet());

        Collections.sort(individualsNames, (a, b) -> (int) (individuals.get(b) - individuals.get(a)));

        List<Team> teams = getTeams(numberOfTeams, individualsNames);

        for(String individualName : individualsNames) {
            Team lowest = findTeamWithTheLowestTeamSkill(teams);
            lowest.getIndividuals().add(individualName);
            Double teamSkill = lowest.getTeamSkill();
            lowest.setTeamSkill(teamSkill + individuals.get(individualName));
        }

        return teams;
    }

    private List<Team> getTeams(int numberOfTeams, List<String> individualsNames) {
        if(individualsNames.size() % numberOfTeams != 0) {
            numberOfTeams = getCustomNumberOfTeams(numberOfTeams, individualsNames);
        }

        List<Team> teams = new ArrayList<>();
        for(int i = 0; i < numberOfTeams; i++) {
            teams.add(new Team());
        }
        return teams;
    }

    private int getCustomNumberOfTeams(int numberOfTeams, List<String> individualsNames) {
        numberOfTeams = Math.max(numberOfTeams, 2);

        int individualsPerTeam = individualsNames.size() / numberOfTeams;

        while (numberOfTeams * individualsPerTeam < individualsNames.size()) {
            numberOfTeams++;
            individualsPerTeam = individualsNames.size() / numberOfTeams;
        }

        return numberOfTeams;
    }


//    private void showTeams(List<Team> teams) {
//        for(int i = 0; i < teams.size(); i++) {
//            String individuals = teams.get(i).getIndividuals().toString();
//            System.out.println("Team no " + (i + 1) + " has " + teams.get(i).getIndividuals().size() + " players(" +
//                    individuals.substring(1, individuals.length() - 1) + "). Average rate: " + teams.get(i).getTeamSkill() / 2);
//        }
//
//        System.out.println();
//    }
//
//    public static void main(String[] args) {
//        TeamBalancer teamBalancer = new TeamBalancer();
//
//        Map<String, Double> individuals = new HashMap<>();
//        individuals.put("Johnny", 8.0);
//        individuals.put("Robbie", 5.0);
//        individuals.put("Juliet", 3.0);
//        individuals.put("Scarlet", 5.0);
//        individuals.put("Jude", 9.0);
//        individuals.put("Deborah", 6.0);
//
//        int numberOfTeams = 3;
//
//        List<Team> teams = teamBalancer.balanceTeams(individuals, numberOfTeams);
//        teamBalancer.showTeams(teams);
//    }
}
