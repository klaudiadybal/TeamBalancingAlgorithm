package teambalancer;

import java.util.*;

public class TeamBalancer {

    Team findTeamWithTheLowestTeamSkill(List<Team> teams) {
        PriorityQueue<Team> teamQueue = new PriorityQueue<>(Comparator.comparingDouble(Team::getTeamSkill));
        teamQueue.addAll(teams);
        return teamQueue.poll();
    }

    Team findTeamWithTheLowestTeamSize(List<Team> teams) {
        return Collections.min(teams, Comparator.comparingInt(team -> team.getIndividuals().size()));
    }

    public List<Team> balanceTeams(Map<String, Double> individuals, int numberOfTeams) {

        if(numberOfTeams > individuals.size() || numberOfTeams == 0) {
            return new ArrayList<>();
        }

        List<String> individualsNames = new ArrayList<>(individuals.keySet());

        individualsNames.sort((a, b) -> (int) (individuals.get(b) - individuals.get(a)));

        List<Team> teams = getTeams(numberOfTeams, individualsNames);


        for (String individualsName : individualsNames) {
            Team lowestTeamSkill = findTeamWithTheLowestTeamSkill(teams);
            Team lowestTeamSize = findTeamWithTheLowestTeamSize(teams);

            if (lowestTeamSkill.getIndividuals().size() > lowestTeamSize.getIndividuals().size()) {
                addIndividual(individuals, individualsName, lowestTeamSize);
            } else {
                addIndividual(individuals, individualsName, lowestTeamSkill);
            }
        }

        printTeams(teams);

        return teams;
    }

    private void addIndividual(Map<String, Double> individuals, String individualsName, Team team) {
        team.getIndividuals().add(individualsName);
        Double teamSkill = team.getTeamSkill();
        team.setTeamSkill(teamSkill + individuals.get(individualsName));
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
        int size = individualsNames.size();

        for(int i = 2; i <= size; i++) {
            if(size % i == 0) {
                numberOfTeams = i;
                break;
            }
        }

        return numberOfTeams;
    }


    void printTeams(List<Team> teams) {

        double standardDeviation = getStandardDeviation(teams);

        int teamSize = teams.get(0).getIndividuals().size();
        StringBuilder stringBuilder = new StringBuilder();


        for(int i = 0; i < teams.size(); i++) {
            String individuals = teams.get(i).getIndividuals().toString();
            String formattedAverageRate = String.format(Locale.US, "%.1f", teams.get(i).getTeamSkill() / teamSize);
            stringBuilder.append("Team no ").append(i + 1).append(" has ").append(teamSize).append(" players(").append(individuals, 1, individuals.length() - 1)
                .append("). Average rate: ").append(formattedAverageRate).append("\n");
        }

        stringBuilder.append("Teams rate standard deviation: ").append(standardDeviation).append("\n");
        System.out.print(stringBuilder);
    }

    double getStandardDeviation(List<Team> teams) {
        double totalSkill = 0;
        int teamSize = teams.get(0).getIndividuals().size();
        int teamsSize = teams.size();

        for (Team team : teams) {
            totalSkill += team.getTeamSkill() / teamSize;
        }

        double averageSkill = totalSkill / teamsSize;
        double squaredDifferencesSum = 0;

        for (Team team : teams) {
            squaredDifferencesSum += (team.getTeamSkill() / teamSize - averageSkill) * (team.getTeamSkill() / teamSize - averageSkill);
        }
        double variance = squaredDifferencesSum / teamsSize;

        double standardDeviation = Math.sqrt(variance);
        return Math.round(standardDeviation * 100.0) / 100.0;
    }

}
