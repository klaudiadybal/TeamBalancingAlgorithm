package teambalancer;

import java.util.ArrayList;
import java.util.List;

public class Team {

    List<String> individuals;

    Double teamSkill;

    public Team() {
        individuals = new ArrayList<>();
        teamSkill = 0.0;
    }

    public List<String> getIndividuals() {
        return individuals;
    }

    public void setIndividuals(List<String> individuals) {
        this.individuals = individuals;
    }

    public Double getTeamSkill() {
        return teamSkill;
    }

    public void setTeamSkill(Double teamSkill) {
        this.teamSkill = teamSkill;
    }

}
