package teambalancer;

import java.util.*;

public class TeamBalancer {

    Team findTeamWithTheLowestTeamSkill(List<Team> teams) {
        PriorityQueue<Team> teamQueue = new PriorityQueue<>(Comparator.comparingDouble(Team::getTeamSkill));
        teamQueue.addAll(teams);
        return teamQueue.poll();
    }

    public List<Team> balanceTeams(Map<String, Double> individuals, int numberOfTeams) {

        if(numberOfTeams > individuals.size() || numberOfTeams == 0) {
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



    List<Team> getTeams(int numberOfTeams, List<String> individualsNames) {

        if(individualsNames.size() % numberOfTeams != 0) {
            numberOfTeams = getCustomNumberOfTeams(numberOfTeams, individualsNames);
        }

        List<Team> teams = new ArrayList<>();
        for(int i = 0; i < numberOfTeams; i++) {
            teams.add(new Team());
        }
        return teams;
    }

    int getCustomNumberOfTeams(int numberOfTeams, List<String> individualsNames) {

        for(int i = 2; i <= individualsNames.size(); i++) {
            if(individualsNames.size() % i == 0) {
                numberOfTeams = i;
                break;
            }
        }

        return numberOfTeams;
    }


    void printTeams(List<Team> teams) {

        double standardDeviation = getStandardDeviation(teams);

        for(int i = 0; i < teams.size(); i++) {
            String individuals = teams.get(i).getIndividuals().toString();
            System.out.format(Locale.US, "Team no %d has %d players(%s). Average rate: %.1f\n", i+1,
                    teams.get(i).getIndividuals().size(),
                    individuals.substring(1, individuals.length() - 1),
                    teams.get(i).getTeamSkill() / teams.get(i).getIndividuals().size());
        }

        System.out.print("Teams rate standard deviation: " + standardDeviation + "\n");
    }

    double getStandardDeviation(List<Team> teams) {
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
