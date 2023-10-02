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

        if(numberOfTeams > individuals.size()) {
            return new ArrayList<>();
        }

        List<String> individualsNames = new ArrayList<>(individuals.keySet());

        individualsNames.sort((a, b) -> (int) (individuals.get(b) - individuals.get(a)));

        List<Team> teams = getTeams(numberOfTeams, individualsNames);

        for(String individualName : individualsNames) {
            Team lowest = findTeamWithTheLowestTeamSkill(teams);
            lowest.getIndividuals().add(individualName);
            Double teamSkill = lowest.getTeamSkill();
            lowest.setTeamSkill(teamSkill + individuals.get(individualName));
        }

        printTeams(teams);

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


    public void printTeams(List<Team> teams) {

        double standardDeviation = getStandardDeviation(teams);

        for(int i = 0; i < teams.size(); i++) {
            String individuals = teams.get(i).getIndividuals().toString();
            System.out.print("Team no " + (i + 1) + " has " + teams.get(i).getIndividuals().size() + " players(" +
                    individuals.substring(1, individuals.length() - 1) + "). Average rate: " +
                    teams.get(i).getTeamSkill() / teams.get(i).getIndividuals().size() + "\n");
        }
        System.out.print("Teams rate standard deviation: " + standardDeviation);
    }

    public double getStandardDeviation(List<Team> teams) {
        double totalSkill = 0;
        for (Team team : teams) {
            totalSkill += team.getTeamSkill() / team.getIndividuals().size();
        }
        double averageSkill = totalSkill / teams.size();

        double squaredDifferencesSum = 0;
        for (Team team : teams) {
            squaredDifferencesSum += (team.getTeamSkill() / team.getIndividuals().size() - averageSkill) * (team.getTeamSkill() / team.getIndividuals().size() - averageSkill);
        }
        double variance = squaredDifferencesSum / teams.size();

        double standardDeviation = Math.sqrt(variance);
        return Math.round(standardDeviation * 100.0) / 100.0;
    }

}
