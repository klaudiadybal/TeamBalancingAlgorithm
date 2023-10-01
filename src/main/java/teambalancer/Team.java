package teambalancer;

import java.util.ArrayList;
import java.util.List;

public class Team {

    List<String> individuals;

    float teamSkill;

    public Team() {
        individuals = new ArrayList<>();
        teamSkill = 0;
    }

    public List<String> getIndividuals() {
        return individuals;
    }

    public void setIndividuals(List<String> individuals) {
        this.individuals = individuals;
    }

    public float getTeamSkill() {
        return teamSkill;
    }

    public void setTeamSkill(float teamSkill) {
        this.teamSkill = teamSkill;
    }
}
